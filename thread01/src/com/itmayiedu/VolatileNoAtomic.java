
package com.itmayiedu;

import java.util.concurrent.atomic.AtomicInteger;

public class VolatileNoAtomic extends Thread {
	//private static volatile int count;
	private static AtomicInteger atomicInteger= new AtomicInteger(0);
	@Override
	public void run() {
		for (int i = 0; i < 1000; i++) {
//			count++;
			atomicInteger.incrementAndGet();
		}
		System.out.println(getName()+","+atomicInteger.get());
	}
	public static void main(String[] args) {
		VolatileNoAtomic[] volatileNoAtomic=  new VolatileNoAtomic[10];
		for (int i = 0; i < volatileNoAtomic.length; i++) {
			volatileNoAtomic[i]=new VolatileNoAtomic();
		}
		for (VolatileNoAtomic vl : volatileNoAtomic) {
			vl.start();
		}
	}

}
