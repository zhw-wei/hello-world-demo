package com.hello.demo.rocketmq.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

/**
 * @author: zhaohw
 * @date: 2021.12.15 下午 4:49
 */
public class HelloRocketMQConsumer {
    public static void main(String[] args) throws MQClientException {

        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer();
        consumer.setConsumerGroup("hello_group_01");

        //注册中心
        consumer.setNamesrvAddr("192.168.88.130:9876");
        //队列地址
        consumer.subscribe("topic_hello_01", "*");

        //消费方式
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.setMessageModel(MessageModel.BROADCASTING);

        //设置消费方式
        consumer.registerMessageListener((MessageListenerConcurrently) (msgList, context) -> {

            for (MessageExt message : msgList) {
                String msgId = message.getMsgId();
//                byte[] body = message.getBody();
                System.out.println("接收到消息，msgId: " + msgId);
            }

            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });

        System.out.println("连接开始。。。。。。");
        consumer.start();
        System.out.println("连接成功。。。。。。");

    }
}
