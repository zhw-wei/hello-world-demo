package com.hello.netty.io.nio.demo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

/**
 * 客户端代码逻辑事件
 */
public class Client {

    //定义客户端相关属性
    private Selector selector;
    private static int PORT = 9999;
    private SocketChannel socketChannel;

    //初始化客户端信息
    public Client(){

        try {
            //创建选择器
            selector = Selector.open();
            //连接服务器
            socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", PORT));
            //设置非阻塞模式
            socketChannel.configureBlocking(false);
            //绑定选择器
            socketChannel.register(selector, SelectionKey.OP_READ);
            System.out.println("当前客户端准备完成：");

        }catch (Exception ex){

        }
    }

    private void readInfo() throws IOException {
        if(selector.select() > 0){
            Iterator<SelectionKey> it = selector.selectedKeys().iterator();
            while (it.hasNext()){
                SelectionKey sk = it.next();
                if(sk.isReadable()){
                    SocketChannel sc = (SocketChannel) sk.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    sc.read(buffer);
                    buffer.flip();
                    System.out.println(new String(buffer.array(), 0, buffer.remaining()));
                }

                it.remove();
            }
        }
    }

    private void sendToServer(String msg){
        try {
            socketChannel.write(ByteBuffer.wrap(msg.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client client = new Client();

        //定义一个线程专门负责发送过来的读消息事件
        new Thread(() -> {
            while (true) {
                try {
                    client.readInfo();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()){
            String str = scanner.nextLine();
            client.sendToServer(str);
        }
    }
}
