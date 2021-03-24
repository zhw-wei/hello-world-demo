package com.hello.demo.disruptor.disruptor_03;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

public class Main {

    private static Test01 test01 = new Test01();
    private static Test02 test02 = new Test02();

    private static AtomicLong time0 = new AtomicLong(0);
    private static AtomicLong time1 = new AtomicLong(0);

    public static void main(String[] args) throws Exception {
        Thread.sleep(1000 * 3);

        /**
         * 其它说明：
         * 1. 要看到明显的效果，需要有足够多的线程数
         * 2. 线程数少的话，对对象操作耗费时间不是很明显
         * 3. 如果线程过少，则代码顺序对时间的影响会足够大
         */
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ExecutorService executorService = Executors.newFixedThreadPool(200);

        for (int i = 0; i < 100; i++) {

            executorService.submit(() -> {
                try {
                    countDownLatch.await();
                    System.out.println("start time: " + System.currentTimeMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                test01();
            });
            executorService.submit(() -> {
                try {
                    countDownLatch.await();
                    System.out.println("start time: " + System.currentTimeMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                test02();
            });
        }

        countDownLatch.countDown();
        executorService.shutdown();

        Thread.sleep(10 * 1000); //5秒钟之后输出，保证除主线程之外的线程执行结束
        //执行结果 - time1远小于time0
        //其中一次执行结果 - time0=4445600, time1=36800
        System.out.println(time0);
        System.out.println(time1);
    }

    private static void test01() {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        CountDownLatch countDownLatch = new CountDownLatch(1);  //线程同时开始

        for (int i = 0; i < 100; i++) {
            executorService.submit(() -> {
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                long startTime = System.nanoTime();
                //同一个缓存行，产生伪共享
                test01.value0 = 1;
                test01.value1 = 1;

                time0.getAndAdd(System.nanoTime() - startTime);
            });
        }
        countDownLatch.countDown();
        executorService.shutdown();
    }

    private static void test02() {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        CountDownLatch countDownLatch = new CountDownLatch(1);//线程同时开始

        for (int i = 0; i < 100; i++) {
            executorService.submit(() -> {
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                long startTime = System.nanoTime();
                //两个缓存行，不产生伪共享
                test02.value0 = 1;
                test02.value1 = 1;

                time1.getAndAdd(System.nanoTime() - startTime);
            });
        }
        countDownLatch.countDown();
        executorService.shutdown();
    }

    private static class Test01 {
        private long value0 = 1;    //未处理伪共享 - value0/value1处于同一个缓存行中
        private long value1 = 2;
    }

    private static class Test02 {
        @jdk.internal.vm.annotation.Contended   //注解避免伪共享 - value0/value1/处于两个缓存行中
        private long value0 = 1;
        @jdk.internal.vm.annotation.Contended
        private long value1 = 2;
    }
}
