package com.maxlong.study.singleton;

/**
 * Created by IntelliJ IDEA.
 *
 * @author 作者: maxlong E-mail: hu5624548@163.com
 * @version 创建时间：2018/7/5 13:44
 * 类说明:
 */
public class Singleton {

    private Singleton() {}

    private static Singleton instance = new Singleton();

    public static Singleton getInstance(){
        return instance;
    }
}
