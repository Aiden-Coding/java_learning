
package com.itmayiedu;

import java.util.concurrent.CountDownLatch;

public class Test002 {

	public static void main(String[] args) throws InterruptedException {
	   CountDownLatch countDownLatch = new CountDownLatch(1);
		new Thread(new Runnable() {
			public void run() {
				System.out.println("�������߳̿�ʼִ������......");
				try {
					//���߳����ڴ�������
					Thread.sleep(10);
				} catch (Exception e) {
					// TODO: handle exception
				}
				countDownLatch.countDown();//ÿ�μ�1
				System.out.println("�������߳̿�ʼִ������......");
			}
		}).start();;
		new Thread(new Runnable() {
			public void run() {
				System.out.println("�������߳̿�ʼִ������......");
				try {
					//���߳����ڴ�������
					Thread.sleep(10);
				} catch (Exception e) {
					// TODO: handle exception
				}
				countDownLatch.countDown();//ÿ�μ�1
				System.out.println("�������߳̿�ʼִ������......");
			}
		}).start();;
		countDownLatch.await();//�����Ϊ0��ʱ��һ�µȴ�
		System.out.println("���߳̿�ʼִ������");
		for (int i = 0; i < 10; i++) {
			System.out.println("main i:"+i);
		}
	}

}
