package com.hello.demo.rocketmq.producer;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * @author: zhaohw
 * @date: 2021.12.15 下午 4:48
 */
public class HelloRocketMQProducer {

    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer("hello_group_01");

        //注册中心
        producer.setNamesrvAddr("192.168.88.130:9876");

        System.out.println("连接开始。。。。。。");
        producer.start();
        System.out.println("连接成功。。。。。。");

        Message msg = new Message("topic_hello_01", "hello world".getBytes());

        //同步发送
        producer.send(msg);
    }
}
