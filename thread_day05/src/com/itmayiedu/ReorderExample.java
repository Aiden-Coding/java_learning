package com.itmayiedu;

class ReorderExample {
	volatile int a = 0;
	volatile boolean flag = false;

	// д���߳�
	public void writer() {
		flag = true; // 2
		a = 1; // 1
		
	}
	// 1.1�д����2�д���û���κ�������ϵ
	// ��ȡ���߳�
	public void reader() {
		if (flag) { // 3
			int i = a * a; // 4  0

		}
	}
}
