
package com.itmayiedu;

class ThreadTrain3 implements Runnable {
	private static int count = 100;
	public boolean flag = true;
	private static Object oj = new Object();

	@Override
	public void run() {
		if (flag) {

			while (true) {

				synchronized (oj) {
					sale();
				}

			}

		} else {
			while (true) {
				sale();
			}
		}

	}

	public static synchronized void sale() {

		// 前提 多线程进行使用、多个线程只能拿到一把锁。
		// 保证只能让一个线程 在执行 缺点效率降低
		synchronized (oj) {
			if (count > 0) {
				try {
					Thread.sleep(50);
				} catch (Exception e) {
					// TODO: handle exception
				}
				System.out.println(Thread.currentThread().getName() + ",出售第" + (100 - count + 1) + "票");
				count--;
			}
		}
	}
}

public class ThreadDemo3 {
	public static void main(String[] args) throws InterruptedException {
		ThreadTrain3 threadTrain1 = new ThreadTrain3();
		Thread t1 = new Thread(threadTrain1, "①号窗口");
		Thread t2 = new Thread(threadTrain1, "②号窗口");
		t1.start();
		Thread.sleep(40);
		threadTrain1.flag = false;
		t2.start();
	}
}
