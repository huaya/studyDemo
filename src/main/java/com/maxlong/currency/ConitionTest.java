package com.maxlong.currency;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
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
 