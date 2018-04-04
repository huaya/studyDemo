package com.maxlong.camel; 

import org.slf4j.*;
import org.apache.camel.*;
import org.apache.camel.builder.*;
/** 
* @author ���� maxlong: 
* @version ����ʱ�䣺2016��6��24�� ����9:44:31 
* ��˵�� 
*/

 
public class TimerRouteBuilder extends RouteBuilder {
	static Logger LOG = LoggerFactory.getLogger(TimerRouteBuilder.class);
	public void configure() {
		from("timer://timer1?period=1000")
		.process(new Processor() {
			public void process(Exchange msg) {
				System.out.println("Processing {}" + msg);
			}
		});
	}
}
 