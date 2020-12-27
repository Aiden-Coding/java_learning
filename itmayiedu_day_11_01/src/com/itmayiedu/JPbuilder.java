package com.itmayiedu;

// 构建日本人
public class JPbuilder implements PersonBuilder {

	@Override
	public void builderHead() {
    System.out.println("日本人 圆脸");
	}

	@Override
	public void builderBody() {
		 System.out.println("日本人 比较矮");
	}

	@Override
	public void builderFoot() {
		 System.out.println("日本人 腿短");

	}

	@Override
	public Person builderPerson() {
	
		return null;
	}

}
