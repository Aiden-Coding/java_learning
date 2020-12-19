
package com.itmayiedu;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.itmayiedu.entity.User;

public class Test004 {

	public static void main(String[] args)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException,
			SecurityException, IllegalArgumentException, InvocationTargetException {
		// 1.获取当前的class地址 反射机制 给私有属性复制
		// User user = new User();
		// 2.通过反射机制实例化对象 <bean id="user" class="com.itmayiedu.entity.User">
		Class<?> classUser = Class.forName("com.itmayiedu.entity.User");
		// 使用反射机制创建对象
		System.out.println("使用java反射机制创建对象");
		// newInstance(使用默认无参构造函数初始化对象)
		// User user = (User) classUser.newInstance();
		// user.setId("1");
		// user.setName("张三");
		// System.out.println(user.getId()+"---"+user.getName());
		Constructor<?> constructor = classUser.getConstructor(String.class);
		User user = (com.itmayiedu.entity.User) constructor.newInstance("1");
		System.out.println(user.getId());
	}

}
