package com.hello.demo.middleware.rocketmq.producer;

import com.hello.demo.middleware.Config;
import com.hello.demo.middleware.rocketmq.MQConfig;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * @author: zhaohw
 * @date: 2021.12.15 下午 4:48
 */
public class HelloRocketMQProducer {

    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        //生产组
        DefaultMQProducer producer = new DefaultMQProducer("producer_group_01");

        //注册中心
        producer.setNamesrvAddr(MQConfig.MQ_PATH);

        System.out.println("连接开始。。。。。。");
        producer.start();
        System.out.println("连接成功。。。。。。");

        Message msg = new Message(MQConfig.MQ_TOPIC, //主题
                "Tags", //主要用于消息过滤
                "Keys", //消息的唯一值
                "hello world".getBytes());

        Config.sleep();
        //同步发送
        SendResult send = producer.send(msg);
        System.out.println(send);

        producer.shutdown();
    }
}
