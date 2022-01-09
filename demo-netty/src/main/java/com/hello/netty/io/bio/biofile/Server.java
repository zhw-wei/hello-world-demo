package com.hello.netty.io.bio.biofile;

import com.hello.netty.io.Config;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 实现接收客户端的任意类型文件，并保存到服务器端磁盘
 */
public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(Config.SERVER_PORT);

        while (true){
            Socket socket = serverSocket.accept();
            //交给一个独立的线程来处理与这个客户端的通信
            new ServerReaderThread(socket).start();
        }
    }

}
