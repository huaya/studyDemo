package com.maxlong.study.consistenthash;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @describe：
 * @author： ma.xl
 * @datetime： 2019-3-22 17:55
 */
public class MainTest {

    public static void main(String[] args) {
        ConcurrentHashMap<String, Object> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put("1000", "100");
    }
}
