package com.maxlong.study.io.server;

import com.maxlong.study.io.server.nio.MultiplexerTimeServer;

/**
* @author 作者:maxlong
* @version 创建时间：2016年6月5日 上午11:25:21
* 类说明
*/
public class TimeNIOServer {

	private static final int port = 7002;

    public static void main(String[] args) {
		MultiplexerTimeServer timeServer = new MultiplexerTimeServer(port);
		new Thread(timeServer, "NIO-MultiplexerTimeServer-001").start();
    }
}

