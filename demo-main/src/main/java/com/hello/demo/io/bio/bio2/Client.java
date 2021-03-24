package com.hello.demo.io.bio.bio2;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        System.out.println("client start...");
        //1. 创建一个socket对象请求服务端的连接
        Socket socket = new Socket("127.0.0.1", 9999);
        //2. 从Socket对象中获取一个字节输出流（根据数据流量看，是输出流）
        OutputStream outputStream = socket.getOutputStream();
        //3。 把字节输出流包装成一个打印流
        PrintStream printStream = new PrintStream(outputStream);
        printStream.println("hello world");
        printStream.flush();
    }
}
