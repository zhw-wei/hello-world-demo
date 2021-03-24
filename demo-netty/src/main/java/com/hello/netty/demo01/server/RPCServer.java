package com.hello.netty.demo01.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;

public class RPCServer {
    private String ip;
    private int port;
    private int ioThreads;  //  用于处理网络流的读写线程
    private int workerThreads;  //用于业务处理的计算线程

    public RPCServer(String ip, int port, int ioThreads, int workerThreads){
        this.ip = ip;
        this.port = port;
        this.ioThreads = ioThreads;
        this.workerThreads = workerThreads;
    }

    private ServerBootstrap bootstrap;
    private EventLoopGroup group;

}
