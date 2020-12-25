package com.itmayiedu;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test0002 {

	public static void main(String[] args) {
		// 控制并发数的线程池
		ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(3);
		for (int i = 0; i < 10; i++) {
			final int temp = i;
			newFixedThreadPool.execute(new Runnable() {

				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName() + "," + temp);
				}
			});
		}
	}

}
