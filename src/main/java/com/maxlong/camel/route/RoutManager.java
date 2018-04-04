package com.maxlong.camel.route;

import com.maxlong.camel.route.api.Route;

/** 
* @author ���� maxlong: 
* @version ����ʱ�䣺2016��6��24�� ����5:07:27 
* ��˵�� 
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
 