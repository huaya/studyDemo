package com.maxlong.study.consistenthash;

import com.google.common.collect.Sets;
import org.junit.Test;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.support.SpringFactoriesLoader;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @describe：spring源码阅读测试
 * @author： ma.xl
 * @datetime： 2019-3-25 15:34
 */
public class SpringResourceTest {

    @Test
    public void applicationContextInitializer(){
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Set<String> names = new LinkedHashSet<String>(
                SpringFactoriesLoader.loadFactoryNames(ApplicationContextInitializer.class, classLoader));
        names.forEach(name -> System.out.println(name));
    }

    @Test
    public void applicationListener(){
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Set<String> names = new LinkedHashSet<String>(
                SpringFactoriesLoader.loadFactoryNames(ApplicationListener.class, classLoader));
        names.forEach(name -> System.out.println(name));
    }

    @Test
    public void deduceMainApplicationClass() throws ClassNotFoundException {
        Set<Class> classes = Sets.newHashSet();
        StackTraceElement[] stackTrace = new RuntimeException().getStackTrace();
        for (StackTraceElement stackTraceElement : stackTrace) {
            if ("main".equals(stackTraceElement.getMethodName())) {
                Class<?> clazz = Class.forName(stackTraceElement.getClassName());
                classes.add(clazz);
            }
        }
        classes.forEach(clazz -> System.out.println(clazz.getName()));
    }




}
