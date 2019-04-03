package com.maxlong.study.io.client.bio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

/**
* @author 作者:maxlong
* @version 创建时间：2016年6月4日 下午12:51:19
* 类说明
*/
public class TimeClient {

	private static final int port = 7002;

	public static void main(String[] args) {
		
		try (Socket socket = new Socket("localhost", port);
			 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			 BufferedWriter writer =  new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))){
			Scanner scanner = new Scanner(System.in);
			while (scanner.hasNext()){
				String msg = scanner.nextLine();
				writer.write(msg);
				writer.newLine();
				writer.flush();
				System.out.println("[request]:" + msg);
				System.out.println("[response]: " + in.readLine());
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}
