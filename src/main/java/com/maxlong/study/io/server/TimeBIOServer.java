package com.maxlong.study.io.server;

import com.maxlong.study.io.server.hander.TimeServerHander;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

/**
* @author 作者 :maxlong
* @version 创建时间：2016年6月4日 下午12:09:48
* 类说明:传统BIO(异步阻塞IO)
* BIO特点：每当一个新用户接入时都会新建一个线程接入客户端链路，一个线程只能处理一个客户端
* 连接，在高性能服务器领域，择需要成千上万的客户端并发连接，这种模型无法满足高性能
* 高并发的场景
*/
@Slf4j
public class TimeBIOServer {

	private static final int port = 7002;
	private static AtomicInteger THREADID = new AtomicInteger(1);

	public static void main(String[] args) {
		try (ServerSocket server = new ServerSocket(port)){
			log.info("The time server is start in port:{}", port);
			while (true){
				Socket socket = server.accept();
				new Thread(new TimeServerHander(socket), "Thread-" + THREADID.getAndIncrement()).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
