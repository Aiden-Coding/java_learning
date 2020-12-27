package com.itmayiedu.list;

public class Test001 {

	// 1.集合底层使用数组实现
	// 2.怎么保证集合中存放无限大小###数组扩容技术
	// 3.>> << 2 >> 1= 2/2=1
	public static void main(String[] args) {

		ExtList<String> extArrayList = new ExtArrayList<String>();
		extArrayList.add("张三");
		extArrayList.add("李四");
		extArrayList.add("王武");
		extArrayList.add(1, "余胜军");
		// System.out.println(extArrayList.getSize());
		// for (int i = 0; i < extArrayList.getSize(); i++) {
		// Object object = extArrayList.get(i);
		// System.out.println(object);
		// }
		System.out.println("获取第一个元素值:" + extArrayList.get(0));
		System.out.println("获取第二个元素值:" + extArrayList.get(1));
		System.out.println("获取第三个元素值:" + extArrayList.get(2));
		System.out.println("获取第四个元素值:" + extArrayList.get(3));
		System.out.println("获取第五个元素值:" + extArrayList.get(4));

	}

}
