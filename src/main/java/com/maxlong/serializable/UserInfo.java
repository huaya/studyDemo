package com.maxlong.serializable;

import java.io.Serializable;
import java.nio.ByteBuffer;

/**
 * @author 作者 maxlong:
 * @version 创建时间：2016年6月23日 上午9:14:22
 * 类说明
 */
public class UserInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String  userId;

	private String  userName;

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


}
 