package com.itmayiedu.decorator;

//装饰类
public class HouseDecorato implements House {
	private House house;

	public HouseDecorato(House house) {
		this.house = house;
	}

	@Override
	public void run() {
		house.run();
	}

}
