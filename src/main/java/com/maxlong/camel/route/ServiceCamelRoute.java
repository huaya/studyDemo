package com.maxlong.camel.route;

import org.apache.camel.builder.NoErrorHandlerBuilder;
import org.apache.camel.builder.RouteBuilder;
import com.maxlong.spring.factory.Springfactory;

/** 
* @author ���� maxlong: 
* @version ����ʱ�䣺2016��6��23�� ����4:46:04 
* ��˵�� 
*/
public class ServiceCamelRoute extends CamelRoute {

	@Override
	public RouteBuilder getRouteBuilder() {
		return new RouteBuilder() {

			@Override
			public void configure() throws Exception {
				 Object bean = getBean("com.maxlong.camel.CamelTestService");
				 String route = "direct://maxlong_camelTestService";
				 from(route)
		            .bean(bean)
		            .setErrorHandlerBuilder(new NoErrorHandlerBuilder());
			}

			private Object getBean(String string) {
				return Springfactory.getBean(string);
			}
			
		};
	}

}
 