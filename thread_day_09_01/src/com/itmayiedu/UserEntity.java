package com.itmayiedu;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;

public class UserEntity {

	private String userName;

	private UserEntity() {
		System.out.println("对象初始化...");
		 throw new RuntimeException();
	}

	private UserEntity(String userName) {
		System.out.println("userName:" + userName);
		this.userName = userName;
	}

	// 思考:怎么防止被反射  设置成私有的
	// 思考：在构造函数中，如果发生异常，对象会创建成功吗? 扩展性
	public static void main(String[] args)
 throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		
	}

}
