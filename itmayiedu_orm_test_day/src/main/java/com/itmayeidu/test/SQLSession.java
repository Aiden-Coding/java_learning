package com.itmayeidu.test;

import java.lang.reflect.Proxy;

public class SQLSession {

	public static <T> T getMapper(Class<T> classz) {
		return (T) Proxy.newProxyInstance(classz.getClassLoader(), new Class[] { classz },
				new MyInvocationHandlerMbatis(classz));
	}

}
