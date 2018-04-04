package com.maxlong.netty.server;

import java.util.Date;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author 作者 maxlong:
 * @version 创建时间：2016年6月22日 下午1:26:38
 * 类说明
 */
public class TimeServerHandler extends ChannelHandlerAdapter {

    private int counter;

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("this is TimeServerHandler's channelRead");
        /** get request from client */
        //ByteBuf buffer  = (ByteBuf)msg;
        //byte[] req = new byte[buffer.readableBytes()];
        //buffer.readBytes(req);
        // String body = new String(req,"UTF-8").substring(0,req.length-System.getProperty("line.separator").length());

        String body = (String) msg;
        System.out.println("This timeserver receive order: " + body + ", the counter is :" + ++counter);

        String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body)?new Date(System.currentTimeMillis()).
                toString():"BADY ORDER";
        currentTime = currentTime + System.getProperty("line.separator");
        ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
        ctx.writeAndFlush(resp);
    }

    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("this is TimeServerHandler's channelReadComplete");
        ctx.flush();
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
        System.out.println("this is TimeServerHandler's exceptionCaught");
        ctx.close();
    }
}
 