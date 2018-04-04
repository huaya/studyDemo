package com.maxlong.disruptor.eventFactory;

import com.lmax.disruptor.EventFactory;
import com.maxlong.disruptor.event.LongEvent;

/** 
* @author ���� maxlong: 
* @version ����ʱ�䣺2016��6��27�� ����10:34:35 
* ��˵�� 
*/
public class LongEventFactory implements EventFactory<LongEvent> {

	@Override
	public LongEvent newInstance() {
		return new LongEvent();
	}

}
 