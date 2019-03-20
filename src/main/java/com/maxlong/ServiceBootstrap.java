package com.maxlong;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.maxlong.study.camel.route.RoutManager;
import com.maxlong.study.camel.route.ServiceCamelRoute;
import com.maxlong.study.camel.route.api.Route;
import com.maxlong.study.spring.factory.Springfactory;
import com.maxlong.study.thrift.config.ProductServerConfig;
import com.maxlong.study.thrift.service.ThriftShareService;

/**
 * @author 作者 maxlong:
 * @version 创建时间：2016年6月23日 下午4:08:41
 * 类说明
 */
@Log4j2
public class ServiceBootstrap {

    public static void main(String[] args) {
        log.info("starting camel service.");
        try {
            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("appclitionContext.xml");
            context.start();

            Springfactory.getInstance().setContext(context);
            Route route = new ServiceCamelRoute();
            route.start();
            RoutManager.getInstance().setRoute(route);

            ThriftShareService thriftShareService = new ThriftShareService();
            ProductServerConfig config = new ProductServerConfig();
            config.setPort(5568);
            config.setSelectorthreads(2);
            config.setWorkthreads(5);
            config.setAcceptqueuesizeterthread(100);
            config.setFixedThreadPoolSize(100);
            thriftShareService.start(config);
            log.info("start camel service success.");
        } catch (Exception e){
            e.printStackTrace();
            log.info("start camel service fail.");
        }

    }
}
 