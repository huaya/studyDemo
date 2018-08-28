package com.maxlong.spring.factory;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author 作者 maxlong:
 * @version 创建时间：2016年6月23日 下午5:02:27
 * 类说明
 */
public class Springfactory{

	private ClassPathXmlApplicationContext context;

	private Springfactory(){}

	private static class SpringfactoryHolder{
		public static Springfactory springfactory = new Springfactory();
	}

	public static Springfactory getInstance(){
		return SpringfactoryHolder.springfactory;
	}

	public void setContext(ClassPathXmlApplicationContext context){
		this.context = context;
	}

	public ClassPathXmlApplicationContext getContext() {
		return context;
	}

	public <T> T getBean(String beanName) {
		if (null != context) {
			return (T) context.getBean(beanName);
		}
		return null;
	}

}
 