package com.maxlong.netty.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author 作者 maxlong:
 * @version 创建时间：2016年6月22日 下午2:41:19
 * 类说明
 */
public class TimeClientHandler extends ChannelHandlerAdapter {

	private int counter;
	private byte[] req;
	public TimeClientHandler() {
		req = ("QUERY TIME ORDER" + System.getProperty("line.separator")).getBytes();
	}

	@Override
	public void channelActive(ChannelHandlerContext ctc){
		System.out.println("This is TimeClientHandler's channelActive");
		ByteBuf message;
		for(int i = 0;i<50;i++){
			message = Unpooled.buffer(req.length);
			message.writeBytes(req);
			ctc.writeAndFlush(message);
		}
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx,Object msg) throws Exception {
		System.out.println("this is TimeServerHandler's channelRead");
		//ByteBuf buf = (ByteBuf) msg;
		//byte[] req = new byte[buf.readableBytes()];
		//buf.readBytes(req);
		//String body = new String(req,"UTF-8");
		String body  = (String) msg;
		System.out.println("Now is: " + body + ", the counter is :" + ++counter);
	}


	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
		System.out.println("this is TimeServerHandler's exceptionCaught");
		ctx.close();
	}
}
 	