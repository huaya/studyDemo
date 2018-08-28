package com.maxlong.spring.factory;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author 作者 maxlong:
 * @version 创建时间：2016年6月23日 下午5:02:27
 * 类说明
 */
public class Springfactory{

	public Springfactory springfactory;

	public static ClassPathXmlApplicationContext context;

	public Springfactory getInstance(){
		if(springfactory==null){
			synchronized(Springfactory.class){
				if(springfactory==null){
					springfactory=new Springfactory();
				}
			}
		}
		return springfactory;
	}

	public static void setContext(ClassPathXmlApplicationContext context){
		Springfactory.context = context;
	}

	public static <T> T getBean(String beanName) {
		if (null != context) {
			return (T) context.getBean(beanName);
		}
		return null;
	}

}
 