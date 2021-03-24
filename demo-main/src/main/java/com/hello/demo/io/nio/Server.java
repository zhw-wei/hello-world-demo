package com.hello.demo.io.nio;

import com.hello.demo.io.Config;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class Server {

    /**
     * NIO非阻塞通信下的案例，服务端
     * 服务端接收客户端的连接请求，并接收多个客户端发送过来的事件
     * @param args
     */
    public static void main(String[] args) throws IOException {
        System.out.println("client start ...");
        //获取通道
        ServerSocketChannel ssChannel = ServerSocketChannel.open();
        //切换为非阻塞模式
        ssChannel.configureBlocking(false);
        //绑定连接的端口
        ssChannel.bind(new InetSocketAddress(Config.SERVER_PORT));
        //获取选择器Selector
        Selector selector = Selector.open();
        //将通道注册到选择器上，并开始指定监听接收事件
        ssChannel.register(selector, SelectionKey.OP_ACCEPT);

        //使用选择器轮询已经就绪好的事件
        while (selector.select()>0){//select方法是阻塞方法，没有事件会暂停

            //获取选择器中所有注册的通道中已经就绪号的事件
            Iterator<SelectionKey> it = selector.selectedKeys().iterator();
            //遍历这些准备好的事件
            while (it.hasNext()){
                //获取当前事件
                SelectionKey sk = it.next();

                //判断这个事件具体是什么
                if(sk.isAcceptable()){//客户端连接事件
                    //获取当前接入的客户端通道
                    SocketChannel sChannel = ssChannel.accept();
                    //切换成非阻塞模式
                    sChannel.configureBlocking(false);
                    //将本客户端通道注册到选择器
                    sChannel.register(selector, SelectionKey.OP_READ);//注意：监听事件不一样
                }else if(sk.isReadable()){//读事件
                    //获取当前选择器上的读就绪事件
                    SocketChannel sChannel = (SocketChannel) sk.channel();
                    //读取数据
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    int len = 0;
                    while ((len = sChannel.read(buffer)) > 0){
                        buffer.flip();
                        System.out.println(new String(buffer.array(), 0, len));
                        buffer.clear(); //清除之前的数据
                    }

                }

                //处理完毕，移除当前事件
                it.remove();
            }

        }

    }
}
