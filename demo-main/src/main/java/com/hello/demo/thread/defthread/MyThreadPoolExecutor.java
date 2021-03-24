package com.hello.demo.thread.defthread;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

public class MyThreadPoolExecutor implements Executor {
    //线程池的名字
    private String name;
    //线程序列号
    private AtomicInteger sequence = new AtomicInteger(0);
    //核心线程数
    private Integer coreSize;
    //最大线程数
    private Integer maxSize;
    //正在运行的线程数
    private AtomicInteger runningCount = new AtomicInteger(0);
    //任务队列
    private BlockingQueue<Runnable> taskQueue;
    //拒绝策略
    private RejectPolicy rejectPolicy;

    public MyThreadPoolExecutor(String name, Integer coreSize, Integer maxSize, BlockingQueue<Runnable> taskQueue, RejectPolicy rejectPolicy){
        this.name = name;
        this.coreSize = coreSize;
        this.maxSize = maxSize;
        this.taskQueue = taskQueue;
        this.rejectPolicy = rejectPolicy;
    }

    @Override
    public void execute(Runnable command) {
        //正在运行的线程数
        Integer count = runningCount.get();
        //如果正在运行的线程数小于核心线程数，直接增加一个线程
        if(count < coreSize){
            //这里不一定成功，addWorker()方法还需要判断是不是确实小于核心线程数
            //添加成功，方法执行结束
            if(addWorker(command, true)) return;
            //添加核心线程数失败，进行下面的逻辑
        }
        //如果达到了核心线程数，先尝试让任务入队
        //这里之所以用offer()，是因为如果队列满了，offer()会立即返回false
        if(taskQueue.offer(command)){
            //do nothing
        }else{  //如果队列入队失败，说明队列满了，那就增加一个非核心线程
            if(!addWorker(command, false)){
                //如果非核心线程添加失败了，那就执行拒绝策略
                rejectPolicy.reject(command, this);
            }
        }
    }

    private Boolean addWorker(Runnable command, Boolean core){
        //自旋判断是不是真的可以创建一个线程
        for(;;){
            //正在运行的线程数
            Integer count = runningCount.get();
            //核心线程还是非核心线程
            Integer max = core ? coreSize : maxSize;
            //不满足创建线程的条件，直接返回false
            if(max <= count) return false;
            //修改runningCount成功，可以修改线程
            if(runningCount.compareAndSet(count, count + 1)){
                //线程的名字
                String threadName = (core ? "core_" : "") + name + sequence.incrementAndGet();
                //创建线程，并启动
                new Thread(() -> {
                    System.out.println("Thread name : " + Thread.currentThread().getName());
                    //运行的任务
                    Runnable task = command;
                    //不断从任务队列中取任务执行，如果取出来的任务为null，则跳出循环，线程就结束了
                    while(Objects.nonNull(task) || Objects.nonNull(task = getTask())){
                        try{
                            task.run();
                        }finally {
                            task = null;
                        }
                    }
                }, threadName).start();
            }

            break;
        }

        return true;
    }

    private Runnable getTask(){

        try {
            //take()方法会一直阻塞，直到取到任务为止
            return taskQueue.take();
        } catch (InterruptedException e) {
            //线程中断了，返回null可以结束当前线程
            //当前线程结束，把runningCount数减一
            runningCount.decrementAndGet();
            return null;
        }
    }

}
