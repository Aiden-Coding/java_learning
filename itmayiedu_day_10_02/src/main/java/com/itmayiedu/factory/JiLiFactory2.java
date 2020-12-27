package com.itmayiedu.factory;

public class JiLiFactory2 implements CarFactory {

	public Engine createEngine() {

		return new EngineA();
	}

	public Chair createChair() {
	
		return new ChairA();
	}



}
