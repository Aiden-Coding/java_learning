package com.itmayiedu;

import java.util.concurrent.ConcurrentLinkedQueue;

public class Test0001 {

	public static void main(String[] args) {
		// ������ʽ�÷� �޽����
		ConcurrentLinkedQueue concurrentLinkedQueue = new ConcurrentLinkedQueue<String>();
		concurrentLinkedQueue.offer("yushengjun");
		concurrentLinkedQueue.offer("zhangsan");
		// ��ȡ����������Ϣ poll ��ȡ����֮�� ��ɾ��������Ϣ �Ƴ�
		System.out.println(concurrentLinkedQueue.peek());
		System.out.println(concurrentLinkedQueue.size());
	}

}
