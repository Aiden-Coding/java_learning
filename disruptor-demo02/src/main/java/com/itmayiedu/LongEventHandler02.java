package com.itmayiedu;

import com.lmax.disruptor.EventHandler;

public class LongEventHandler02 implements EventHandler<LongEvent> {

	public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
		System.out.println("消费者2:" + event.getValue());
	}

}
