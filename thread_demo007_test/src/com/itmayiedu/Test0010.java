package com.itmayiedu;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Test0010 {

	public static void main(String[] args) {
		//�����߳�
		ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
		for (int i = 0; i < 100; i++) {
			final int temp = i;
			// execute�������ã� ִ������
			newSingleThreadExecutor.execute(new Runnable() {

				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName() + ",i:" + temp);

				}
			});

		}
	}

}
