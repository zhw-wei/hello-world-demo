package com.hello.demo.middleware.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import static com.hello.demo.middleware.kafka.KafkaConfig.*;

public class ConsumerTest {

    private KafkaConsumer<String, String> consumer;
    private ConsumerRecords<String, String> msgList;

    @BeforeEach
    public void before() {
        Properties props = new Properties();
        props.put("bootstrap.servers", serverPath);
        props.put("group.id", groupId);
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("auto.offset.reset", "earliest");
        props.put("key.deserializer", StringDeserializer.class.getName());
        props.put("value.deserializer", StringDeserializer.class.getName());
        this.consumer = new KafkaConsumer<String, String>(props);
        this.consumer.subscribe(Arrays.asList(topic));
    }

    @DisplayName("消费者")
    @Test
    public void doCon() {

        int messageNo = 1;
        int sleepNum = 0;
        System.out.println("---------开始消费---------");
        try {
            for (; ; ) {
                msgList = consumer.poll(Duration.ofMillis(1000));
                if (null != msgList && msgList.count() > 0) {
                    for (ConsumerRecord<String, String> record : msgList) {
                        //消费100条就打印 ,但打印的数据不一定是这个规律的
                        System.out.println(messageNo + "=======receive: key = " + record.key()
                                + ", value = " + record.value()
                                + " offset===" + record.offset());
                        messageNo++;
                    }
                } else {
                    System.out.println("--------sleep-------");
                    sleepNum ++;
                    Thread.sleep(1000);
                    if(sleepNum>10) break;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            consumer.close();
        }
    }
}
