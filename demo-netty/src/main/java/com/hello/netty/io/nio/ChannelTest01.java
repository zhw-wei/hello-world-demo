package com.hello.netty.io.nio;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ChannelTest01 {
    /**
     * 使用通道读数据
     * @param args
     */
    public static void main(String[] args) throws IOException {
        //第一个文件字节流输入流与源文件接通
        FileInputStream fis = new FileInputStream("data01.txt");

        //得到文件字节输入流的文件通道
        FileChannel channel = fis.getChannel();

        //定义一个缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        //读取数据到缓冲区
        channel.read(buffer);

        //读取方式1
        //设置数组大小
        //切换到读数据模式
        /*
        byte[] bytes = new byte[buffer.position()];
        buffer.flip();
        buffer.get(bytes);
        String str = new String(bytes);
         */

        //读取方式2
        buffer.flip();
        String str = new String(buffer.array(), 0, buffer.remaining());

        System.out.println(str);

    }
}
