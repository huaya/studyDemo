package com.maxlong.study.io.server;

import com.maxlong.study.io.server.hander.TimeServerHander;
import lombok.extern.slf4j.Slf4j;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
* @author 作者:maxlong
* @version 创建时间：2016年6月4日 下午1:26:45
* 类说明:伪异步IO,对BIO的优化，通过线程池管理线程，不用每个接入者都会创建一个线程，不会导致线程数过于膨胀或内存溢出
* 由于依然是阻塞式，不能解决流阻塞引起的连接超时问题
*/
@Slf4j
public class TimeFIOServer {

	private static final int port = 7002;
	private static ExecutorService service = Executors.newCachedThreadPool();

	public static void main(String[] args) {
		
		try (ServerSocket server = new ServerSocket(port)){
			log.info("The time server is start in port:" + port);

			while(true){
				Socket socket = server.accept();
				service.execute(new TimeServerHander(socket));
			}
		} catch (Exception e) {
			log.error("异常", e);
		}
	}
	
}
