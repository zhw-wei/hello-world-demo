package com.hello.netty.io.bio.bio4;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 伪异步BIO通信架构 - 实现方式：线程池、任务队列
 */
public class Server {

    public static void main(String[] args) throws IOException {
        System.out.println("server start...");
        ServerSocket serverSocket = new ServerSocket(9999);

        HandlerSocketServerPool serverPool = new HandlerSocketServerPool(5, 10);
        //定一个循环接收客户端的socket连接请求
        while(true){
            Socket socket = serverSocket.accept();
            //把socket对象交给一个线程池进行处理
            Runnable runnable = new ServerRunnableTarget(socket);
            serverPool.execute(runnable);
        }
    }
}
