
package com.test.sync;

import java.util.concurrent.atomic.AtomicReference;

public class Test002 {

	public static void main(String[] args) {
		AtomicReference<Integer> atomicReference = new AtomicReference<Integer>();
		atomicReference.set(1);
		System.out.println(atomicReference.get());
	}

}
