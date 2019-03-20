package com.maxlong.study.camel.route;

import com.maxlong.study.camel.route.api.Route;

/**
 * @author 作者 maxlong:
 * @version 创建时间：2016年6月24日 下午5:07:27
 * 类说明
 */
public class RoutManager {

	private RoutManager() {}

	private static Route route;

	private static class RoutManagerHolder{
		public static RoutManager routManager = new RoutManager();
	}
	public static RoutManager getInstance(){
		return RoutManagerHolder.routManager;
	}

	public void setRoute(Route route){
		RoutManager.route = route;
	}

	public Route getRoute() {
		return route;
	}
}
 