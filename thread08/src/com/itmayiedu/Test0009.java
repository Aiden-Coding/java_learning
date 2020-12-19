
package com.itmayiedu;

import java.util.Hashtable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Test0009 {

	public static void main(String[] args) {
		Hashtable<Object, Object> hashtable = new Hashtable<>();

		// 无限大小线程池 jvm自动回收
		ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
		for (int i = 0; i < 10; i++) {
			final int temp = i;
			newCachedThreadPool.execute(new Runnable() {

				@Override
				public void run() {
					try {
						Thread.sleep(1000);
					} catch (Exception e) {
						// TODO: handle exception
					}
					System.out.println(Thread.currentThread().getId() + ",i:" + temp);

				}
			});
		}
		// ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(5);
		// for (int i = 0; i < 10; i++) {
		// final int temp = i;
		// newFixedThreadPool.execute(new Runnable() {
		//
		// @Override
		// public void run() {
		// System.out.println(Thread.currentThread().getId() + ",i:" + temp);
		//
		// }
		// });
		// }
		// ScheduledExecutorService newScheduledThreadPool =
		// Executors.newScheduledThreadPool(5);
		// for (int i = 0; i < 10; i++) {
		// final int temp = i;
		// newScheduledThreadPool.schedule(new Runnable() {
		// public void run() {
		// System.out.println("i:" + temp);
		// }
		// }, 3, TimeUnit.SECONDS);
		// }

		ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
		for (int i = 0; i < 10; i++) {
			final int index = i;
			newSingleThreadExecutor.execute(new Runnable() {

				@Override
				public void run() {
					System.out.println("index:" + index);
					try {
						Thread.sleep(200);
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			});
		}

		newSingleThreadExecutor.shutdown();
	}

}
