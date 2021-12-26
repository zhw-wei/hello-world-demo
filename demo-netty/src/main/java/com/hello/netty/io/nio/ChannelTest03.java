package com.hello.demo.io.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ChannelTest03 {

    public static void main(String[] args) throws IOException {
        //字节输入管道
        FileInputStream is = new FileInputStream("data01.txt");
        FileChannel isChannel = is.getChannel();
        //字节输出流管道
        FileOutputStream fos = new FileOutputStream("data02.txt");
        FileChannel osChannel = fos.getChannel();

        //定义多个缓冲区做数据分散
        ByteBuffer buffer1 = ByteBuffer.allocate(4);
        ByteBuffer buffer2 = ByteBuffer.allocate(1024);
        ByteBuffer[] buffers = {buffer1, buffer2};

        //从通道中读取数据分散到各个缓冲区
        isChannel.read(buffers);

        //从每个缓冲区中查询是否有数据读取到了
        for (ByteBuffer buffer : buffers) {
            buffer.flip();  //切换到数据读取模式
            System.out.println(new String(buffer.array(), 0, buffer.remaining()));
        }

        //聚集写入到通道
        osChannel.write(buffers);

        isChannel.close();
        osChannel.close();
    }
}
