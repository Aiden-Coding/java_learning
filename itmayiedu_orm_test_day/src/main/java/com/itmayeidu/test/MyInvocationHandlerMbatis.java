package com.itmayeidu.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

// 代理类
public class MyInvocationHandlerMbatis implements InvocationHandler {
	private Object object;

	public MyInvocationHandlerMbatis(Object object) {
		this.object = object;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("开始执行..");
		return 1;
	}

}
