
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
		// 前提 多线程进行使用、多个线程只能拿到一把锁。
		// 保证只能让一个线程 在执行 缺点效率降低
//		 synchronized (oj) {
		if (count > 0) {
			System.out.println(Thread.currentThread().getName() + ",出售第" + (100 - count + 1) + "票");
			count--;
		}
//		 }
	}
}

public class ThreadDemo {
	public static void main(String[] args) {
		ThreadTrain1 threadTrain1 = new ThreadTrain1();
		Thread t1 = new Thread(threadTrain1, "①号窗口");
		Thread t2 = new Thread(threadTrain1, "②号窗口");
		t1.start();
		t2.start();
	}
}
