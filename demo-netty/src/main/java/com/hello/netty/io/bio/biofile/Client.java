package com.hello.netty.io.bio.biofile;

import com.hello.netty.io.Config;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * 实现客户端上传任意类型的文件数据给服务端保存
 */
public class Client {

    public static void main(String[] args) throws IOException {

        //1. 请求与服务端的socket连接
        Socket socket = new Socket(Config.SERVER_IP, Config.SERVER_PORT);
        //2. 把字节输出流包装成一个数据输出流
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        //3. 先发送上传文件的后缀给服务端
        dataOutputStream.writeUTF(".png");
        //4. 把文件数据发送给服务端进行接收
        InputStream inputStream = new FileInputStream("/Users/zhw/Pictures/5A30F213-CDF5-4035-A11C-952037EBDA8F.png");
        byte[] buffer = new byte[1024];
        int len;
        while((len = inputStream.read(buffer)) > 0){
            dataOutputStream.write(buffer, 0, len);
        }
        dataOutputStream.flush();
        //通知服务端数据发送完毕
        socket.shutdownOutput();
    }
}
