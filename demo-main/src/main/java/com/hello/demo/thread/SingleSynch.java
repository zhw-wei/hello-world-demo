package com.hello.demo.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleSynch {
    private Integer value = 0;

    public Integer addValue(){
        return value ++;
    }

    public Integer addValue_2(){
        synchronized (SingleSynch.class){
            value ++;
        }
        return value;
    }

    public void sout(){
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i=0; i<10; i++)
            executorService.submit(()-> System.out.println(addValue()));

        executorService.shutdown();
    }
    public void sout_2(){
        for (int i=0; i<10; i++)
            new Thread(()-> System.out.println(addValue_2())).start();
    }
}
