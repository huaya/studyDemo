package com.maxlong.camel.route.api;

/** 
* @author ���� maxlong: 
* @version ����ʱ�䣺2016��6��23�� ����4:18:38 
* ��˵�� 
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
 