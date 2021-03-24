package com.hello.demo.disruptor.disruptor_01;

import com.lmax.disruptor.EventHandler;

import java.util.UUID;

public class TradeTransactionInDBHandler implements EventHandler<TradeTransaction> {
    @Override
    public void onEvent(TradeTransaction event, long sequence, boolean endOfBatch) throws Exception {
        this.onEvent(event);
    }

    private void onEvent(TradeTransaction trade){
        trade.setId(UUID.randomUUID().toString());
        System.out.println(trade.getId());
    }
}
