package com.maxlong.study.singleton;

/**
 * Created on 2020/4/10.
 *
 * @author MaXiaolong
 */
public class DobbleCheckSingleton {

    /**
     *  懒汉模式:在类加载的时候不被初始化。
     *  单例-双重校验模式
     */

    private static DobbleCheckSingleton instance;

    public static DobbleCheckSingleton getInstance() {
        if(instance == null) {
            synchronized (DobbleCheckSingleton.class) {
                if(instance == null){
                    instance = new DobbleCheckSingleton();
                }
            }
        }
        return instance;
    }
}
