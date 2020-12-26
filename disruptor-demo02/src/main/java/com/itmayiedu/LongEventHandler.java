package com.itmayiedu;

import com.lmax.disruptor.EventHandler;

// 定义事件的具体实现---消费者
public class LongEventHandler implements EventHandler<LongEvent> {

	public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
		System.out.println("消费者1:" + event.getValue());
	}

}
