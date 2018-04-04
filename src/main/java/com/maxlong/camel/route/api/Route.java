package com.maxlong.camel.route.api;

/** 
* @author 作者 maxlong: 
* @version 创建时间：2016年6月23日 下午4:18:38 
* 类说明 
*/
public interface Route {
	
    /**  init the route */
    public void init();

    /**  route a processer bean */
    public <T> T route(Rule rule,Class<T> clazz);

    /**  start the route */
    public void start();

    /**  stop the route */
    public void stop();

}
 