package com.itmayiedu;

//构造人物 美国人。
public class ManBuilder implements PersonBuilder {
	private Person person;

	public ManBuilder() {
		person = new Person();
	}

	@Override
	public void builderHead() {
		person.setHead("美国人 头部 鼻子尖、长脸、蓝眼睛");
	}

	@Override
	public void builderBody() {
		person.setBody("美国人 体部  长的比较高 ，块头大。");

	}

	@Override
	public void builderFoot() {
		person.setFoot("美国人 尾部腿长...");
	}

	// 返回每个部位整合
	public Person builderPerson() {
		return person;
	}

}
