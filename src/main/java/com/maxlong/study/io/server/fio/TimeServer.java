package com.maxlong.study.io.server.fio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
* @author 作者:maxlong
* @version 创建时间：2016年6月4日 下午1:26:45
* 类说明:伪异步IO,对BIO的优化，通过线程池管理线程，不用每个接入者都会创建一个线程，不会导致线程数过于膨胀或内存溢出
* 由于依然是阻塞式，不能解决流阻塞引起的连接超时问题
*/
public class TimeServer {

	public static void main(String[] args) {
		
		int port = 7002;
		if(args != null && args.length>0){
			try {
				port = Integer.valueOf(args[0]);
			} catch (Exception e) {
				System.out.println("port:" + port);
			}
		}
		
		ServerSocket server = null;
		try {
			server = new ServerSocket(port);
			System.out.println("The time server is start in port:" + port);
			Socket socket = null;
			TimeServerHandlerExecutePool singleEcecute = new TimeServerHandlerExecutePool(50,10000);//创建IO线程任务池
			
			while(true){
				socket = server.accept();
				singleEcecute.execute(new TimeServerHander(socket));
			}
			
		} catch (Exception e) {
		} finally{
			if(server!=null){
				System.out.println("The time server close");
				if(server!=null && !server.isClosed()){
					try {
						server.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				server = null;
			}
		}
		

	}
	
}
