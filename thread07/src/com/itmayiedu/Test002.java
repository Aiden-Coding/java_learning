
package com.itmayiedu;

import java.util.concurrent.CountDownLatch;

public class Test002 {

	public static void main(String[] args) throws InterruptedException {
		System.out.println("�ȴ����߳�ִ�����...");
		CountDownLatch countDownLatch = new CountDownLatch(2);
		new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("���߳�," + Thread.currentThread().getName() + "��ʼִ��...");
				countDownLatch.countDown();// ÿ�μ�ȥ1
				System.out.println("���߳�," + Thread.currentThread().getName() + "����ִ��...");
			}
		}).start();
		new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("���߳�," + Thread.currentThread().getName() + "��ʼִ��...");
				countDownLatch.countDown();
				System.out.println("���߳�," + Thread.currentThread().getName() + "����ִ��...");
			}
		}).start();

		countDownLatch.await();// ���õ�ǰ�������߳����� countDown���Ϊ0, ������Ϊ����״̬
		System.out.println("�������߳�ִ�����....");
		System.out.println("�������߳�ִ��..");
	}

}
