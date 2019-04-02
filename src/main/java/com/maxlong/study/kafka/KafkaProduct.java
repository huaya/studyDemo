package com.maxlong.study.kafka;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import java.util.Properties;

/**
 * 描述：
 * 作者： ma.xl
 * 版本： 1.0.0
 * 时间： 2018-8-18 13:21
 */
@Log4j2
public class KafkaProduct {

    static final String TOPIC = "TOPIC_SPOT_PRICE_CHNGE_NOTICE";
    static final String servers = "192.168.128.128:9092";//172.16.80.141:9092,172.16.80.142:9092,172.16.80.143:9092


    public static void main(String[] args) {
        log.info("KafkaProduct");
        Properties props = new Properties();
        props.put("bootstrap.servers", servers);
        props.put("group.id", "test-consumer-group");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.springframework.kafka.support.serializer.JsonSerializer");

        Producer<String, SpotPriceNoiceMsg> producer = new KafkaProducer<>(props);
        SpotPriceNoiceMsg spotPriceNoiceMsg = new SpotPriceNoiceMsg();
        spotPriceNoiceMsg.setCfgId("1045");
        spotPriceNoiceMsg.setLow("100");
        spotPriceNoiceMsg.setHigh("200");
        spotPriceNoiceMsg.setMiddle("150");
        spotPriceNoiceMsg.setPublishPoint("10:15");
        spotPriceNoiceMsg.setPublishDate("2019-01-21");
        spotPriceNoiceMsg.setPublishTime("10:15:00");
        spotPriceNoiceMsg.setContract("ZN1907");

        producer.send(new ProducerRecord<>(TOPIC, spotPriceNoiceMsg));
        log.info("send message：{}", JSONObject.toJSONString(spotPriceNoiceMsg));
        producer.close();
    }
}
