package com.maxlong.kafka;

import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import java.util.Arrays;
import java.util.Collection;
import java.util.Properties;

/**
 * 描述：
 * 作者： ma.xl
 * 版本： 1.0.0
 * 时间： 2018-8-18 13:24
 */
@Log4j2
public class KafkaCustom {

    public static void main(String[] args) {
        Properties props = new Properties();
//        props.put("bootstrap.servers", "139.224.15.79:9092");
        props.put("bootstrap.servers", "172.16.80.141:9092,172.16.80.142:9092,172.16.80.143:9092");
        props.put("group.id", "test-consumer-group");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        final KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("TOPIC_SPOT_PRICE_CHNGE_NOTICE"), new ConsumerRebalanceListener() {
//        consumer.subscribe(Arrays.asList("test"), new ConsumerRebalanceListener() {
            public void onPartitionsRevoked(Collection<TopicPartition> collection) {
            }
            public void onPartitionsAssigned(Collection<TopicPartition> collection) {
                //将偏移设置到最开始
                consumer.seekToBeginning(collection);
            }
        });
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records)
                log.error("offset = {}, key = {}, value = {}", record.offset(), record.key(), record.value());
        }
    }
}
