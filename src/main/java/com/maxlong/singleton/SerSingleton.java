package com.maxlong.singleton;
/**
 * @author 作者 maxlong:
 * @version 创建时间：2016年7月28日 下午5:02:39
 * 类说明
 */
public class SerSingleton implements java.io.Serializable{

    private static final long serialVersionUID = 1L;

    String name;

    private SerSingleton() {
        System.out.println("Singleton is create");
        //创建单例的过程可能会比较慢
        name="SerSingleton";
    }

    private static SerSingleton instance = new SerSingleton();
    public static SerSingleton getInstance() {
        return instance;
    }

    public static void createString(){
        System.out.println("createString in Singleton");
    }

    private Object readResolve(){       //阻止生成新的实例，总是返回当前对象
        return instance;
    }

}  