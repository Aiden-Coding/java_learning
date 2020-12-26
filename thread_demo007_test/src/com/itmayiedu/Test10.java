package com.itmayiedu;

import java.util.concurrent.atomic.AtomicInteger;

public class Test10 {

	static private AtomicInteger atomicInteger=new AtomicInteger();
	
	 public static void main(String[] args) {
		 int andSet = atomicInteger.getAndIncrement();
		 int andSet2= atomicInteger.getAndIncrement();;
		 System.out.println(andSet2);
	}
	
}
