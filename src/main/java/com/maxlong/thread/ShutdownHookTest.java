package com.maxlong.thread;
/**
 * @author 作者 maxlong:
 * @version 创建时间：2016年6月27日 下午1:45:22
 * 类说明
 */
public class ShutdownHookTest {
	public static void main(String[] args) {
		Runnable t1 = new Runnable() {
			@Override
			public void run() {
				System.out.println("This is thread 1;");
			}
		};

		Runnable t2 = new Runnable() {
			@Override
			public void run() {
				System.out.println("This is thread 2;");
			}
		};

		Runnable t3= new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("This is thread 3;");
			}
		};

		Thread thread1 = new Thread(t1);
		Thread thread2 = new Thread(t2);
		Thread thread3 = new Thread(t3);

		Runtime.getRuntime().addShutdownHook(thread3);
		thread1.start();
		thread2.start();
		thread3.start();

	}
}
 