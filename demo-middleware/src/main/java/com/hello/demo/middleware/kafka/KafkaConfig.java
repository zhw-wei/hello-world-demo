package com.hello.demo.middleware.kafka;

public interface KafkaConfig {

    String serverPath = "localhost:9092";
    String groupId = "groupA";
    String topic = "kafka-test";
}
