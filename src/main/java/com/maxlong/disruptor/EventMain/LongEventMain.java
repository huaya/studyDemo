package com.maxlong.disruptor.EventMain;

import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.maxlong.disruptor.event.LongEvent;
import com.maxlong.disruptor.eventFactory.LongEventFactory;
import com.maxlong.disruptor.eventHandle.LongEventHandler;
import com.maxlong.disruptor.eventProducer.LongEventProducer;


public class LongEventMain {
	@SuppressWarnings({ "unchecked", "deprecation" })
	public static void main(String[] args) {
        Executor executor = Executors.newCachedThreadPool();
        LongEventFactory factory = new LongEventFactory();
        int bufferSize = 1024;
        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(factory, bufferSize, executor);
        disruptor.handleEventsWith(new LongEventHandler());
        disruptor.start();
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer(); 
 
        LongEventProducer producer = new LongEventProducer(ringBuffer); 
 
        ByteBuffer bb = ByteBuffer.allocate(8);
        for (long l = 0; true; l++) { 
            bb.putLong(0,l); 
            producer.onData(bb); 
            try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
        } 
    } 
}
 