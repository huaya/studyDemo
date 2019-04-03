package com.maxlong.study.io.client;

import com.maxlong.study.io.client.hander.TimeClientHandle;

/**
* @author 作者:maxlong
* @version 创建时间：2016年6月4日 下午12:51:19
* 类说明
*/
public class TimeNIOClient {

	private static final int port = 7002;

	public static void main(String[] args) {
		TimeClientHandle timeClientHandle = new TimeClientHandle("127.0.0.1", port);
	    new Thread(timeClientHandle, "NIO-MultiplexerTimeServer-001").start();
	}
}
