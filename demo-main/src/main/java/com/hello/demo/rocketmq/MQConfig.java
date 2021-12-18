package com.hello.demo.rocketmq;

/**
 * @author zhw
 * @date 2021/12/18 8:58 下午
 */
public interface MQConfig {

    String MQ_LINUX_PATH="192.168.88.130:9876";

    String MQ_MAC_PATH="192.168.1.118:9876";

    String MQ_PATH=MQ_MAC_PATH;

    String MQ_TOPIC="topic_hello_01";
}
