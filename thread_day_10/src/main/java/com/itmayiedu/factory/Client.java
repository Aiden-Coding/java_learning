package com.itmayiedu.factory;

public class Client {

	 public static void main(String[] args) {
		 Car car = CarFactory.createCar("吉利");
		 car.run();
	}
	
}
