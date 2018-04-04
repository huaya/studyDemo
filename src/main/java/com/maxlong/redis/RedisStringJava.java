package com.maxlong.redis;

import java.util.Iterator;
import java.util.Set;

import redis.clients.jedis.Jedis;

/**
 * @author 作者 maxlong:
 * @version 创建时间：2016年6月28日 下午2:02:24
 * 类说明
 */
public class RedisStringJava {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Jedis jedis = new Jedis("172.28.250.122");
		System.out.println("Server is running: "+jedis.ping());
		jedis.set("redie", "sfsdsd");
		Set set = jedis.keys("*");
		System.out.println(set.size());
		Iterator it = set.iterator();
		while(it.hasNext()){
			String key = (String) it.next();
			String value = jedis.get(key);
			System.out.println("key: " + key + ", value: " + value);
		}
	}

}
 