package com.hello.demo.io.bio.bio4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerRunnableTarget implements Runnable{

    private Socket socket;

    public ServerRunnableTarget(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {

        try {
            InputStream inputStream = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String msg;
            if((msg = bufferedReader.readLine()) != null){
                System.out.println("服务端接收到消息： " + msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
