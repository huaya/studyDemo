package com.maxlong.study.redis;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.redisson.config.Config;

/**
 * Created on 2022/3/25.
 *
 * @author xxxx
 * @Email xxxx
 */
public class RedssionClientHolder {

    public static final RedissonClient redisson;
    static {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://127.0.0.1:6379");
        config.setCodec(StringCodec.INSTANCE);
        redisson = Redisson.create(config);
    }
}
