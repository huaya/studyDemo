package com.maxlong.algorithm;

import java.time.Duration;
import java.time.Instant;
import java.util.OptionalLong;
import java.util.stream.LongStream;

/**
 * @describe：
 * @author： ma.xl
 * @datetime： 2018-10-22 10:57
 */
public class StreamApi {

    public static void main(String[] args) {
        addByFor();
        addByStream();
    }


    public static void addByFor(){
        Instant start = Instant.now();
        long sum = 0;
        for (long i = 0; i <= 50000000000L; i++) {
            sum += i;
        }
        System.out.println(sum);
        Instant end = Instant.now();
        System.out.println("十亿求和花费的时间为: " + Duration.between(start, end).toMillis());
    }

    public static void addByStream(){
        Instant start = Instant.now();
        //使用StreamAPI
        OptionalLong result = LongStream.rangeClosed(0L, 50000000000L).parallel().reduce(Long::sum);
        System.out.println(result.getAsLong());
        Instant end = Instant.now();
        System.out.println("十亿求和耗费的时间为: " + Duration.between(start, end).toMillis());
    }
}
