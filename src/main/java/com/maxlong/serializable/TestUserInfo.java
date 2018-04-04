package com.maxlong.serializable;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;

/**
 * @author 作者 maxlong:
 * @version 创建时间：2016年6月23日 上午9:15:55
 * 类说明
 */
public class TestUserInfo {

	public static void main(String[] args) throws IOException {
		UserInfo user = new UserInfo();
		user.setUserId("qwertyuiop");
		user.setUserName("qazwsxedcrfvtgbyhn");
		int loop = 1000000;
		ByteArrayOutputStream bos = null;
		ObjectOutputStream os = null;
		long startTime = System.currentTimeMillis();
		for(int i = 0;i < loop;i++){
			bos = new ByteArrayOutputStream();
			os = new ObjectOutputStream(bos);
			os.writeObject(user);
			os.flush();
			os.close();
			bos.toByteArray();
			bos.close();
		}
		long endTime = System.currentTimeMillis();
		System.out.println("The jdk Serializable cost time is : " + (endTime - startTime) + "ms");

		startTime = System.currentTimeMillis();
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		for(int i = 0;i < loop;i++){
			user.codeC(buffer);
		}
		endTime = System.currentTimeMillis();
		System.out.println("The byte array Serializable cost time is : " + (endTime - startTime) + "ms");
	}

}
 