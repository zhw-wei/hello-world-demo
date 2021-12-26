package com.hello.demo.io.bio.bio4;

import com.hello.demo.io.Config;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    /**
     * 启动多个执行测试(idea设置）
     */
    public static void main(String[] args) throws IOException {
        System.out.println("client start...");
        //1. 创建一个socket对象请求服务端的连接
        Socket socket = new Socket(Config.SERVER_IP, Config.SERVER_PORT);
        //2. 从Socket对象中获取一个字节输出流（根据数据流量看，是输出流）
        OutputStream outputStream = socket.getOutputStream();
        //3。 把字节输出流包装成一个打印流
        PrintStream printStream = new PrintStream(outputStream);

        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.print("please input: ");
            String msg = scanner.nextLine();
            printStream.println(msg);
            printStream.flush();
        }
    }
}
