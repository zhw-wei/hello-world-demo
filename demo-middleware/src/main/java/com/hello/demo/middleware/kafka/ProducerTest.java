package com.hello.demo.middleware.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Properties;

import static com.hello.demo.middleware.kafka.KafkaConfig.serverPath;
import static com.hello.demo.middleware.kafka.KafkaConfig.topic;

public class ProducerTest {
    private KafkaProducer<String, String> producer;

    @BeforeEach
    public void before() {
        Properties props = new Properties();
        props.put("bootstrap.servers", serverPath);
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("key.serializer", StringSerializer.class.getName());
        props.put("value.serializer", StringSerializer.class.getName());
        this.producer = new KafkaProducer<>(props);
    }

    @DisplayName("生产者")
    @Test
    public void test(){

        try {
            for(int i=0; i<1000; i++) {
                String messageStr= String.format("你好，这是第%s条数据", i);
                System.out.println(messageStr);
                producer.send(new ProducerRecord<>(topic, "Message", messageStr));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.close();
        }
    }
}
