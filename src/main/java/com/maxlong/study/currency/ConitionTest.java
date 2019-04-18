package com.maxlong.study.currency;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition中的await()方法相当于Object的wait()方法，
 * Condition中的signal()方法相当于Object的notify()方法，
 * Condition中的signalAll()相当于Object的notifyAll()方法。
 * 不同的是，Object中的wait(),notify(),notifyAll()方法是和"同步锁"(synchronized关键字)捆绑使用的；
 * 而Condition是需要与"互斥锁"/"共享锁"捆绑使用的
 *
 * @author 作者 maxlong:
 * @version 创建时间：2016年6月29日 下午1:58:33
 * 类说明
 */
public class ConitionTest {

	public static void main(String[] args) {
		Lock lock = new ReentrantLock();
		Condition finishCondition = lock.newCondition();

		try {
			lock.lock();
			try {
				finishCondition.await();
			} finally {
				lock.unlock();
			}
		} catch (InterruptedException e) {
			e.getStackTrace();
		}
	}

}
 