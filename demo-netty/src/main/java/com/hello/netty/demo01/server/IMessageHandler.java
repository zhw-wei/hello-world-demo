package com.hello.netty.demo01.server;

import io.netty.channel.ChannelHandlerContext;

public interface IMessageHandler<T> {

    void handle(ChannelHandlerContext ctx, String requestId, T n);
}
