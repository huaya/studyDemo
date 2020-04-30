package com.maxlong.study.dubbo;

import java.util.ServiceLoader;

/**
 * Created on 2020/4/30.
 *
 * @author MaXiaolong
 * @Email hu5624548@163.com
 */
public class ServiceLoadTest {

    public static void main(String[] args) {
        ServiceLoader<GreetingService> serviceLoader = ServiceLoader.load(GreetingService.class);
        serviceLoader.forEach(service -> System.out.println(service.sayHello()));
    }
}
