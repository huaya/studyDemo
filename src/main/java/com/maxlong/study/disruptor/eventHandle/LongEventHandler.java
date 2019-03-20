package com.maxlong.study.disruptor.eventHandle;

import com.lmax.disruptor.EventHandler;
import com.maxlong.study.disruptor.event.LongEvent;

/**
 * @author 作者 maxlong:
 * @version 创建时间：2016年6月27日 上午10:39:02
 * 类说明
 */
public class LongEventHandler implements EventHandler<LongEvent>{

	@Override
	public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
		System.out.println(event.getValue());
	}

}
 