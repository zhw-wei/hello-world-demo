package com.hello.netty.demo01.server;

import com.hello.netty.demo01.dto.MessageInput;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class MessageController extends ChannelInboundHandlerAdapter {

    private ThreadPoolExecutor executor;//业务线程池

    public MessageController(int workerThreads){

        //业务队列最大1000，避免堆积
        //如果子线程处理不过来，io线程会加入处理业务逻辑
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(1000);

        //业务线程命名
        ThreadFactory factory = new ThreadFactory() {
            AtomicInteger seq = new AtomicInteger();
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setName("rpc-" + seq.getAndIncrement());
                return t;
            }
        };

        //闲置时间超过30秒的线程自动销毁
        this.executor = new ThreadPoolExecutor(1, workerThreads, 30, TimeUnit.SECONDS,
                queue, factory, new ThreadPoolExecutor.CallerRunsPolicy());
    }

    public void closeGracefully(){
        //优雅一点关闭，先通知，再等待，最后强制关闭
        this.executor.shutdown();
        try {
            this.executor.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
        }
        this.executor.shutdownNow();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //客户端来了一个新的连接
        System.out.println("connection comes");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        //客户端走了一个
        System.out.println("connection leaves");
        ctx.close();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(msg instanceof MessageInput){
            System.out.println("read a message");
            //用业务线程池处理
            this.executor.execute(() -> {
                this.handlerMessage(ctx, (MessageInput) msg);
            });
        }
    }
    private void handlerMessage(ChannelHandlerContext ctx, MessageInput msg){

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("connection error");
        cause.printStackTrace();
        ctx.close();
    }
}
