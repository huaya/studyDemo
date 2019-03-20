package com.maxlong.study.singleton;

/**
 * Created by IntelliJ IDEA.
 *
 * @author 作者: maxlong E-mail: hu5624548@163.com
 * @version 创建时间：2018/7/5 13:28
 * 类说明: 单例推荐写法，既满足延迟加载，有不会有性能上的损耗
 */
public class InnerSingleton {

    private InnerSingleton() { }

    private static class InnerSingletonHolder {
        private static InnerSingleton instance = new InnerSingleton();
    }

    public static InnerSingleton getInstance(){
        return InnerSingletonHolder.instance;
    }
}
