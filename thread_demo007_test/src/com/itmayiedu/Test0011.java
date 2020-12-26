package com.itmayiedu;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

// 自定义线程池
public class Test0011 {

	public static void main(String[] args) {
		// 这个线程池表达含义 核心线程 1 最多创建线程1
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 2, 0L, TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<Runnable>(3));
		// 任务1线程创建线程并且执行
		threadPoolExecutor.execute(new TaskThread("任务1"));
		// 任务2存放在LinkedBlockingQueue
		threadPoolExecutor.execute(new TaskThread("任务2"));
		// 任务3存放在LinkedBlockingQueue
		threadPoolExecutor.execute(new TaskThread("任务3"));
		// 任务4存放在LinkedBlockingQueue
		threadPoolExecutor.execute(new TaskThread("任务4"));

		// ### 任务2、3、4都是存放在缓存队列中

		// // ###判断实际创建线程数<2 任务5存放在LinkedBlockingQueue 独立线程
		threadPoolExecutor.execute(new TaskThread("任务5"));
//		 // 任务6存放在LinkedBlockingQueue
//		 threadPoolExecutor.execute(new TaskThread("任务6"));
	}

}

// 线程执行任务
class TaskThread implements Runnable {
	private String taskName;

	public TaskThread(String taskName) {
		this.taskName = taskName;
	}

	public void run() {
		System.out.println(Thread.currentThread().getName() + taskName);
	}
}
