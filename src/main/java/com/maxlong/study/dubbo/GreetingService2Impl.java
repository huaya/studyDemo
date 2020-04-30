package com.maxlong.study.dubbo;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;

/**
 * @describe：
 * @author： ma.xl
 * @datetime： 2019-4-11 10:42
 */
@Slf4j
@Service(version = "1.0")
public class GreetingService2Impl implements GreetingService {

    @Override
    public String sayHello() {
        return "hello world! I'm service 2.";
    }
}
