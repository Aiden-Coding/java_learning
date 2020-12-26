package com.itmayiedu;

import java.util.concurrent.ConcurrentLinkedQueue;

public class Test0001 {

	public static void main(String[] args) {
		// 非阻塞式用法 无界队列
		ConcurrentLinkedQueue concurrentLinkedQueue = new ConcurrentLinkedQueue<String>();
		concurrentLinkedQueue.offer("yushengjun");
		concurrentLinkedQueue.offer("zhangsan");
		// 获取单个队列信息 poll 获取队列之后 会删除队列信息 移除
		System.out.println(concurrentLinkedQueue.peek());
		System.out.println(concurrentLinkedQueue.size());
	}

}
