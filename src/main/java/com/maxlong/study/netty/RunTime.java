package com.maxlong.study.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.nio.NioEventLoopGroup;

/**
 * @author 作者 maxlong:
 * @version 创建时间：2016年6月21日 下午3:06:29
 * 类说明
 */
public class RunTime {
	public static void main(String[] args) {
		int bossw = Runtime.getRuntime().availableProcessors()+1;
		int workerw = Runtime.getRuntime().availableProcessors()*2+1;
		NioEventLoopGroup boss = new NioEventLoopGroup(2);
		NioEventLoopGroup work = new NioEventLoopGroup(5);
		ServerBootstrap b = new ServerBootstrap();
		b.group(boss,work);
		System.out.println("boss:" + bossw + ", worker:" + workerw);

	}
}
 