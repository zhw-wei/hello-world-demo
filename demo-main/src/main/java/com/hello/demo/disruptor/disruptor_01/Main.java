package com.hello.demo.disruptor.disruptor_01;

import com.lmax.disruptor.BatchEventProcessor;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SequenceBarrier;
import com.lmax.disruptor.YieldingWaitStrategy;

import java.math.BigDecimal;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        final int BUFFER_SIZE = 1024;
        final int THREAD_NUMBERS = 2;

        final RingBuffer<TradeTransaction> ringBuffer =
                RingBuffer.createSingleProducer(() -> new TradeTransaction(), BUFFER_SIZE, new YieldingWaitStrategy());

        //创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_NUMBERS);

        //创建sequenceBarrier序号屏障器
        SequenceBarrier barrier = ringBuffer.newBarrier();

        //创建消息处理器
        BatchEventProcessor<TradeTransaction> eventProcessor =
                new BatchEventProcessor<>(ringBuffer, barrier, new TradeTransactionInDBHandler());

        //ringBuffer可以知晓消费者的状态
        ringBuffer.addGatingSequences(eventProcessor.getSequence());

        executorService.submit(eventProcessor);

        Future<?> future = executorService.submit(() -> {
            long seq;
            for(int i=0; i<1000; i++){
                seq = ringBuffer.next();
                ringBuffer.get(seq).setPrice(new BigDecimal(Math.random()*1000));
                ringBuffer.publish(seq);
            }
        });
        future.get();
        Thread.sleep(1000);
        eventProcessor.halt();
        executorService.shutdown();
    }
}
