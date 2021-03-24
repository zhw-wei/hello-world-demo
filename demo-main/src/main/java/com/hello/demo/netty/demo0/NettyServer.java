package com.hello.demo.netty.demo0;

import com.hello.demo.netty.NettyConfig;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer {

    public static void main(String[] args) throws InterruptedException {
        //创建BoosGroup 和 WorkerGroup
        //1、创建两个线程组
        //2、boosGroup处理连接请求，真正与客户端业务处理会交给workerGroup去完成
        //3、两个group都是无限循环
        EventLoopGroup boosGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            //创建服务器端启动的对象，配置参数
            ServerBootstrap bootstrap = new ServerBootstrap();

            //使用链式编程来进行设置
            bootstrap.group(boosGroup, workerGroup) //设置两个线程组
                    .channel(NioServerSocketChannel.class)  //使用NioServerSocketChannel作为服务器通道的实现
                    .option(ChannelOption.SO_BACKLOG, 128)  //设置线程队列得到连接个数
                    .childOption(ChannelOption.SO_KEEPALIVE, true)  //设置保持活动连接状态
                    .childHandler(new ChannelInitializer<SocketChannel>() { //创建一个通道测试对象
                        //给pipeline设置处理器
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new NettyServerHandler());
                        }
                    }); //给workerGroup的EventLoop对应的管道设置处理器

            System.out.println("server ready ...");

            //绑定一个端口并且同步，生成一个ChannelFuture对象
            //执行到这里，服务器已经启动
            ChannelFuture channelFuture = bootstrap.bind(NettyConfig.PORT).sync();

            //对关闭通道进行监听
            channelFuture.channel().closeFuture().sync();
        } finally {
            boosGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
