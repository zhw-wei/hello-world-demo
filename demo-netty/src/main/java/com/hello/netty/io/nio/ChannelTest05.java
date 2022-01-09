package com.hello.netty.io.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class ChannelTest05 {
    public static void main(String[] args) throws IOException {

        //字节输入管道
        FileInputStream is = new FileInputStream("data01.txt");
        FileChannel isChannel = is.getChannel();
        //字节输出流管道
        FileOutputStream fos = new FileOutputStream("data03.txt");
        FileChannel osChannel = fos.getChannel();

        //复制数据
        isChannel.transferTo(isChannel.position(), isChannel.size(), osChannel);

        isChannel.close();
        osChannel.close();
    }
}
