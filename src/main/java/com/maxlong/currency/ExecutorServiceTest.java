package com.maxlong.currency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @author 作者 maxlong:
 * @version 创建时间：2016年7月6日 下午2:29:19
 * 类说明
 */
public class ExecutorServiceTest {
	public static void main(String[] args) {
		ExecutorService Service =  Executors.newCachedThreadPool(new ThreadFactory() {
			public Thread newThread(Runnable r) {
				Thread t = new Thread(r);
				t.setDaemon(false);
				return t;
			}
		});

		Service.execute(new Runnable() {
			@Override
			public void run() {
				System.out.println("1122");
			}
		});
	}
}
 