package com.maxlong.camel;

import lombok.extern.log4j.Log4j2;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
/**
 * @author 作者 maxlong:
 * @version 创建时间：2016年6月24日 上午10:01:48
 * 类说明
 */
@Log4j2
public class TimerMain {

	public static void main(String[] args) throws Exception {
		new TimerMain().run();
	}

	void run() throws Exception {
		final CamelContext camelContext = new DefaultCamelContext();
		camelContext.addRoutes(createRouteBuilder());
		camelContext.setTracing(true);
		camelContext.start();

		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			try {
				camelContext.stop();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}));

		waitForStop();
	}

	RouteBuilder createRouteBuilder() {
		return new TimerRouteBuilder();
	}

	void waitForStop() {
		while (true) {
			try {
				Thread.sleep(Long.MAX_VALUE);
			} catch (InterruptedException e) {
				break;
			}
		}
	}
}
 