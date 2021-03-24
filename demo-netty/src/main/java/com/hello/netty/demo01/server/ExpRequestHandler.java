package com.hello.netty.demo01.server;

import com.hello.netty.demo01.dto.ExpRequest;
import com.hello.netty.demo01.dto.ExpResponse;
import com.hello.netty.demo01.dto.MessageOutput;
import io.netty.channel.ChannelHandlerContext;

public class ExpRequestHandler implements IMessageHandler<ExpRequest> {
    @Override
    public void handle(ChannelHandlerContext ctx, String requestId, ExpRequest message) {
        int base = message.getBase();
        int exp = message.getExp();
        long start = System.nanoTime();

        long res = 1L;
        for (int i = 0; i < exp; i++) {
            res *= base;
        }
        long cost = System.nanoTime() - start;

        //输出响应
        ctx.writeAndFlush(new MessageOutput("exp_res", requestId, new ExpResponse(res, cost)));
    }
}
