package com.maxlong.disruptor.eventHandle;

import com.lmax.disruptor.EventHandler;
import com.maxlong.disruptor.event.LongEvent;

/** 
* @author ���� maxlong: 
* @version ����ʱ�䣺2016��6��27�� ����10:39:02 
* ��˵�� 
*/
public class LongEventHandler implements EventHandler<LongEvent>{

	@Override
	public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
		 System.out.println(event.getValue()); 
	}

}
 