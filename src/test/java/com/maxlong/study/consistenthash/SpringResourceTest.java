package com.maxlong.study.consistenthash;

import com.google.common.collect.Sets;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.support.SpringFactoriesLoader;

import java.sql.*;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @describe：spring源码阅读测试
 * @author： ma.xl
 * @datetime： 2019-3-25 15:34
 */
@Log4j2
public class SpringResourceTest {


    @Test
    public void dbTest() throws ClassNotFoundException {
        String sql = "UPDATE `tb_worldpay` SET `address1`='🔥aaabbb2' WHERE `id`=369";
        String url = "jdbc:mysql://rm-rj9741o0c47t9xu4e6o.mysql.rds.aliyuncs.com:3306/orderplus_payment?useUnicode=true&characterEncoding=utf8&autoReconnect=true&rewriteBatchedStatements=TRUE";
        String username = "appuser";
        String password = "rTPkaB2!^yy_QBM";
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            connection.setAutoCommit(true);
            PreparedStatement statement = connection.prepareStatement(sql);
            int result = statement.executeUpdate();
            System.out.println(result);
        } catch (Exception e) {
            log.error("连接异常, 原因:", e);
        }
    }

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
