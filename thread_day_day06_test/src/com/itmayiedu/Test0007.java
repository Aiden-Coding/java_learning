package com.itmayiedu;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Test0007 {

	public static void main(String[] args) {
		// Executors.newCachedThreadPool();
		//提交一个任务到线程池中，线程池的处理流程如下：
		//1、判断线程池里的核心线程是否都在执行任务，如果不是（核心线程空闲或者还有核心线程没有被创建）则创建一个新的工作线程来执行任务。如果核心线程都在执行任务，则进入下个流程。
		//2、线程池判断工作队列是否已满，如果工作队列没有满，则将新提交的任务存储在这个工作队列里。如果工作队列满了，则进入下个流程。
		//3、判断线程池里的线程是否都处于工作状态，如果没有，则创建一个新的工作线程来执行任务。如果已经满了，则交给饱和策略来处理这个任务。

		ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 2, 60L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(3));
		for (int i = 1; i <= 6; i++) {
			TaskThred t1 = new TaskThred("任务" + i);
			executor.execute(t1);
		}
		executor.shutdown();
	}
}

class TaskThred implements Runnable {
	private String taskName;

	public TaskThred(String taskName) {
		this.taskName = taskName;
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName()+taskName);
	}

}
