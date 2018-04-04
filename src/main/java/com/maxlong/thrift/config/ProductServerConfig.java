package com.maxlong.thrift.config;
/**
 * @author 作者 maxlong:
 * @version 创建时间：2016年6月24日 下午4:22:48
 * 类说明
 */
public class ProductServerConfig {
	private int port;

	private int selectorthreads;

	private int workthreads;

	private int acceptqueuesizeterthread;

	private int fixedThreadPoolSize;

	public int getFixedThreadPoolSize() {
		return fixedThreadPoolSize;
	}

	public void setFixedThreadPoolSize(int fixedThreadPoolSize) {
		this.fixedThreadPoolSize = fixedThreadPoolSize;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getSelectorthreads() {
		return selectorthreads;
	}

	public void setSelectorthreads(int selectorthreads) {
		this.selectorthreads = selectorthreads;
	}

	public int getWorkthreads() {
		return workthreads;
	}

	public void setWorkthreads(int workthreads) {
		this.workthreads = workthreads;
	}

	public int getAcceptqueuesizeterthread() {
		return acceptqueuesizeterthread;
	}

	public void setAcceptqueuesizeterthread(int acceptqueuesizeterthread) {
		this.acceptqueuesizeterthread = acceptqueuesizeterthread;
	}


}
 