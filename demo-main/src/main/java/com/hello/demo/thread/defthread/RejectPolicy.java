package com.hello.demo.thread.defthread;

import java.util.concurrent.Executor;

public interface RejectPolicy {

    void reject(Runnable command, Executor executor);
}
