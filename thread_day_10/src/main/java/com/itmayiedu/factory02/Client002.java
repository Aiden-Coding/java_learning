package com.itmayiedu.factory02;

public class Client002 {

	public static void main(String[] args) {
		CarFactory carFactory = new JiLiFactory();
		Engine engine = carFactory.createEngine();
		Chair chair = carFactory.createChair();
		engine.run();
		chair.run();

	}
}
