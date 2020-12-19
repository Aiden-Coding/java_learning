
package com.itmayiedu;

interface Car {
	void run();
}

class AoDi implements Car {

	@Override
	public void run() {
		System.out.println("我是奥迪....");
	}

}

class BenChi implements Car {

	@Override
	public void run() {
		System.out.println("我是奔驰....");
	}

}

// 简单工厂
class CarFactory {
	public static Car createCar(String name) {
		Car car = null;
		switch (name) {
		case "奥迪":
			car = new AoDi();
			break;
		case "奔驰":
			car = new BenChi();
			break;
		default:
			break;
		}
		return car;
	}

}

class BenChiFactory {

	public static Car createCar() {
		return new BenChi();
	}
}

class AoDiFactory {

	public static Car createCar() {
		return new AoDi();
	}
}

public class Test002 {

	public static void main(String[] args) {
		// Car car = CarFactory.createCar("奔驰");
		// car.run();
		Car aodi = AoDiFactory.createCar();
		Car benchi = BenChiFactory.createCar();
		aodi.run();
		benchi.run();
	}

}
