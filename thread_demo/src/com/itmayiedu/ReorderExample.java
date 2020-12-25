package com.itmayiedu;

class ReorderExample {
	int a = 0;
	boolean flag = false;

	public void writer() {
		a = 1; // 1
		flag = true; // 2
		System.out.println("writer");
	}

	public void reader() {
		if (flag) { // 3
			int i = a * a; // 4
			System.out.println("i:" + i);
		}
		System.out.println("reader");
	}

	public static void main(String[] args) {
		ReorderExample reorderExample = new ReorderExample();
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				reorderExample.writer();
			}
		});
		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				reorderExample.reader();
			}
		});
		t1.start();
		t2.start();

	}
}