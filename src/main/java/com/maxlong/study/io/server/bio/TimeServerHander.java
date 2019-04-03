package com.maxlong.study.io.server.bio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

/**
* @author 作者:maxlong
* @version 创建时间：2016年6月4日 下午12:24:45
* 类说明
*/
public class TimeServerHander implements Runnable,AutoCloseable {
	
	private Socket socket;
	
	public TimeServerHander(Socket socket) {
		super();
		this.socket = socket;
	}

	@Override
	public void run() {
		try (BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()))){
			PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);

			while(true){
				String body = in.readLine();
				if(body == null)break;
				System.out.println("The time server recevice order:" + body);
				String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body)?new Date(System.currentTimeMillis()).toString():"BAD ORDER";
				out.println(currentTime);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void close() throws Exception {
		this.socket.close();
		this.socket = null;
	}
}
