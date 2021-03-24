package com.hello.demo.io.nio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ChannelTest {

    /**
     * 使用通道写数据
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        //字节输出流通向目标文件，文件位置：项目根目录，也可以是其它目录，此处是为了方便多环境测试
        FileOutputStream fos = new FileOutputStream("data01.txt");
        //得到字节输出流对应的通道channel
        FileChannel channel = fos.getChannel();
        //分配缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put("hello world".getBytes());

        //把缓冲区切换成写出模式
        buffer.flip();

        System.out.println("写数据到文件中...");
        channel.write(buffer);
        channel.close();
    }
}
