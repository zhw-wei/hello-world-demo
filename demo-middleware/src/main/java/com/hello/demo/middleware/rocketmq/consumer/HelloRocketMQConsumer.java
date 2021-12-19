package com.hello.demo.middleware.rocketmq.consumer;

import com.hello.demo.middleware.rocketmq.MQConfig;
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

        //消费组
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("consumer_group_01");

        //注册中心
        consumer.setNamesrvAddr(MQConfig.MQ_PATH);

        //topic：主题地址，subExpression: tag过滤
        consumer.subscribe(MQConfig.MQ_TOPIC, "*");

        //消费方式
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.setMessageModel(MessageModel.BROADCASTING);

        //设置消费方式
        //msgList是list类型，说明可以一次性接收多个消息，默认32个
        consumer.registerMessageListener((MessageListenerConcurrently) (msgList, context) -> {

            for (MessageExt message : msgList) {
                String msgId = message.getMsgId();

                byte[] body = message.getBody();
                String result = new String(body);

                System.out.println("接收到消息，msgId: " + msgId + ", result: " + result);
            }

            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });

        System.out.println("连接开始。。。。。。");
        consumer.start();
        System.out.println("连接成功。。。。。。");
    }
}
