package com.maxlong.study.io.server.aio;

import java.io.IOException;

/**
* @author 作者 :maxlong
* @version 创建时间：2016年6月4日 下午12:09:48
* 类说明:传统BIO(异步阻塞IO)
* BIO特点：每当一个新用户接入时都会新建一个线程接入客户端链路，一个线程只能处理一个客户端
* 连接，在高性能服务器领域，择需要成千上万的客户端并发连接，这种模型无法满足高性能
* 、高并发的场景
*/
public class TimeServer {

	public static void main(String[] args) throws IOException {
		int port = 7002;
		if(args!=null && args.length>0){
			port = Integer.valueOf(args[0]);
		}
		
		AsyncTimeServerHander timeServer = new AsyncTimeServerHander(port);
		new Thread(timeServer, "NIO-MultiplexerTimeServer-001").start();
    }

}
