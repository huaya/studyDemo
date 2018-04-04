package com.maxlong.rabbitMQ;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

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

    public static final String queue_name="my_queue";
    public static final boolean autoAck=false;
    public static final boolean durable=true;

    public static void main(String[] argv)throws java.io.IOException,java.lang.InterruptedException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
//	        factory.setPort(5672);
//	        factory.setVirtualHost("/");
//	        factory.setUsername("guest");
//	        factory.setPassword("guest");
        Connection connection=factory.newConnection();
        Channel channel=connection.createChannel();
        channel.queueDeclare(queue_name, durable, false, false, null);
        System.out.println("Wait for message");
        channel.basicQos(1); //消息分发处理
        QueueingConsumer consumer=new QueueingConsumer(channel);
        channel.basicConsume(queue_name, autoAck, consumer);
        while(true){
            Thread.sleep(500);
            QueueingConsumer.Delivery deliver=consumer.nextDelivery();
            String message=new String(deliver.getBody());
            deliver.getProperties();
            System.out.println("Message received:"+message);
            channel.basicAck(deliver.getEnvelope().getDeliveryTag(), false);
        }
    }
}
