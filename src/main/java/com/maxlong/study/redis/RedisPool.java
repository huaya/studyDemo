package com.maxlong.study.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by IntelliJ IDEA.
 *
 * @author 作者: maxlong E-mail: hu5624548@163.com
 * @version 创建时间：2018/7/9 15:18
 * 类说明:
 */
public class RedisPool {

    private static JedisPool jedisPool;

    static {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(10);
        jedisPoolConfig.setMaxTotal(50);
        jedisPoolConfig.setMaxWaitMillis(1000);
        jedisPool = new JedisPool(jedisPoolConfig, "127.0.0.1", 6379);
    }

    public static String get(String key) {
        return jedisPool.getResource().get(key);
    }

    public static String setEx(String key, String value, int time) {
        return jedisPool.getResource().setex(key, time, value);
    }

    public static void main(String[] args) {
        setEx("maxlong", "1234567890", 60);
        System.out.println(get("maxlong"));
    }
}
