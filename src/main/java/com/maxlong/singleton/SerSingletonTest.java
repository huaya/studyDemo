package com.maxlong.singleton;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author 作者 maxlong:
 * @version 创建时间：2016年7月28日 下午5:03:23
 * 类说明
 */
public class SerSingletonTest{

	@Test
	public void test() throws Exception {
		SerSingleton s1 = null;
		SerSingleton s = SerSingleton.getInstance();
		//先将实例串行化到文件
		FileOutputStream fos = new FileOutputStream("SerSingleton.txt");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(s);
		oos.flush();
		oos.close();
		//从文件读出原有的单例类
		FileInputStream fis = new FileInputStream("SerSingleton.txt");
		ObjectInputStream ois = new ObjectInputStream(fis);
		s1 = (SerSingleton) ois.readObject();
	}

	@Test
	public void test_1(){

		InnerSingleton singleton1 = InnerSingleton.getInstance();
		InnerSingleton singleton2 = InnerSingleton.getInstance();
		System.out.println(singleton1==singleton2);
	}
}