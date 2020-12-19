
package com.itmayiedu;

public class Demo001 {

	private static int count = 0;
	private  Demo001 demo01=new Demo001();

	public static void main(String[] args) {
		Demo001 demo01 = new Demo001();
		Demo001 demo02 = new Demo001();
		++demo01.count;
		++demo02.count;
		System.out.println(demo01.count);
	}

}
