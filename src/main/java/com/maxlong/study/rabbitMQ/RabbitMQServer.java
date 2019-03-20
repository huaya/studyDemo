package com.maxlong.study.rabbitMQ;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.QueueingConsumer;

/**
 * @author 作者 maxlong:
 * @version 创建时间：2016年8月19日 下午4:01:47
 * 类说明
 */
public class RabbitMQServer {

    public static final String queue_name = "my_queue";
    public static final boolean autoAck = false;
    public static final boolean durable = true;

    public static void main(String[] argv) throws IOException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.46.128");
        factory.setPort(5672);
        factory.setVirtualHost("/");
        factory.setUsername("admin");
        factory.setPassword("admin");
        Channel channel = null;
        try {
            Connection connection = factory.newConnection();
            channel = connection.createChannel();
            channel.queueDeclare(queue_name, durable, false, false, null);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        System.out.println("Wait for message");

        QueueingConsumer consumer = new QueueingConsumer(channel);

        try {
            //在消息确认之前，不接受其他消息
//            channel.basicQos(1);
            channel.basicConsume(queue_name, autoAck, consumer);

            while(true){
                QueueingConsumer.Delivery deliver = null;
                try {
                    deliver = consumer.nextDelivery();
                    String messageStr = new String(deliver.getBody());
                    deliver.getProperties();
                    System.out.println("Message received:" + messageStr);

                    Message message = JSONObject.parseObject(messageStr, Message.class);

                    Thread.sleep(10000);
                    System.out.println("job end!:" + message.getMsgId());

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    channel.basicAck(deliver.getEnvelope().getDeliveryTag(), false);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
