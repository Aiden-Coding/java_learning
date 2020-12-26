package com.itmayiedu;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test0008 {

	public static void main(String[] args) {
		// 可固定长度的线程池   核心线程数 为3 最多创建3个線程
		ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(3);
		for (int i = 0; i < 10; i++) {
			final int temp = i;
			// execute方法作用： 执行任务
			newFixedThreadPool.execute(new Runnable() {

				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName() + ",i:" + temp);

				}
			});
		}
	}

}
