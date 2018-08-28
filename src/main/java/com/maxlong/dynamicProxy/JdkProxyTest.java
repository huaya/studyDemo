package com.maxlong.dynamicProxy;

import com.maxlong.service.UserService;
import com.maxlong.service.impl.UserServiceImpl;

import java.lang.reflect.*;

/**
 * Created by IntelliJ IDEA.
 *
 * @author 作者: maxlong E-mail: hu5624548@163.com
 * @version 创建时间：2018/6/19 22:00
 * 类说明:
 */
public class JdkProxyTest {

    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        InvocationHandler invocationHandler = new MyInvocationHandler(userService);
        UserService userServiceProxy = (UserService)Proxy.newProxyInstance(userService.getClass().getClassLoader(),
                userService.getClass().getInterfaces(), invocationHandler);

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
