package com.maxlong.study.camel.route;

import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.bean.ProxyHelper;
import org.apache.camel.impl.DefaultCamelContext;

import com.maxlong.study.camel.route.api.Route;
import com.maxlong.study.camel.route.api.Rule;

/**
 * @author 作者 maxlong:
 * @version 创建时间：2016年6月23日 下午4:23:40
 * 类说明
 */
public abstract class CamelRoute implements Route{

	private  CamelContext camelContext = new DefaultCamelContext();

	public CamelRoute() {
		this.init();
	}

	@Override
	public void init() {
		try {
			this.camelContext.addRoutes(this.getRouteBuilder());
		} catch (Exception e) {
			//
		}
	}

	public abstract RouteBuilder getRouteBuilder();

	@Override
	public <T> T route(Rule rule, Class<T> clazz) {
		try {
			Endpoint endpoint = camelContext.hasEndpoint(rule.getRule());
			if(endpoint == null){
				return null;
			}
			return ProxyHelper.createProxy(endpoint, clazz);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void start() {
		try {
			this.camelContext.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void stop() {
		try {
			this.camelContext.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
 