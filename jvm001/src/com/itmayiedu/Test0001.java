
package com.itmayiedu;

import java.util.ArrayList;
import java.util.List;

public class Test0001 {

	public static void main(String[] args) {
		//-Xms1m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
		List<Object> listObject = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			System.out.println("i:" + i);
			byte[] bytes = new byte[1*1024*1024];
			listObject.add(bytes);
		}
		System.out.println("添加成功.....");

	}

}
