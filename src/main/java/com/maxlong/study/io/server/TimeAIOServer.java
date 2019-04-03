package com.maxlong.study.io.server;

import com.maxlong.study.io.server.hander.AsyncTimeServerHander;

import java.io.IOException;

/**
* @author 作者 :maxlong
* @version 创建时间：2016年6月4日 下午12:09:48
* 类说明:传统AIO(同步阻塞IO)
* 、高并发的场景
*/
public class TimeAIOServer {

	private static final int port = 7002;

	public static void main(String[] args) throws IOException {
		int port = 7002;
		if(args!=null && args.length>0){
			port = Integer.valueOf(args[0]);
		}
		
		AsyncTimeServerHander timeServer = new AsyncTimeServerHander(port);
		new Thread(timeServer, "NIO-MultiplexerTimeServer-001").start();
    }

}
