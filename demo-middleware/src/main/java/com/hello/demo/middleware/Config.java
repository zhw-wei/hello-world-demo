package com.hello.demo.middleware;

/**
 * linux虚拟机环境下
 * 1. 向rocketmq推送消息会连接超时，执行时需要暂停一段时间，防止发送报错
 * 2. 连接zookeeper时正常，能见听到节点变动，但是读取创建节点
 *
 *
 * @author: zhaohw
 * @date: 2021.12.24 下午 5:03
 */
public class Config {

    static final Boolean IS_VM = true;

    public static void sleep() {
        sleep(1);
    }

    public static void sleep(int second) {
        if (IS_VM) {
            try {
                System.out.println("sleep start ......");
                //虚拟机循环发送消息过快，需要暂停一段时间，防止发送报错
                Thread.sleep(1000 * second);
                System.out.println("sleep end ......");
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }
}
