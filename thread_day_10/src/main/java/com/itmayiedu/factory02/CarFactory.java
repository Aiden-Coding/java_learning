package com.itmayiedu.factory02;

public interface CarFactory {

	// 创建发动机
	Engine createEngine();

	// 创建座椅
	Chair createChair();
}
