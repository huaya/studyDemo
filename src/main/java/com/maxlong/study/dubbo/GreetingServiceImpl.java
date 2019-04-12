package com.maxlong.study.dubbo;

import lombok.extern.slf4j.Slf4j;

/**
 * @describe：
 * @author： ma.xl
 * @datetime： 2019-4-11 10:42
 */
@Slf4j
public class GreetingServiceImpl implements GreetingService {

    @Override
    public String sayHello() {
        return "hello world!";
    }
}
