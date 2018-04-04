package com.maxlong.camel; 

import org.slf4j.*;
import org.apache.camel.*;
import org.apache.camel.builder.*;
/** 
* @author 作者 maxlong: 
* @version 创建时间：2016年6月24日 上午9:44:31 
* 类说明 
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
 