
package com.itmayiedu;

class ThreadTrain1 implements Runnable {
	private int count = 100;
	private static Object oj = new Object();

	@Override
	public void run() {
		while (count > 0) {
			try {
				Thread.sleep(50);
			} catch (Exception e) {
				// TODO: handle exception
			}
			sale();
		}
	}

	public synchronized void sale() {
		// ǰ�� ���߳̽���ʹ�á�����߳�ֻ���õ�һ������
		// ��ֻ֤����һ���߳� ��ִ�� ȱ��Ч�ʽ���
//		 synchronized (oj) {
		if (count > 0) {
			System.out.println(Thread.currentThread().getName() + ",���۵�" + (100 - count + 1) + "Ʊ");
			count--;
		}
//		 }
	}
}

public class ThreadDemo {
	public static void main(String[] args) {
		ThreadTrain1 threadTrain1 = new ThreadTrain1();
		Thread t1 = new Thread(threadTrain1, "�ٺŴ���");
		Thread t2 = new Thread(threadTrain1, "�ںŴ���");
		t1.start();
		t2.start();
	}
}
