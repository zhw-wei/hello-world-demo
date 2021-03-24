package com.hello.demo.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockUtils {
    private int value = 0;
    private Lock lock = new ReentrantLock();

    public int getValue(){
        lock.lock();
        int value = this.value++;
        lock.unlock();
        return value;
    }
}
