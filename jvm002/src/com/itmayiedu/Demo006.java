
package com.itmayiedu;

//什么是栈溢出？ 无线递归调用
public class Demo006 {
	private static int count = 0;

	public static void getCount() {
		try {
			count++;
		 getCount();
		} catch (  Throwable e) {
			System.out.println("最大的深度...." + count);
			e.printStackTrace();
		}

	}
   //栈溢出 是方法中递归调用 不是循环调用方法 发生
	public static void main(String[] args) {
//		for (int i = 0; i < 10000; i++) {
			getCount();
		

	}
}
