package com.itmayiedu;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Test007 {

	public static void main(String[] args)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException,
			SecurityException, IllegalArgumentException, InvocationTargetException {
		Class<?> forName = Class.forName("com.itmayiedu.SingletonDemo04");
		Constructor<?> declaredConstructor = forName.getDeclaredConstructor(null);
		declaredConstructor.setAccessible(true);
		Object s1 = declaredConstructor.newInstance();
		// Object s2 = declaredConstructor.newInstance();
		System.out.println(s1);
		// System.out.println(s2);
	}

}
