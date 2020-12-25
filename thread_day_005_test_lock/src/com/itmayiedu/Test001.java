package com.itmayiedu;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Res {
	public String userSex;
	public String userName;
	public boolean flag = false;
	public Lock lock = new ReentrantLock();
	public Condition condition = lock.newCondition();
}

class IntThread extends Thread {
	private Res res;

	public IntThread(Res res) {
		this.res = res;
	}

	@Override
	public void run() {
		int count = 0;
		while (true) {
			try {
				res.lock.lock();
				if (res.flag) {
					res.condition.await();
				}

				if (count == 0) {
					res.userName = "小红";
					res.userSex = "女";
				} else {
					res.userName = "小军";
					res.userSex = "男";
				}
				count = (count + 1) % 2;
				res.flag = true;
				res.condition.signal();
			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				res.lock.unlock();
			}

		}
	}

}

class OutThread extends Thread {
	private Res res;

	public OutThread(Res res) {
		this.res = res;
	}

	@Override
	public void run() {
		while (true) {
			synchronized (res) {
				try {
					res.lock.lock();
					if (!res.flag) {
						res.condition.await();
					}
					Thread.sleep(1000);
					System.out.println(res.userName + "," + res.userSex);
					res.flag = false;
					res.condition.signal();
				} catch (Exception e) {
					// TODO: handle exception
				} finally {
					res.lock.unlock();
				}

			}

		}
	}

}

public class Test001 {

	public static void main(String[] args) {

		Res res = new Res();
		IntThread intThread = new IntThread(res);
		OutThread outThread = new OutThread(res);
		intThread.start();
		outThread.start();
	}

}
