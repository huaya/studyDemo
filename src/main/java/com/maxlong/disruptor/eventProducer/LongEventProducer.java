package com.maxlong.disruptor.eventProducer;

import java.nio.ByteBuffer;

import com.lmax.disruptor.RingBuffer;
import com.maxlong.disruptor.event.LongEvent;

/** 
* @author ���� maxlong: 
* @version ����ʱ�䣺2016��6��27�� ����10:41:59 
* ��˵�� 
*/
public class LongEventProducer {

	private final RingBuffer<LongEvent> ringBuffer;

	public LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
		this.ringBuffer = ringBuffer;
	}
	
	public void onData(ByteBuffer bb) { 
	    //���԰�ringBuffer����һ���¼����У���ônext���ǵõ�����һ���¼���
	    long sequence = ringBuffer.next();
	    try { 
	        //�����������ȡ��һ���յ��¼�������� 
	        LongEvent event = ringBuffer.get(sequence);// for the sequence 
	        event.setValue(bb.getLong(0)); 
	    } finally { 
	        //�����¼� 
	        ringBuffer.publish(sequence); 
	    } 
} 
}
 