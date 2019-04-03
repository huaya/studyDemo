package com.maxlong.study.io.server.nio;

import java.io.IOException;

/**
* @author 作者:maxlong
* @version 创建时间：2016年6月5日 上午11:25:21
* 类说明
*/
public class TimeServer {

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
	int port = 7002;
	if (args != null && args.length < 0) {
	    try {
		port = Integer.valueOf(args[0]);
	    } catch (NumberFormatException e) {
		// 采用默认值
	    }
	}
	MultiplexerTimeServer timeServer = new MultiplexerTimeServer(port);
		new Thread(timeServer, "NIO-MultiplexerTimeServer-001").start();
    }
}

