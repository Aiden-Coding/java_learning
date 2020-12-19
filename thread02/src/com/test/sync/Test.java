package com.test.sync;

import java.util.concurrent.atomic.AtomicReference;

import java.util.concurrent.atomic.AtomicReference;

import java.util.concurrent.atomic.AtomicReference;

import java.util.concurrent.atomic.AtomicReference;

class SpinLock {
	// java��ԭ�ӣ�CAS������
	AtomicReference<Thread> owner = new AtomicReference<Thread>();// �������������̶߳���
	private int count;

	public void lock() {
		Thread cur = Thread.currentThread();
		// lock������owner����Ϊ��ǰ�̣߳�����Ԥ��ԭ����ֵΪ�ա�unlock������owner����Ϊnull������Ԥ��ֵΪ��ǰ�̡߳����еڶ����̵߳���lock����ʱ����ownerֵ��Ϊ�գ�����ѭ��

		// һֱ��ִ�У�ֱ����һ���̵߳���unlock������owner����Ϊnull���ڶ����̲߳��ܽ����ٽ�����
		while (!owner.compareAndSet(null, cur)) {
		}
	}

	public void unLock() {
		Thread cur = Thread.currentThread();
		owner.compareAndSet(cur, null);
	}
}

public class Test implements Runnable {
	static int sum;
	private SpinLock lock;

	public Test(SpinLock lock) {
		this.lock = lock;
	}

	
	public static void main(String[] args) throws InterruptedException {
		SpinLock lock = new SpinLock();
		for (int i = 0; i < 100; i++) {
			Test test = new Test(lock);
			Thread t = new Thread(test);
			t.start();
		}

		Thread.currentThread().sleep(1000);
		System.out.println(sum);
	}

	@Override
	public void run() {
		this.lock.lock();
		sum++;
		this.lock.unLock();
	}
}