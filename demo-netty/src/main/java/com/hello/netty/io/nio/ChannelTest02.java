package com.hello.demo.io.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ChannelTest02 {
    /**
     * 使用channel完成文件的复制
     * @param args
     */
    public static void main(String[] args) throws IOException {

        //得到一个字节输入流、字节输出流
        FileInputStream fis = new FileInputStream("data01.txt");
        FileOutputStream fos = new FileOutputStream("data02.txt");

        //得到文件通道
        FileChannel isChannel = fis.getChannel();
        FileChannel osChannel = fos.getChannel();

        //分配缓冲区，一次读写1024字节大小
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        while (true){
            //必须先清空缓冲区，在写入到缓冲区
            buffer.clear();
            //开始读取一次数据
            int flag = isChannel.read(buffer);
            if(flag == -1) break;   //-1表示读完成

            //已经读取了数据，把缓冲区的模式切换成可读模式
            buffer.flip();
            //把数据写入到文件
            osChannel.write(buffer);
        }
        isChannel.close();
        osChannel.close();
        System.out.println("复制完成");
    }
}
