package com.itmayiedu;

class Res {
	public static Integer count = 0;
	public static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>() {
		protected Integer initialValue() {
			return 0;
		};
	};

	public Integer getNum() {
		int count = threadLocal.get() + 1;
		threadLocal.set(count);
		return count;
	}
}

public class Test006 extends Thread {

	private Res res;

	public Test006(Res res) {
		this.res = res;
	}

	@Override
	public void run() {
		for (int i = 0; i < 3; i++) {
			System.out.println(Thread.currentThread().getName() + "," + res.getNum());
		}
	}

	public static void main(String[] args) {
		Res res = new Res();
		Test006 t1 = new Test006(res);
		Test006 t2 = new Test006(res);

		t1.start();
		t2.start();
	}

}
