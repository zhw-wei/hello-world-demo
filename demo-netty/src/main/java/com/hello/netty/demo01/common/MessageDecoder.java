package com.hello.netty.demo01.common;

import com.hello.netty.demo01.dto.MessageInput;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.DecoderException;
import io.netty.handler.codec.ReplayingDecoder;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * 消息解码器
 */
public class MessageDecoder extends ReplayingDecoder<MessageInput> {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        String requestId = this.readString(byteBuf);
        String type = this.readString(byteBuf);
        String content = this.readString(byteBuf);
        list.add(new MessageInput(type, requestId, content));
    }

    private String readString(ByteBuf buf){
        // 字符串先长度后字节数组，统一UTF8编码
        int len = buf.readInt();
        if(len <0 || len > (1 << 20)){
            throw new DecoderException("string to long len=" + len);
        }
        byte[] bytes = new byte[len];
        buf.readBytes(bytes);
        return new String(bytes, StandardCharsets.UTF_8);
    }
}
