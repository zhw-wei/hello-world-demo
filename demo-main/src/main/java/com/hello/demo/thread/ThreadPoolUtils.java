package com.hello.demo.thread;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadPoolUtils {

    public static void pool(){
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        executorService.execute(()-> System.out.println("hello thread pool 01"));

        executorService.shutdown();
    }

    public static void thread(){
        Thread th01 = new Thread(()-> System.out.println("hello world thread 01"));
        Thread th02 = new Thread(()-> System.out.println("hello world thread 02"));
        Thread th03 = new Thread(()-> System.out.println("hello world thread 03"));

        th01.setPriority(Thread.MIN_PRIORITY);
        th02.setPriority(Thread.MAX_PRIORITY);
        th03.setPriority(Thread.NORM_PRIORITY);

        th01.start();
        th02.start();
        th03.start();
    }

    public static void pool_s(){
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10);
        executorService.scheduleAtFixedRate(
                () -> System.out.println("hello world thread 02, " + ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"))),
                3, 10, TimeUnit.SECONDS);
    }
}
