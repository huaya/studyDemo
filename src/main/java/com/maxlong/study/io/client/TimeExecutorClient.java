package com.maxlong.study.io.client;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
* @author 作者:maxlong
* @version 创建时间：2016年6月4日 下午12:51:19
* 类说明
*/
@Slf4j
public class TimeExecutorClient {

	private static final int port = 7002;

	private static ExecutorService service = Executors.newCachedThreadPool();

	public static void main(String[] args) {
		for(int i=0; i < 2000; i++){
			service.execute(() -> {
				try (Socket socket = new Socket("localhost", port);
					 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					 BufferedWriter writer =  new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))){

					String msg = "QUERY TIME ORDER";
					writer.write(msg);
					writer.newLine();
					writer.flush();
					log.info("request :{}", msg);
					log.info("response: {}", in.readLine());

				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		}
		service.shutdown();
	}
}
