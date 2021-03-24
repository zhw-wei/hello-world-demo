package com.hello.netty.demo01.server;

import com.hello.netty.demo01.dto.MessageInput;
import io.netty.channel.ChannelHandlerContext;

/**
 * 找不到类型的消息统一使用默认的处理器处理
 */
public class DefaultHandler implements IMessageHandler<MessageInput>{
    @Override
    public void handle(ChannelHandlerContext ctx, String requestId, MessageInput n) {
        System.out.println("unrecognized message type=" + n.getType() + " comes");
    }
}
