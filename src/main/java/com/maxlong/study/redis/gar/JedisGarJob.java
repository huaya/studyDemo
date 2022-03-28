package com.maxlong.study.redis.gar;

import com.maxlong.study.redis.RedisPool;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import redis.clients.jedis.Jedis;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created on 2022/3/25.
 *
 * @author xxxx
 * @Email xxxx
 */
public class JedisGarJob extends GarJob {

    public JedisGarJob(String waitKey, String onKey) {
        super(waitKey, onKey);
    }

    public Set<Long> getAndMove() throws Exception {
        try (Jedis jedis = RedisPool.getJedisPool().getResource()) {
            Set<String> members = jedis.zrange(waitKey, 0, 5);

            Thread.sleep(RandomUtils.nextInt(100, 5000));
            jedis.sadd(onKey, members.toArray(new String[members.size()]));
            jedis.zrem(waitKey, members.toArray(new String[members.size()]));

            return members.stream()
                    .filter(item -> StringUtils.isNotBlank(item) && NumberUtils.isDigits(item))
                    .map(item -> Long.valueOf(item)).collect(Collectors.toSet());
        }
    }

}
