package com.maxlong.study.kafka;

import com.alibaba.fastjson.JSONObject;
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

    static final String TOPIC = "TOPIC_SPOT_PRICE_CHNGE_NOTICE";
    static final String servers = "192.168.128.128:9092";//172.16.80.141:9092,172.16.80.142:9092,172.16.80.143:9092

    public static void main(String[] args) {

        Properties props = new Properties();
        props.put("bootstrap.servers", servers);
        props.put("group.id", "test");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.springframework.kafka.support.serializer.JsonDeserializer");
        props.put("spring.json.trusted.packages", "com.maxlong.study.kafka");
        final KafkaConsumer<String, SpotPriceNoiceMsg> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList(TOPIC), new ConsumerRebalanceListener() {
            public void onPartitionsRevoked(Collection<TopicPartition> collection) {
            }
            public void onPartitionsAssigned(Collection<TopicPartition> collection) {
                consumer.seekToBeginning(collection);//将偏移设置到最开始
            }
        });
        log.info("message mintor....");
        while (true) {
            ConsumerRecords<String, SpotPriceNoiceMsg> records = consumer.poll(100);
            for (ConsumerRecord<String, SpotPriceNoiceMsg> record : records)
                log.error("offset = {}, value = {}", record.offset(), JSONObject.toJSONString(record.value()));
        }
    }
}
