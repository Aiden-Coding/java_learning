package com.itmayiedu.adaptor;

//电饭煲
public class ElectricCooker {

	private JP110VInterface jP110VInterface;

	public ElectricCooker(JP110VInterface jP110VInterface) {
		this.jP110VInterface = jP110VInterface;
	}

	public void cook() {
		jP110VInterface.connect();
		System.out.println("开始做饭...");
	}

}
