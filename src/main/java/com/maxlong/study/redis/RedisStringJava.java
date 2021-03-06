package com.maxlong.study.redis;

import redis.clients.jedis.Jedis;

import java.util.Iterator;
import java.util.Set;

/**
 * @author 作者 maxlong:
 * @version 创建时间：2016年6月28日 下午2:02:24
 * 类说明
 */
public class RedisStringJava {

	public static void main(String[] args) {
		Jedis jedis = new Jedis("192.168.128.128", 6379);
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
 