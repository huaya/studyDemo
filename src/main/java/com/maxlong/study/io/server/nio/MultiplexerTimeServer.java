package com.maxlong.study.io.server.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
* @author 作者:maxlong
* @version 创建时间：2016年6月5日 下午12:05:24
* 类说明
*/
public class MultiplexerTimeServer implements Runnable {

	private Selector selector;
	
	private ServerSocketChannel sevChannle;
	
	private volatile boolean stop;
	
	public MultiplexerTimeServer(int port) {
		try {
			selector = Selector.open();
			sevChannle = ServerSocketChannel.open();
			sevChannle.configureBlocking(false);
			sevChannle.socket().bind(new InetSocketAddress(port), 1024);
			sevChannle.register(selector, SelectionKey.OP_ACCEPT);
			System.out.println("The time server is start in port:" + port);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public void stop(){
		this.stop = true;
	}
	
	@Override
	public void run() {
		while(!stop){
			try {
				selector.select(1000);
				Set<SelectionKey> selectedKeys = selector.selectedKeys();
				Iterator<SelectionKey> it = selectedKeys.iterator();
				SelectionKey key = null;
				while(it.hasNext()){
					key = it.next();
					it.remove();
					handleInput(key);
					if(key!=null){
						key.cancel();
						if(key.channel()!=null){
							key.channel().close();
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	    if (selector != null)
	        try {
	        	selector.close();
	        } catch (IOException e) {
	        	e.printStackTrace();
	        }
	}

	private void handleInput(SelectionKey key) throws IOException {
		if(key.isValid()){
			if(key.isAcceptable()){
				ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
				SocketChannel sc = ssc.accept();
				sc.configureBlocking(false);
				sc.register(selector, SelectionKey.OP_READ);
			}
			if(key.isReadable()){
				SocketChannel sc = (SocketChannel) key.channel();
				ByteBuffer readBuffer = ByteBuffer.allocate(1024);
				int readBytes = sc.read(readBuffer);
				if(readBytes > 0){
					readBuffer.flip();
					byte[] bytes = new byte[readBuffer.remaining()];	
					readBuffer.get(bytes);
					String body = new String(bytes,"UTF-8").trim();
					System.out.println("The time server recevice order:" + body);
					String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body)?new Date(System.currentTimeMillis()).toString():"BAD ORDER";
					doWrite(sc,currentTime);
				}else if(readBytes < 0){
					//对端链路关闭
					key.cancel();
					sc.close();
				}else{//读到0字节，忽略}
				}
			}
		}
	}

	private void doWrite(SocketChannel sc, String response) throws IOException {
		if(response!=null&&response.length()>0){
			byte[] bytes = response.getBytes();
			ByteBuffer writeBuffers = ByteBuffer.allocate(1024);
			writeBuffers.put(bytes);
			writeBuffers.flip();
			sc.write(writeBuffers);
		}
	}
}
