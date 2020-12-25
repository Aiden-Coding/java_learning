package com.itmayiedu;

public class ThreadTrain4 implements Runnable {
	private int trainCount = 1000;
	private boolean flag = true;
	private Object oj = new Object();

	@Override
	public void run() {
		if (flag) {
			while (trainCount > 0) {

				synchronized (oj) {
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
			if (trainCount > 0) {
				try {
					Thread.sleep(50);
				} catch (Exception e) {

				}
				System.out.println(Thread.currentThread().getName() + ",���۵�" + (1000 - trainCount + 1) + "��Ʊ");
				trainCount--;
			}
		}

	}

	public static void main(String[] args) throws InterruptedException {
		ThreadTrain4 threadTrain = new ThreadTrain4();
		Thread t1 = new Thread(threadTrain, "�ٺ�");
		Thread t2 = new Thread(threadTrain, "�ں�");
		t1.start();
		Thread.sleep(40);
		threadTrain.flag = false;
		t2.start();
	}

}
