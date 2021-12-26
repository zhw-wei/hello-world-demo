package com.hello.demo.io.bio.bio4;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class HandlerSocketServerPool {

    private ExecutorService executorService;

    /**
     * @param maxThreadNum 客户端最大连接数
     * @param queueSize 队列最大值
     */
    public HandlerSocketServerPool(int maxThreadNum, int queueSize){
        this.executorService = new ThreadPoolExecutor(3, maxThreadNum > 3 ? maxThreadNum : 5,
                120, TimeUnit.SECONDS, new ArrayBlockingQueue<>(queueSize));
    }

    public void execute(Runnable target){
        executorService.execute(target);
    }
}
