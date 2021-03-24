package com.hello.demo.io.nio.demo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

public class Server {

    //定义一些成员属性：选择器、服务端通道、端口
    private Selector selector;
    private ServerSocketChannel ssChannel;
    private static final int PORT = 9999;

    //定义初始化代码
    public Server(){
        try{
            //创建选择器对象
            selector = Selector.open();
            //获取通道
            ssChannel = ServerSocketChannel.open();
            //绑定客户端连接的端口
            ssChannel.bind(new InetSocketAddress(PORT));
            //设置非阻塞通信模式
            ssChannel.configureBlocking(false);

            //把通道注册到选择器上，并且开始指定接收连接事件
            ssChannel.register(selector, SelectionKey.OP_ACCEPT);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    /**
     * 开始监听
     */
    private void listen(){

        try {

            while (selector.select() > 0){
                //获取选择器中所有注册通道的就绪事件
                Iterator<SelectionKey> it = selector.selectedKeys().iterator();

                //开始遍历这些事件
                while (it.hasNext()){
                    SelectionKey sk = it.next();

                    //判断事件的类型
                    if(sk.isAcceptable()){//客户端接入请求
                        //获取当前客户端通道
                        SocketChannel sChannel = ssChannel.accept();
                        //注册成非阻塞模式
                        sChannel.configureBlocking(false);
                        //组册给选择器，监听读数据的事件
                        sChannel.register(selector, SelectionKey.OP_READ);

                    }else if(sk.isReadable()){  //客户端读事件
                        //处理客户端消息，接收它然后实现转发逻辑
                        this.readClientData(sk);
                    }

                    //处理完毕之后，移除当前事件
                    it.remove();
                }
            }

        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    /**
     * 接收当前客户端通道的消息，转发给其它全部客户端通道
     * @param sk
     */
    private void readClientData(SelectionKey sk){
        SocketChannel sChannel = null;

        try {
            //得到当前客户端通道
            sChannel = (SocketChannel) sk.channel();
            //创建缓冲区对象，开始接收客户端通道的数据
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int count = sChannel.read(buffer);
            if(count > 0) {
                buffer.flip();
                //收到的消息
                String msg = new String(buffer.array(), 0, buffer.remaining());
                System.out.println("接收到了客户端消息：" + msg);
                //把这个消息推送给全部客户端
                this.sendMsgToAllClient(msg, sChannel);
            }

        }catch (Exception ex){
            try {
                System.out.println("有人离线了" + sChannel.getRemoteAddress());
                //当前客户端离线
                sk.cancel();    //取消注册
                sChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 把当前客户端的消息推送给当前全部在线的channel
     */
    private void sendMsgToAllClient(String msg, SocketChannel sChannel) throws IOException {
        System.out.println("服务端开始转发这个消息，当前处理的线程：" + Thread.currentThread().getName());
        for (SelectionKey key : selector.keys()) {
            Channel channel = key.channel();
            //不要把数据发给自己
            if(channel instanceof SocketChannel && sChannel != channel){
                ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
                ((SocketChannel)channel).write(buffer);
            }
        }
    }

    public static void main(String[] args) {
        //创建服务端对象
        Server server = new Server();

        //开始监听客户端各种消息事件：连接、群聊消息、离线消息
        server.listen();
    }
}
