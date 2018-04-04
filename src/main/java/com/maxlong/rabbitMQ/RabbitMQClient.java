package com.maxlong.rabbitMQ;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

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

	public static final String queue_name="my_queue";
	public static final boolean durable=true; //消息队列持久化

	public static void main(String[] argv)throws java.io.IOException, TimeoutException {

		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("127.0.0.1");
		Connection connection=factory.newConnection(); //创建连接
		Channel channel=connection.createChannel();//创建信道
		channel.queueDeclare(queue_name, durable, false, false, null); //声明消息队列，且为可持久化的
		String message="Hello world"+Math.random();
		//将队列设置为持久化之后，还需要将消息也设为可持久化的，MessageProperties.PERSISTENT_TEXT_PLAIN
		channel.basicPublish("", queue_name, MessageProperties.PERSISTENT_TEXT_PLAIN,message.getBytes());
		System.out.println("Send message:"+message);
		channel.close();
		connection.close();
	}

}
