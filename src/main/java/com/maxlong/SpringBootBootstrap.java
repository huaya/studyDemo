package com.maxlong;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author 作者 maxlong:
 * @version 创建时间：2016年6月23日 下午4:08:41
 * 类说明
 */
@EnableConfigurationProperties
@SpringBootApplication(scanBasePackages = {"com.maxlong"})
public class SpringBootBootstrap {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(SpringBootBootstrap.class);
        application.run();
    }
}
 