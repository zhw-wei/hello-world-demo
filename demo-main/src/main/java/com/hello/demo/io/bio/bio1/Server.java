package com.hello.demo.io.bio.bio1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    /**
     * 此处案例中，服务端会一直等待客户端的消息，如果客户端没有进行消息的发送，服务端将一直进入阻塞状态
     * 同时服务端是按照行获取消息的，这意味着客户端也必须按照行进行消息的发送，否则服务端将进入等待消息的阻塞状态
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
        if ((msg = bufferedReader.readLine()) != null){ //readLine: 一次接受一行
            System.out.println("服务端接收到：" + msg);
        }
    }
}
