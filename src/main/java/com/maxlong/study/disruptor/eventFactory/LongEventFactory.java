package com.maxlong.study.disruptor.eventFactory;

import com.lmax.disruptor.EventFactory;
import com.maxlong.study.disruptor.event.LongEvent;

public class LongEventFactory implements EventFactory<LongEvent> {

	@Override
	public LongEvent newInstance() {
		return new LongEvent();
	}

}
 