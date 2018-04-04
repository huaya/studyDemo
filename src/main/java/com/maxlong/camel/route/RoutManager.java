package com.maxlong.camel.route;

import com.maxlong.camel.route.api.Route;

/** 
* @author 作者 maxlong: 
* @version 创建时间：2016年6月24日 下午5:07:27 
* 类说明 
*/
public class RoutManager {
	public static RoutManager routManager;
	
	public static Route route;
	
	public static RoutManager getInstance(){
		if(routManager==null){
			synchronized(RoutManager.class){
                if(routManager==null){
                	routManager=new RoutManager();
                }
            }
		}
		return routManager;
	}
	
	public void setRoute(Route route){
		RoutManager.route = route;  
	}
}
 