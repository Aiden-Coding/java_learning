
package com.itmayiedu;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.itmayiedu.entity.User;

public class Test005 {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, NoSuchFieldException, SecurityException {
		// 为user对象私有属性赋值
		Class<?> classUser = Class.forName("com.itmayiedu.entity.User");
		// 获取到当前的所有属性
		Field[] fields = classUser.getDeclaredFields();
		for (Field field : fields) {
			System.out.println(field.getName());
		}
		// 获取当前所有的方法
		Method[] declaredMethods = classUser.getDeclaredMethods();
		for (Method method : declaredMethods) {
			System.out.println(method.getName());
		}
		// 初始化对象
		User user = (User) classUser.newInstance();
		Field declaredField = classUser.getDeclaredField("id");
		// 标记为true 允许反射赋值
		declaredField.setAccessible(true);
		declaredField.set(user, "20");
		System.out.println("使用反射机制给id赋值为:"+user.getId());

	}

}
