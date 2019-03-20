package com.maxlong.study.rabbitMQ;

import java.util.UUID;
import java.util.concurrent.TimeoutException;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

/**
 * @author 作者 maxlong:
 * @version 创建时间：2016年8月19日 下午4:01:58
 * 类说明
 */
public class RabbitMQClient {

	public static final String queue_name = "my_queue";
	public static final boolean durable = true; //消息队列持久化

	public static void main(String[] argv)throws java.io.IOException, TimeoutException {

		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("192.168.46.128");
		factory.setPort(5672);
		factory.setVirtualHost("/");
		factory.setUsername("admin");
		factory.setPassword("admin");
		Connection connection=factory.newConnection(); //创建连接
		Channel channel = connection.createChannel();//创建信道
		channel.queueDeclare(queue_name, durable, false, false, null); //声明消息队列，且为可持久化的

		Message message = new Message(UUID.randomUUID().toString(),"hello!");

		//将队列设置为持久化之后，还需要将消息也设为可持久化的，MessageProperties.PERSISTENT_TEXT_PLAIN
		channel.basicPublish("", queue_name, MessageProperties.PERSISTENT_TEXT_PLAIN, JSONObject.toJSON(message).toString().getBytes());

		System.out.println("Send message:" + JSONObject.toJSON(message));
		channel.close();
		connection.close();
	}

}
