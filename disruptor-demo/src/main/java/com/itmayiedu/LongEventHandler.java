package com.itmayiedu;

import com.lmax.disruptor.EventHandler;

// 事件消费者
public class LongEventHandler implements EventHandler<LongEvent>{

	public void onEvent(LongEvent longEvent, long l, boolean b) throws Exception {
	     System.out.println("消费者:"+longEvent.getValue());
		
	}

}
