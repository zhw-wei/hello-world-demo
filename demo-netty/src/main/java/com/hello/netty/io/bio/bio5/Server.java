package com.hello.demo.io.bio.bio5;

import com.hello.demo.io.Config;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * bio模式下，端口转发思想 - 服务端实现
 *
 * 服务端实现的需求：
 * 1、 注册端口
 * 2、 接收客户端的socket连接，交给一个独立的线程来处理
 * 3、 把当前连接的客户端socket存入到一个所谓的在线socket集合中保存
 * 4、 接收客户端的消息，然后推送给当前所有在线的socket接收
 */
public class Server {

    public static List<Socket> allSocketOnLine = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        System.out.println("server start ...");
        ServerSocket serverSocket = new ServerSocket(Config.SERVER_PORT);

        while (true){
            Socket socket = serverSocket.accept();
            //把登陆的客户端socket存入到一个在线集合中
            allSocketOnLine.add(socket);
            //为当前登陆的socket分配一个独立的线程来处理，用于通信
            new ServerReaderThread(socket).start();
        }
    }
}
