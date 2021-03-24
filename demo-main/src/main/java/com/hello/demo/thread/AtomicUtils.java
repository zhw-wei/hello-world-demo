package com.hello.demo.thread;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class AtomicUtils {

    private AtomicInteger value = new AtomicInteger(0);
    private int[] array = {1, 2};
    private AtomicIntegerArray valueArray = new AtomicIntegerArray(array);

    public Integer getNext(Integer var){
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return value.getAndAdd(var);
    }
}
