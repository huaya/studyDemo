package com.maxlong.study.io.server.aio;

import java.io.IOException;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.CountDownLatch;

/**
* @author 作者:maxlong
* @version 创建时间：2016年6月4日 下午12:24:45
* 类说明
*/
public class AsyncTimeServerHander implements Runnable {

	private int port;
	
	private CountDownLatch latch;
	
	private  AsynchronousServerSocketChannel asynchronousServerSocketChannel;


	public AsyncTimeServerHander(int port) throws IOException {
		asynchronousServerSocketChannel = AsynchronousServerSocketChannel.open();
	}

	@Override
	public void run() {
	}

}
