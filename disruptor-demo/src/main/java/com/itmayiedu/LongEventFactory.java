package com.itmayiedu;

import com.lmax.disruptor.EventFactory;

//实例化Event对象
public class LongEventFactory   implements   EventFactory{

	public Object newInstance() {
	
		return new LongEvent();
	}

}
