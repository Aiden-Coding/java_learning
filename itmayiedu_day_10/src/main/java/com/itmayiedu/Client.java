package com.itmayiedu;

public class Client {

	public static void main(String[] args) {
      Car byd = CarFactory.createCar("吉利");
      byd.run();
	}

}
