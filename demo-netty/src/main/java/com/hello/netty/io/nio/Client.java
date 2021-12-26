package com.hello.demo.io.nio;

import com.hello.demo.io.Config;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class Client {

    /**
     * 客户端案例事件，基于NIO非阻塞通信
     * @param args
     */
    public static void main(String[] args) throws IOException {
        System.out.println("server start ...");
        //获取通道
        SocketChannel sChannel = SocketChannel.open(new InetSocketAddress(Config.SERVER_IP, Config.SERVER_PORT));
        // 切换成非阻塞模式
        sChannel.configureBlocking(false);
        //分配缓冲区大小
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        //发送数据给服务端
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("请说：");
            String msg = scanner.nextLine();

            buffer.put(msg.getBytes());
            buffer.flip();
            sChannel.write(buffer);

            buffer.clear();
        }
    }
}
