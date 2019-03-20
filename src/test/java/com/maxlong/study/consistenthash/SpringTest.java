package com.maxlong.study.consistenthash;

import com.maxlong.SpringBootBootstrap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @describe：
 * @author： ma.xl
 * @datetime： 2019-3-20 15:18
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBootBootstrap.class)
public class SpringTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testRedis(){
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        operations.set("test", "xxxxxxxxx");
//        String aaa = operations.get("malxong");
//        System.out.println(aaa);
    }

}
