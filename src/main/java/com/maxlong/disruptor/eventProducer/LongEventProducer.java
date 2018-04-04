package com.maxlong.disruptor.eventProducer;

import java.nio.ByteBuffer;

import com.lmax.disruptor.RingBuffer;
import com.maxlong.disruptor.event.LongEvent;

/** 
* @author 作者 maxlong: 
* @version 创建时间：2016年6月27日 上午10:41:59 
* 类说明 
*/
public class LongEventProducer {

	private final RingBuffer<LongEvent> ringBuffer;

	public LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
		this.ringBuffer = ringBuffer;
	}
	
	public void onData(ByteBuffer bb) { 
	    //可以把ringBuffer看做一个事件队列，那么next就是得到下面一个事件槽
	    long sequence = ringBuffer.next();
	    try { 
	        //用上面的索引取出一个空的事件用于填充 
	        LongEvent event = ringBuffer.get(sequence);// for the sequence 
	        event.setValue(bb.getLong(0)); 
	    } finally { 
	        //发布事件 
	        ringBuffer.publish(sequence); 
	    } 
} 
}
 