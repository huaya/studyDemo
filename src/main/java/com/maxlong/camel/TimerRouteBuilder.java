package com.maxlong.camel;

import lombok.extern.log4j.Log4j2;
import org.slf4j.*;
import org.apache.camel.*;
import org.apache.camel.builder.*;
/**
 * @author 作者 maxlong:
 * @version 创建时间：2016年6月24日 上午9:44:31
 * 类说明
 */

@Log4j2
public class TimerRouteBuilder extends RouteBuilder {

	public void configure() {
		from("timer://timer1?period=1000")
				.process(msg -> log.info("Processing {}", msg));
	}

}
 