package com.maxlong.study.io.server.fio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

/**
* @author 作者:maxlong
* @version 创建时间：2016年6月4日 下午12:24:45
* 类说明
*/
public class TimeServerHander implements Runnable {
	
	private Socket socket;
	
	public TimeServerHander(Socket socket) {
		super();
		this.socket = socket;
	}

	@Override
	public void run() {
		BufferedReader in = null;
		PrintWriter out = null;
		try {
			in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			out = new PrintWriter(this.socket.getOutputStream(), true);
			String currentTime = null;
			String body = null;
			while(true){
				body = in.readLine();
				if(body == null)break;
				System.out.println("The time server recevice order:" + body);
				currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body)?new Date(System.currentTimeMillis()).toString():"BAD ORDER";
				out.println(currentTime);
			}
		} catch (Exception e) {
			if(in != null){
				try {
					in.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			
			if(out != null){
				out.close();
				out = null;
			}
			
			if(this.socket != null){
				try {
					this.socket.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			this.socket = null;
		}
	}

}
