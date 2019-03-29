package com.maxlong.study.currency;
/**
 * @author 作者 maxlong:
 * @version 创建时间：2016年6月27日 下午1:45:22
 * 类说明
 */
public class ShutdownHookTest {
	public static void main(String[] args) {
		Thread thread1 = new Thread(() -> System.out.println("This is thread 1;"));
		Thread thread2 = new Thread(() -> System.out.println("This is thread 2;"));
		Thread shutdownThread = new Thread(() -> {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("This is shutdownThread;");
		});

		//无论是先打印thread1还是thread2，shutdownThread 线程都是最后执行的（因为这个线程是在jvm执行关闭前才会执行）。
		Runtime.getRuntime().addShutdownHook(shutdownThread);
		thread1.start();
		thread2.start();

	}
}
 