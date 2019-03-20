package com.maxlong.study.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @author 作者 maxlong:
 * @version 创建时间：2016年6月22日 下午2:37:23
 * 类说明
 */
public class TimeClient {
	public void connect(int port,String host){
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap b = new  Bootstrap();
			b.group(group)
					.channel(NioSocketChannel.class)
					.option(ChannelOption.TCP_NODELAY,true)
					.handler(new ChannelInitializer<SocketChannel>(){
						@Override
						protected void initChannel(SocketChannel ch) throws Exception {
							ch.pipeline().addLast(new LineBasedFrameDecoder(1024));
							ch.pipeline().addLast(new StringDecoder());
							ch.pipeline().addLast(new TimeClientHandler());
						}
					});

			ChannelFuture f = b.connect(host, port).sync();
			f.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			group.shutdownGracefully();
		}
	}

	public static void main(String[] args) {
		int port = 8080;
		new TimeClient().connect(port, "127.0.0.1");
	}
}
 