package com.maxlong.study.netty.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author 作者 maxlong:
 * @version 创建时间：2016年6月22日 下午1:29:26
 * 类说明
 */
public class TimeLogHander extends LoggingHandler{

	public TimeLogHander(LogLevel level) {
		super(level);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println("this is TimeLogHander's read");
		super.channelRead(ctx, msg);
	}

	@Override
	public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
		System.out.println("this is TimeLogHander's write");
		super.write(ctx, msg, promise);
	}

}
 