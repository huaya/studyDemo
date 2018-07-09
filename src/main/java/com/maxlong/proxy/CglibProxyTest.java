package com.maxlong.proxy;


import net.sf.cglib.proxy.Enhancer;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * Created by IntelliJ IDEA.
 *
 * @author 作者: maxlong E-mail: hu5624548@163.com
 * @version 创建时间：2018/6/19 22:32
 * 类说明:
 */
public class CglibProxyTest {

    public static void main(String[] args) {
        CglibProxy cglibProxy = new CglibProxy();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserServiceImpl.class);
        enhancer.setCallback(cglibProxy);
        UserService userServiceProxy = (UserService) enhancer.create();

        System.out.println("interfaces : ");
        Class<?>[] interFaces = userServiceProxy.getClass().getInterfaces();
        for(Class<?> clazz : interFaces){
            System.out.println(clazz.getName());
        }

        System.out.println("fields : ");
        Field[] fields = userServiceProxy.getClass().getDeclaredFields();
        for(Field field : fields){
            System.out.println(field.getName());
        }

        System.out.println("methods : ");
        Method[] methods = userServiceProxy.getClass().getDeclaredMethods();
        for(Method method : methods){
            System.out.println(method.getName());
        }

        System.out.println("interface types : ");
        Type[] types = userServiceProxy.getClass().getGenericInterfaces();
        for(Type type : types){
            System.out.println(type.getTypeName());
        }

        System.out.println("superclass type :");
        Type type = userServiceProxy.getClass().getGenericSuperclass();
        System.out.println(type.getTypeName());

        System.out.println(userServiceProxy.getName(1));
        System.out.println(userServiceProxy);
        System.out.println(userServiceProxy.getAge(1));
    }
}
