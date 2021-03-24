package com.hello.demo.thread;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;

public class FutureUtils {

    public static void completableFuture() throws ExecutionException, InterruptedException, TimeoutException {
        //无返回
        CompletableFuture.runAsync(() -> System.out.println("hello world completable future!"));
        //有返回
        CompletableFuture<String> cf01 = CompletableFuture.supplyAsync(() -> "cf01");
        System.out.println(cf01.getNow("cf01-err"));
        System.out.println(cf01.get(1, TimeUnit.SECONDS));
    }

    //带返回值的线程
    public static void future() throws ExecutionException, InterruptedException, TimeoutException {
        FutureTask<String> task = new FutureTask<>(() -> "task");
        new Thread(task).start();
        System.out.println(task.get(10, TimeUnit.SECONDS));
    }

    //定时器
    public static void timer(){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("timer");
            }
        }, 0, 1000);
    }
    //quartz
}
