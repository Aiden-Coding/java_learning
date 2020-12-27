package com.itmayiedu.factory;

import com.itmayiedu.JILiFactory;

public class Client {

	public static void main(String[] args) {
		CarFactory carFactory = new JiLiFactory2();
		Engine createEngine = carFactory.createEngine();
		Chair createChair = carFactory.createChair();
		createEngine.run();
		createChair.run();
	}

}
