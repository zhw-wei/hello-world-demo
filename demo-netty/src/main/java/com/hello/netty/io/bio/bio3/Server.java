package com.hello.netty.io.bio.bio3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    /**
     * 多发和多收机制实现 - 此处实现，服务端可以接收多个客户端的通讯请求
     * 思路：服务端每接收一个客户端socket请求对象之后，都交给一个独立的线程来处理客户端的数据交互需求
     */
    public static void main(String[] args) throws IOException {
        System.out.println("server start...");
        //1. 定义一个ServerSocket对象进行服务端的端口注册
        ServerSocket serverSocket = new ServerSocket(9999);

        // 定义一个死循环，负责不断的接收客户端的socket连接请求
        while(true) {
            //2. 监听客户端Socket连接请求
            Socket socket = serverSocket.accept();

            //创建一个独立的线程，来处理来处理与客户端
            new ServerThreadReader(socket).start();
        }
    }
}
