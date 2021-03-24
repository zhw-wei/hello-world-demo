package com.hello.demo.disruptor.disruptor_01;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TradeTransaction {
    private String id;//交易id
    private BigDecimal price;//交易金额
}
