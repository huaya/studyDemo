package com.maxlong.study.disruptor.eventProducer;

import java.nio.ByteBuffer;

import com.lmax.disruptor.RingBuffer;
import com.maxlong.study.disruptor.event.LongEvent;

public class LongEventProducer {

	private final RingBuffer<LongEvent> ringBuffer;

	public LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
		this.ringBuffer = ringBuffer;
	}
	
	public void onData(ByteBuffer bb) { 
	    long sequence = ringBuffer.next();
	    try { 
	        LongEvent event = ringBuffer.get(sequence);// for the sequence
	        event.setValue(bb.getLong(0)); 
	    } finally { 
	        ringBuffer.publish(sequence);
	    } 
} 
}
 