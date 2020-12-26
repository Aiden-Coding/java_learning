package com.itmayiedu;

import com.lmax.disruptor.EventFactory;

//定义事工厂
public class LongEventFactory implements EventFactory<LongEvent> {

	public LongEvent newInstance() {

		return new LongEvent();
	}

}
