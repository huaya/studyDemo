package com.maxlong.config;

import com.maxlong.study.utils.ReflectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;
import java.util.Optional;

/**
 * @describe：
 * @author： ma.xl
 * @datetime： 2019-3-20 16:11
 */
@Configuration
public class RedisClusterConfig {

    @Autowired
    private RedisProperties redisProperties;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(redisProperties.getMaxTotal());
        poolConfig.setMaxIdle(redisProperties.getMaxIdle());
        poolConfig.setNumTestsPerEvictionRun(redisProperties.getNumTestsPerEvictionRun());
        poolConfig.setTimeBetweenEvictionRunsMillis(redisProperties.getTimeBetweenEvictionRunsMillis());
        poolConfig.setMinEvictableIdleTimeMillis(redisProperties.getMinEvictableIdleTimeMillis());
        poolConfig.setSoftMinEvictableIdleTimeMillis(redisProperties.getSoftMinEvictableIdleTimeMillis());
        poolConfig.setMaxWaitMillis(redisProperties.getMaxWaitMillis());
        poolConfig.setTestOnBorrow(redisProperties.isTestOnBorrow());
        poolConfig.setTestOnCreate(redisProperties.isTestOnCreate());
        poolConfig.setTestOnReturn(redisProperties.isTestOnReturn());
        poolConfig.setTestWhileIdle(redisProperties.isTestWhileIdle());
        poolConfig.setBlockWhenExhausted(redisProperties.isBlockWhenExhausted());

        if (redisProperties.isClusterMode()) {
            return new JedisConnectionFactory(new RedisClusterConfiguration(redisProperties.getNodes()), poolConfig);
        } else {
            RedisStandaloneConfiguration standaloneConfiguration =
                    new RedisStandaloneConfiguration(redisProperties.getHost(), redisProperties.getPort());
            standaloneConfiguration.setPassword(redisProperties.getPassword());

            JedisClientConfiguration jedisClientConfiguration =
                    JedisClientConfiguration.builder().usePooling().poolConfig(poolConfig).build();

            return new JedisConnectionFactory(standaloneConfiguration, jedisClientConfiguration);
        }
    }

    @Bean
    public <T> RedisTemplate<String, T> redisTemplate() {
        final RedisTemplate<String, T> redisTemplate = new RedisTemplate<>();
        this.init(redisTemplate, redisConnectionFactory());
        return redisTemplate;
    }

    private <T> void init(RedisTemplate<String, T> redisTemplate, RedisConnectionFactory factory) {
        RedisSerializer keySerializer = null;
        Class keySerializerClass = ReflectUtil.loadClass(redisProperties.getKeySerializer());
        if(keySerializerClass != null && RedisSerializer.class.isAssignableFrom(keySerializerClass)){
            keySerializer = (RedisSerializer) ReflectUtil.newInstance(keySerializerClass);
        }

        RedisSerializer hashKeySerializer = null;
        Class hashKeySerializerClass = ReflectUtil.loadClass(redisProperties.getKeySerializer());
        if(hashKeySerializerClass != null && RedisSerializer.class.isAssignableFrom(hashKeySerializerClass)){
            hashKeySerializer = (RedisSerializer) ReflectUtil.newInstance(hashKeySerializerClass);
        }


        RedisSerializer valueSerializer = null;
        Class valueSerializerClass = ReflectUtil.loadClass(redisProperties.getKeySerializer());
        if(valueSerializerClass != null && RedisSerializer.class.isAssignableFrom(valueSerializerClass)){
            valueSerializer = (RedisSerializer) ReflectUtil.newInstance(valueSerializerClass);
        }

        RedisSerializer hashValueSerializer = null;
        Class hashValueSerializerClass = ReflectUtil.loadClass(redisProperties.getKeySerializer());
        if(hashValueSerializerClass != null && RedisSerializer.class.isAssignableFrom(hashValueSerializerClass)){
            hashValueSerializer = (RedisSerializer) ReflectUtil.newInstance(hashValueSerializerClass);
        }

        redisTemplate.setKeySerializer(Optional.ofNullable(keySerializer).orElse(StringRedisSerializer.UTF_8));
        redisTemplate.setHashKeySerializer(Optional.ofNullable(hashKeySerializer).orElse(StringRedisSerializer.UTF_8));
        redisTemplate.setValueSerializer(Optional.ofNullable(valueSerializer).orElseGet(JdkSerializationRedisSerializer :: new));
        redisTemplate.setHashValueSerializer(Optional.ofNullable(hashValueSerializer).orElseGet(JdkSerializationRedisSerializer::new));

        redisTemplate.setEnableTransactionSupport(true);
        redisTemplate.setConnectionFactory(factory);
    }

}
