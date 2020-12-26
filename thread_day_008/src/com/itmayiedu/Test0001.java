package com.itmayiedu;

import java.util.concurrent.atomic.AtomicInteger;

import sun.misc.Unsafe;

public class Test0001 {
	private static MyLock mylock = new MyLock();

	public static void main(String[] args) {

		mylock.lock();
		mylock.unlock();
	}

	public void add() {

	}

}
