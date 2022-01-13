package com.hello.netty.io.bio.bio5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ServerReaderThread extends Thread{

    private Socket socket;

    public ServerReaderThread(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String msg;
            while ((msg = bufferedReader.readLine()) != null){ //客户端下线时，此处会抛出异常
                System.out.println("服务端接到数据：" + msg);
                //服务端接收到了客户端的消息之后，需要推送给当前所有的在线socket
                this.sendMessageToAllClient(msg);
            }
        } catch (IOException e) {
            System.out.println("当前有人下线...");
            //从在线socket集合中中移除本socket
            Server.allSocketOnLine.remove(socket);
        }
    }

    /**
     * 把当前消息发送给所有的客户但
     * @param msg
     */
    private void sendMessageToAllClient(String msg) throws IOException {
        for (Socket sk : Server.allSocketOnLine) {
            PrintStream printStream = new PrintStream(sk.getOutputStream());
            printStream.println(msg);
            printStream.flush();
        }
    }
}
