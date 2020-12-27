package com.itmayiedu;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class Test {

	public static void main(String[] args)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException,
			SecurityException, IllegalArgumentException, InvocationTargetException {
		// // 1.初始化的操作 无参数构造函数
		//// UserEntity userEntity = new UserEntity();
		//// userEntity.userName = "蚂蚁课堂";
		//// System.out.println(userEntity.userName);
		// // 2.使用Java的反射机制创建对象 类的完整路径
		// Class<?> forName = Class.forName("com.itmayiedu.UserEntity");
		// // 3.使用反射机制创建对象
		// UserEntity userEntity = (UserEntity) forName.newInstance();
		//// userEntity.userName="反射对象";
		//// System.out.println(userEntity.userName);
		// // 4.反射应用场景 1.jdbc连接、springIOC底层使用反射机制+DOM4J 2、框架hibernate、mybatis
		// 使用到反射机制
		// // 5.使用反射机制 获取类的方法信息
		//// Method[] methods = forName.getMethods();
		//// for (Method method : methods) {
		//// System.out.println(method.getName());
		//// }
		// // 6.获取类属性
		// Field[] fields = forName.getDeclaredFields();
		// for (Field field : fields) {
		// System.out.println(field.getName());
		// }

		// 1.初始化的操作 无参数构造函数
		// UserEntity userEntity = new UserEntity();
		// userEntity.userName = "蚂蚁课堂";
		// System.out.println(userEntity.userName);
		// 2.使用Java的反射机制创建对象 类的完整路径
		Class<?> forName = Class.forName("com.itmayiedu.UserEntity");
		// 3.使用反射机制创建对象
		// UserEntity userEntity = (UserEntity) forName.newInstance();
		// userEntity.userName="反射对象";
		// System.out.println(userEntity.userName);
		// 4.反射应用场景 1.jdbc连接、springIOC底层使用反射机制+DOM4J 2、框架hibernate、mybatis
		// 使用到反射机制
		// 5.使用反射机制 获取类的方法信息
		// Method[] methods = forName.getMethods();
		// for (Method method : methods) {
		// System.out.println(method.getName());
		// }
		// 6.获取类属性
		// Field[] fields = forName.getDeclaredFields();
		// for (Field field : fields) {
		// System.out.println(field.getName());
		// }
		Constructor<?> constructor = forName.getDeclaredConstructor(String.class);
		constructor.setAccessible(true);
		UserEntity userEntity = (UserEntity) constructor.newInstance("每特教育");
		// System.out.println(userEntity.userName);
	}

}
