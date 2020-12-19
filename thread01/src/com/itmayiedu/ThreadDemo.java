
package com.itmayiedu;

class Res {
	// private int count = 0;
	public static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>() {
		protected Integer initialValue() {

			return 0;
		};

	};

	public String getNumber() {
		int count = threadLocal.get() + 1;
		threadLocal.set(count);
		return count + "";
	}

}

public class ThreadDemo extends Thread {
	private Res res;

	public ThreadDemo(Res res) {
		this.res = res;
	}

	@Override
	public void run() {
		for (int i = 0; i < 3; i++) {
			System.out.println(getName() + ",i:" + i + ",res" + res.getNumber());
		}
	}

	public static void main(String[] args) {
		// Res res1 = new Res();
		// Res res2 = new Res();
		// Res res3 = new Res();
		Res res = new Res();
		ThreadDemo t1 = new ThreadDemo(res);
		ThreadDemo t2 = new ThreadDemo(res);
		ThreadDemo t3 = new ThreadDemo(res);
		t1.start();
		t2.start();
		t3.start();
	}

}
