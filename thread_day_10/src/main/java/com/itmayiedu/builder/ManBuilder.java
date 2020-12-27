package com.itmayiedu.builder;

public class ManBuilder implements PersonBuilder {
	private Person person;

	public ManBuilder() {
		person = new Person();//创建一个Person实例,用于调用set方法
	}

	public void builderHead() {
		person.setHead("建造者头部分");
	}

	public void builderBody() {
		person.setBody("建造者身体部分");
	}

	public void builderFoot() {
		person.setFoot("建造者头四肢部分");
	}

	public Person BuilderPersion() {
		return person;
	}

}
