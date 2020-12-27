package com.itmayiedu.test;

import java.util.Arrays;

public class Test001 {

	// 底层采用数组方式
	// 怎么保证集合存放无限大小---数组扩容技术
	public static void main(String[] args) {
		Object[] objects = { 1, 2 };
		System.out.println(objects.length);
		Object[] copyNewObjects = Arrays.copyOf(objects, 10);
		System.out.println(copyNewObjects.length);
	}

}
