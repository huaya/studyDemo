package com.maxlong.study.redis;

import com.maxlong.study.redis.gar.GarJob;
import com.maxlong.study.redis.gar.JedisGarJob;
import lombok.Data;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created on 2022/3/25.
 *
 * @author xxxx
 * @Email xxxx
 */
@Data
public class JobExecute {

   public static Set<Long> synSet = Collections.synchronizedSet(new HashSet<>());

    public static void main(String[] args) throws Exception {
        GarJob garJob1 = new JedisGarJob("test_get_remove", "test_get_remove_on");
        GarJob garJob2 = new JedisGarJob("test_get_remove", "test_get_remove_on");

        new Thread(garJob1).start();
        new Thread(garJob2).start();
        int cnt = 0;
        while (true) {
            if(cnt%10000 == 0){
                System.out.println("cnt:" + cnt);
            }
            Thread.sleep(5000);
            cnt++;
        }
    }
}
