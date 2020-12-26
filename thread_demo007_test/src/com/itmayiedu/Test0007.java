package com.itmayiedu;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//线程池基本使用方式
public class Test0007 {
	// 线程与任务区别什么？
	public static void main(String[] args) {
		// 创建线程(四种方式) 1.可缓存、定长、定时、单例
		ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
		for (int i = 0; i < 10; i++) {
			final int temp = i;
			// execute方法作用： 执行任务
			newCachedThreadPool.execute(new Runnable() {

				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName() + ",i:" + temp);

				}
			});
		}

	}

}
