package com.itmayiedu.factory;

import com.itmayiedu.entity.LongEvent;
import com.lmax.disruptor.EventFactory;

// EventFactory 实例化LongEvent
public class LongEventFactory implements EventFactory<LongEvent> {

	public LongEvent newInstance() {

		return new LongEvent();
	}

}
