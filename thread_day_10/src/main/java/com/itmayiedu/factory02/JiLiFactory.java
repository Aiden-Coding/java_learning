package com.itmayiedu.factory02;

public class JiLiFactory implements CarFactory {

	public Engine createEngine() {

		return new EngineA();
	}

	public Chair createChair() {

		return new ChairA();
	}

}
