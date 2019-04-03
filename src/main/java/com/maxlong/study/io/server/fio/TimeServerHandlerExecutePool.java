package com.maxlong.study.io.server.fio;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
* @author 作者:maxlong
* @version 创建时间：2016年6月4日 下午1:33:38
* 类说明
*/
public class TimeServerHandlerExecutePool {

	private ExecutorService executor;
	
	public TimeServerHandlerExecutePool(int maxPoolSize, int queueSize) {
		executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), maxPoolSize, 120L, 
				TimeUnit.SECONDS, new ArrayBlockingQueue<>(queueSize));
	}

	public void execute(Runnable task){
		executor.execute(task);
	}	
}
	