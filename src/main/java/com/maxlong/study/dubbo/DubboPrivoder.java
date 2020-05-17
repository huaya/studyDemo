package com.maxlong.study.dubbo;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;

import java.io.IOException;

/**
 * @describe：
 * @author： ma.xl
 * @datetime： 2019-4-11 10:44
 */
public class DubboPrivoder {

    public static void main(String[] args) throws IOException {
        ServiceConfig<GreetingService> serviceConfig = new ServiceConfig<>();
        serviceConfig.setApplication(new ApplicationConfig("first-dubbo-provider"));
        serviceConfig.setRegistry(new RegistryConfig("zookeeper://192.168.128.128:2181"));
        serviceConfig.setInterface(GreetingService.class);
        serviceConfig.setRef(new GreetingService1Impl());
        serviceConfig.export();
        System.in.read();
    }
}
