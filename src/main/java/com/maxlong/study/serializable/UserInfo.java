package com.maxlong.study.serializable;

import sun.misc.Unsafe;
import sun.reflect.CallerSensitive;
import sun.reflect.Reflection;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;

/**
 * @author 作者 maxlong:
 * @version 创建时间：2016年6月23日 上午9:14:22
 * 类说明
 */
public class UserInfo implements Serializable {

	private String  userId;

	private String  userName;

	private int age;

	public static final sun.misc.Unsafe U;

	public static final Long AGE;

	public static final Long USERID;

	public static final Long USERNAME;

	static {
		try {
			Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
			theUnsafe.setAccessible(true);
			U = (Unsafe) theUnsafe.get(null);
			Class k = UserInfo.class;
			USERID = U.objectFieldOffset(k.getDeclaredField("userId"));
			USERNAME = U.objectFieldOffset(k.getDeclaredField("userName"));
			AGE = U.objectFieldOffset(k.getDeclaredField("age"));
		} catch (Exception e) {
			throw new Error(e);
		}

	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public UserInfo() {
	}

	public static void testCallerSensitive() {
		Class var1 = Reflection.getCallerClass();
		System.out.println(var1);
	}

	public UserInfo(String userId, String userName) {
		this.userId = userId;
		this.userName = userName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public static Unsafe getU() {
		return U;
	}

	public static Long getUSERID() {
		return USERID;
	}

	public static Long getUSERNAME() {
		return USERNAME;
	}

	public byte[] codeC(ByteBuffer buffer) {
		buffer.clear();
		buffer = ByteBuffer.allocate(1024);
		byte[] value = this.userName.getBytes();
		buffer.putInt(value.length);
		buffer.put(value);
		byte[] id = this.userId.getBytes();
		buffer.putInt(id.length);
		buffer.put(id);
		buffer.flip();
		byte[] resule = new byte[buffer.remaining()];
		buffer.get(resule);
		return resule;
	}

	@Override
	public String toString() {
		return "UserInfo{" +
				"userId='" + userId + '\'' +
				", userName='" + userName + '\'' +
				'}';
	}


}
 