package com.hello.demo.io.bio.biofile;

import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.UUID;

public class ServerReaderThread extends Thread{

    private Socket socket;

    public ServerReaderThread(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {

        try{
            //得到一个数据输入流读取客户端发送的数据
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            //读取客户端发送过来的文件类型
            String suffix = dataInputStream.readUTF();
            //定一个字节输出管道，把客户端发来的文件数据写出去
            OutputStream outputStream = new FileOutputStream("/Users/zhw/Pictures/" + UUID.randomUUID().toString() + suffix);
            //从数据输入流中读取文件数据，写出到字节输出流中
            byte[] buffer = new byte[1024];
            int len;
            while ((len = dataInputStream.read(buffer)) > 0){
                outputStream.write(buffer, 0, len);
            }

            System.out.println("服务端接收到文件，保存成功");
            //为了方便测试，未执行正常代码编写
            outputStream.close();
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
