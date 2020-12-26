package com.itmayiedu;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Test0009 {

	public static void main(String[] args) {
		// 可定时线程池 3核心线程数
		ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(3);
		for (int i = 0; i < 100; i++) {
			final int temp = i;
			// execute方法作用： 执行任务
			newScheduledThreadPool.schedule(new Runnable() {

				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName() + ",i:" + temp);

				}
			}, 3, TimeUnit.SECONDS);

		}
	}

}
