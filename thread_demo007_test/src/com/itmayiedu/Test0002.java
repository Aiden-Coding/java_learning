package com.itmayiedu;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Test0002 {

	public static void main(String[] args) throws InterruptedException {
		// 阻塞式队列
		ArrayBlockingQueue<Object> arrayBlockingQueue = new ArrayBlockingQueue<>(3);
		// 沒有加超时时间都是为非阻塞式
		arrayBlockingQueue.offer("张三");
		arrayBlockingQueue.offer("李四");
		// 添加阻塞式队列
		arrayBlockingQueue.offer("余胜军", 3, TimeUnit.SECONDS);
//		arrayBlockingQueue.offer("15k", 3, TimeUnit.SECONDS);
		// 获取单个队列信息,并且会删除该队列
		System.out.println(arrayBlockingQueue.poll(3,TimeUnit.SECONDS));
		System.out.println(arrayBlockingQueue.poll(3,TimeUnit.SECONDS));
		System.out.println(arrayBlockingQueue.poll(3,TimeUnit.SECONDS));
		System.out.println(arrayBlockingQueue.poll(3,TimeUnit.SECONDS));
		System.out.println(arrayBlockingQueue.size());

	}

}
