package com.itmayiedu;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Test0002 {

	public static void main(String[] args) throws InterruptedException {
		// ����ʽ����
		ArrayBlockingQueue<Object> arrayBlockingQueue = new ArrayBlockingQueue<>(3);
		// �]�мӳ�ʱʱ�䶼��Ϊ������ʽ
		arrayBlockingQueue.offer("����");
		arrayBlockingQueue.offer("����");
		// �������ʽ����
		arrayBlockingQueue.offer("��ʤ��", 3, TimeUnit.SECONDS);
//		arrayBlockingQueue.offer("15k", 3, TimeUnit.SECONDS);
		// ��ȡ����������Ϣ,���һ�ɾ���ö���
		System.out.println(arrayBlockingQueue.poll(3,TimeUnit.SECONDS));
		System.out.println(arrayBlockingQueue.poll(3,TimeUnit.SECONDS));
		System.out.println(arrayBlockingQueue.poll(3,TimeUnit.SECONDS));
		System.out.println(arrayBlockingQueue.poll(3,TimeUnit.SECONDS));
		System.out.println(arrayBlockingQueue.size());

	}

}
