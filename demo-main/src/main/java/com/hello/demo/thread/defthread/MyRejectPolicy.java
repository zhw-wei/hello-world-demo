package com.hello.demo.thread.defthread;

import java.util.concurrent.Executor;

public class MyRejectPolicy implements RejectPolicy{
    @Override
    public void reject(Runnable command, Executor executor) {
        System.out.println("hello world! my reject policy!");
    }
}
