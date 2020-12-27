package com.itmayiedu;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Test0005 {

	public static void main(String[] args)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException,
			SecurityException, IllegalArgumentException, InvocationTargetException {
		Class<?> clazz = Class.forName("com.itmayiedu.Test0005");
		Object newInstance = clazz.newInstance();
		Method method = clazz.getDeclaredMethod("sum", int.class, int.class);
		Object invoke = method.invoke(newInstance, 1, 1);
	}

	public void sum(int a, int b) {
		System.out.println("sum:" + a + b);
	}

}
