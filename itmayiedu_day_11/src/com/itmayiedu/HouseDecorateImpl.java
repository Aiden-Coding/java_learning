package com.itmayiedu;

//房屋装饰具体实现类
public class HouseDecorateImpl extends HouseDecorate {

	public HouseDecorateImpl(House house) {
		super(house);

	}

	@Override
	public void run() {
		super.run();
		System.out.println("贴上墙纸..");
	}

}
