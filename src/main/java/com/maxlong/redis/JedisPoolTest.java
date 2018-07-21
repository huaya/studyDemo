package com.maxlong.redis;

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
public class JedisPoolTest {

    public static void main(String[] args) {

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(10);
        jedisPoolConfig.setMaxTotal(50);
        jedisPoolConfig.setMaxWaitMillis(1000);

        JedisPool jedisPool = new JedisPool(jedisPoolConfig, "127.0.0.1", 6379);

        for (int i = 0; i < 50; i++) {
            Jedis jedis = null;
            try {
                jedis = jedisPool.getResource();
                jedis.ping();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (jedis != null) {
                    jedis.close();
                }
            }
        }

        jedisPool.getResource().ping();

    }
}
