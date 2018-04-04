package com.maxlong.camel.bootstrap;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.maxlong.camel.route.RoutManager;
import com.maxlong.camel.route.ServiceCamelRoute;
import com.maxlong.camel.route.api.Route;
import com.maxlong.spring.factory.Springfactory;
import com.maxlong.thrift.config.ProductServerConfig;
import com.maxlong.thrift.service.ThriftShareService;

/** 
* @author ���� maxlong: 
* @version ����ʱ�䣺2016��6��23�� ����4:08:41 
* ��˵�� 
*/
public class Bootstrap {
	
	public static ClassPathXmlApplicationContext context;
	
	public static void main(String[] args) {
		context = new ClassPathXmlApplicationContext("appclitionContext.xml");
		context.start();
		Springfactory.setContext(context);
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
	}
}
 