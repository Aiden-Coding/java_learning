
package com.itmayiedu;

public class Demo004 {
	private static int count = 0;

	public static void count() {
		try {
	         count++;
			count();
		} catch (Throwable  e) {
			System.out.println("最大深度i:"+count);
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
//		for (int i = 0; i <10793; i++) {
			count();
//		}
	}

}
