package com.maxlong.study.redis;

import com.maxlong.study.serializable.UserInfo;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.time.Instant;


public class SpringRedisTest {

    public static void main(String[] args) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();

        JedisClientConfiguration jedisClientConfiguration = JedisClientConfiguration.builder().build();
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration("192.168.128.128", 6379);
        JedisConnectionFactory conFactory = new JedisConnectionFactory(redisStandaloneConfiguration, jedisClientConfiguration);
        conFactory.afterPropertiesSet();
        redisTemplate.setKeySerializer(StringRedisSerializer.UTF_8);
        redisTemplate.setConnectionFactory(conFactory);
        redisTemplate.afterPropertiesSet();
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        Instant start = Instant.now();
        for (int i = 0; i < 100; i++) {
            operations.set("userInfo", new UserInfo("1000", "xxxxxx"));
        }
        System.out.println("100个序列化耗费时间: " + Duration.between(start, Instant.now()).toMillis());
    }
}
 