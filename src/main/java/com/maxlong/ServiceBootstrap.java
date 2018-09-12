package com.maxlong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.maxlong.camel.route.RoutManager;
import com.maxlong.camel.route.ServiceCamelRoute;
import com.maxlong.camel.route.api.Route;
import com.maxlong.spring.factory.Springfactory;
import com.maxlong.thrift.config.ProductServerConfig;
import com.maxlong.thrift.service.ThriftShareService;

/**
 * @author 作者 maxlong:
 * @version 创建时间：2016年6月23日 下午4:08:41
 * 类说明
 */
public class ServiceBootstrap {

    private static final Logger logger = LoggerFactory.getLogger(ServiceBootstrap.class);

    public static void main(String[] args) {
        logger.info("starting camel service.");
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
            logger.info("start camel service success.");
        } catch (Exception e){
            e.printStackTrace();
            logger.info("start camel service fail.");
        }

    }
}
 