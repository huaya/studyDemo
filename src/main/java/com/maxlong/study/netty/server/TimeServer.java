package com.maxlong.study.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author 作者 maxlong:
 * @version 创建时间：2016年6月22日 下午12:13:46
 * 类说明
 */
public class TimeServer {
	public void bind(int port){
		NioEventLoopGroup bossGroup = new NioEventLoopGroup(2);
		NioEventLoopGroup workGroup = new NioEventLoopGroup(5);
		try {
			ServerBootstrap b = new  ServerBootstrap();
			b.group(bossGroup, workGroup)
					.channel(NioServerSocketChannel.class)
					.option(ChannelOption.SO_BACKLOG,1024)
					.handler(new LoggingHandler(LogLevel.INFO))
					.childHandler(new ChannelInitializer<SocketChannel>(){
						@Override
						protected void initChannel(SocketChannel ch) throws Exception {
							ch.pipeline().addLast(new LineBasedFrameDecoder(1024));
							ch.pipeline().addLast(new StringDecoder());
							ch.pipeline().addLast(new TimeServerHandler());
						}
					});

			ChannelFuture f = b.bind(port).sync();
			f.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			bossGroup.shutdownGracefully();
			workGroup.shutdownGracefully();
		}

	}

	public static void main(String[] args) {
		int  port = 8080;
		new TimeServer().bind(port);
	}
}
 