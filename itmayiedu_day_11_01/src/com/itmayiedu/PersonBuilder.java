package com.itmayiedu;

//创建人体Builder  在使用设计模式的时候一定学会使用抽象类或者接口。飞虎队 猎狐者 灵狐者
public interface PersonBuilder {

	// 构造头部
	void builderHead();

	// 构造体部
	void builderBody();

	// 构造尾部
	void builderFoot();

	Person builderPerson();//組裝部件。

}
