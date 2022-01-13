package com.hello.netty.io.bio.bio2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    /**
     * 多发和多收机制实现 - 此处实现，一个服务端连接一个客户端，不支持多个
     */
    public static void main(String[] args) throws IOException {
        System.out.println("server start...");
        //1. 定义一个ServerSocket对象进行服务端的端口注册
        ServerSocket serverSocket = new ServerSocket(9999);
        //2. 监听客户端Socket连接请求
        Socket socket = serverSocket.accept();
        //3. 从Socket管道中得到一个字节输入流对象
        InputStream inputStream = socket.getInputStream();
        //4. 把字节输入流包装成一个缓冲字符输入流
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String msg;
        while ((msg = bufferedReader.readLine()) != null){ //readLine: 一次接受一行
            System.out.println("服务端接收到：" + msg);
        }
    }
}
