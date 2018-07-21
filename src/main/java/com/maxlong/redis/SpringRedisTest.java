package com.maxlong.redis;

import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.JedisPoolConfig;

import java.io.UnsupportedEncodingException;

/** 
* @author ���� maxlong: 
* @version ����ʱ�䣺2016��6��28�� ����2:17:40 
* ��˵�� 
*/
public class SpringRedisTest {
	public static void main(String[] args) {
		final String redisCode = "utf-8";
		RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
		JedisConnectionFactory conFactory = new JedisConnectionFactory();
		conFactory.setHostName("127.0.0.1");
		conFactory.setPort(6379);
		JedisPoolConfig jedisConfig = new JedisPoolConfig();
		jedisConfig.setMaxIdle(10);
		jedisConfig.setMaxTotal(50);
		jedisConfig.setTimeBetweenEvictionRunsMillis(30000);
		jedisConfig.setMinEvictableIdleTimeMillis(30000);
		jedisConfig.setTestOnBorrow(true);
		conFactory.setPoolConfig(jedisConfig);
		conFactory.afterPropertiesSet();
		redisTemplate.setConnectionFactory(conFactory);
		redisTemplate.afterPropertiesSet();
		String sdsd = redisTemplate.execute((RedisCallback<String>) conn -> {
			try {
				return new String(conn.get("w3ckey".getBytes()),redisCode);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return "";
		});
		System.out.println(sdsd);
	}
}
 