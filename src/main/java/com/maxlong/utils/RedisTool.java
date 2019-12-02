package com.maxlong.utils;

import com.google.common.collect.Lists;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Created on 2019/9/17.
 *
 * @author MaXiaolong
 */
@Log4j2
@Component
public class RedisTool {

    private static final String SUCCESS = "1";

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 加锁
     * @param lockKey 锁
     * @param requestId 请求标识
     * @param expireTime 超期时间
     * @param timeUnit 时间单位
     * @return
     */
    public boolean lock(String lockKey, String requestId, int expireTime, TimeUnit timeUnit) {
        return redisTemplate.opsForValue().setIfAbsent(lockKey, requestId, expireTime, timeUnit);
    }

    /**
     * 解锁
     * @param lockKey
     * @param requestId
     * @return
     */
    public boolean unlock(String lockKey, String requestId) {
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        DefaultRedisScript redisScript = new DefaultRedisScript();
        redisScript.setResultType(String.class);
        redisScript.setScriptText(script);
        Object result = redisTemplate.execute(redisScript, Lists.newArrayList(lockKey), requestId);
        return result.toString().equals(SUCCESS);
    }

    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }
}
