package com.itmayiedu;

public class Test0004 {

	public static void main(String[] args) throws InterruptedException {
		while (true) {
			Thread.sleep(3000);
			System.gc();
		}
	}

}
