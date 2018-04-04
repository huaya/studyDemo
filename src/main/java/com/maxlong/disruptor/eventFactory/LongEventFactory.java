package com.maxlong.disruptor.eventFactory;

import com.lmax.disruptor.EventFactory;
import com.maxlong.disruptor.event.LongEvent;

/** 
* @author 作者 maxlong: 
* @version 创建时间：2016年6月27日 上午10:34:35 
* 类说明 
*/
public class LongEventFactory implements EventFactory<LongEvent> {

	@Override
	public LongEvent newInstance() {
		return new LongEvent();
	}

}
 