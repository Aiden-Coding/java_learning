package com.itmayiedu.hasmap;

import java.util.HashMap;
import java.util.Map;

public class Test001 {

	public static void main(String[] args) {
		// 基于什么原则 后进选出 非公平锁与公平锁
		ExtHashMap extHashMap = new ExtHashMap<String, String>();
		extHashMap.put("1号", "1号");// 0
		extHashMap.put("2号", "1号");// 1
		extHashMap.put("3号", "1号");// 2
		extHashMap.put("4号", "1号");// 3
		extHashMap.put("6号", "1号");// 4
		extHashMap.put("7号", "1号");
		extHashMap.put("14号", "1号");
		System.out.println("修改3号之前...");
		extHashMap.print();
		System.out.println("修改3号之后...");
		// 修改3号之前
		extHashMap.put("3号", "123");
		extHashMap.print();
		// 修改3号之后
		System.out.println(extHashMap.get("3号"));
		// System.out.println("扩容之前获取数据:" + extHashMap.get("1号"));
		// extHashMap.print();
		// System.out.println();
		// // extHashMap.put(14 + "号", 14 + "号");
		// // extHashMap.put(1 + "号", "4444号");
		// System.out.println("扩容之后获取数据:" + extHashMap.get("1号"));
		// extHashMap.print();

	}

}
