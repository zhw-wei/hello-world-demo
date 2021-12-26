package com.hello.demo.io.bio.bio3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerThreadReader extends Thread{

    private Socket socket;

    public ServerThreadReader(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            //3. 从Socket管道中得到一个字节输入流对象
            InputStream inputStream = socket.getInputStream();
            //4. 把字节输入流包装成一个缓冲字符输入流
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String msg;
            while ((msg = bufferedReader.readLine()) != null) { //readLine: 一次接受一行
                System.out.println("服务端接收到：" + msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
