package com.maxlong.study.currency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 作者 maxlong:
 * @version 创建时间：2016年7月6日 下午2:29:19
 * 类说明
 */
public class ExecutorServiceTest {
	public static void main(String[] args) {
		ExecutorService Service =  Executors.newCachedThreadPool(r -> {
			Thread t = new Thread(r);
			t.setDaemon(false);
			return t;
		});
		Service.execute(() -> System.out.println("1122"));
	}
}
 