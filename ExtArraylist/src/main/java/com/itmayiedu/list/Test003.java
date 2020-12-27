package com.itmayiedu.list;

public class Test003 {

	public static void main(String[] args) {
		// 1.jdk 1.7之后 数组默认数据大小代码存放在add方法 (JDK1.6的时候 默认构造函数初始化elementData大小)
		// 2.arraylist底层采用数组实现 数组名称elementData
		// 2.arraylist底层采用数组实现默认初始化最小容量为10
		JDKArrayList<Object> jdkArrayList = new JDKArrayList<>(2);
		jdkArrayList.add("张三");
		jdkArrayList.remove("yushengjun");
		jdkArrayList.add(1, "李四");
	}

}
