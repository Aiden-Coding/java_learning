package com.itmayiedu;

class Thread009 implements Runnable {
	private int trainCount = 100;
	private Object oj = new Object();
	public boolean flag = true;

	public void run() {

		if (flag) {
			while (trainCount > 0) {
				synchronized (oj) {
					try {
						Thread.sleep(10);
					} catch (Exception e) {
						// TODO: handle exception
					}
					sale();
				}

			}
		} else {
			while (trainCount > 0) {
				sale();
			}

		}

	}

	public synchronized void sale() {
		synchronized (oj) {
			try {
				Thread.sleep(10);
			} catch (Exception e) {

			}
			if (trainCount > 0) {
				System.out.println(Thread.currentThread().getName() + "," + "出售第" + (100 - trainCount + 1) + "票");
				trainCount--;
			}
		}
	}
}

public class Test009 {
	public static void main(String[] args) throws InterruptedException {
		Thread009 threadTrain = new Thread009();
		Thread t1 = new Thread(threadTrain, "窗口1");
		Thread t2 = new Thread(threadTrain, "窗口2");
		t1.start();
		Thread.sleep(40);
		threadTrain.flag = false;
		t2.start();

	}
}
