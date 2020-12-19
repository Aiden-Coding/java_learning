
package com.itmayiedu;

import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

public abstract class Demo005 {

	public static void main(String[] args) {
		List<Object> listObject = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			System.out.println("i:"+i);
			listObject.add(new byte[1*1024*1024]);
		}
		System.out.println("创建完毕!");
	}

}
