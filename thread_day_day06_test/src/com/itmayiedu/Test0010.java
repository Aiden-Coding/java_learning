package com.itmayiedu;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Test0010 {

	public static void main(String[] args) throws InterruptedException {
		ArrayBlockingQueue<Object> arrayBlockingQueue = new ArrayBlockingQueue<>(3);
		System.out.println(arrayBlockingQueue.poll(3,TimeUnit.SECONDS));
	}

}
