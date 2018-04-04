package com.maxlong.spring.factory;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/** 
* @author ���� maxlong: 
* @version ����ʱ�䣺2016��6��23�� ����5:02:27 
* ��˵�� 
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
	
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String beanName) {  
        if (null != context) {  
            return (T) context.getBean(beanName);  
        }  
        return null;  
    }
	
}
 