package com.itmayiedu.list;

import java.util.Arrays;

import org.omg.CORBA.OBJ_ADAPTER;

public class Test002 {

	public static void main(String[] args) {
		Object[] objects = new Object[] { 0, 1, 2, 3, 4, 5, 6 };
		System.out.println(objects.length);
		// Object[] newObjects = Arrays.copyOf(objects, 10);
		// System.out.println(newObjects.length);
		// 参数1起始位置赋值 参数2赋值数据源 参数3 目的地数据源 参数4 目的的起始位置 参数五 赋值长度
		System.arraycopy(objects, 0, objects, 3, 3);
		for (Object object : objects) {
			System.out.print(object + ",");
		}

	}

}
