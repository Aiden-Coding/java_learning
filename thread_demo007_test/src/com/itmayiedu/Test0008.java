package com.itmayiedu;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test0008 {

	public static void main(String[] args) {
		// �ɹ̶����ȵ��̳߳�   �����߳��� Ϊ3 ��ഴ��3������
		ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(3);
		for (int i = 0; i < 10; i++) {
			final int temp = i;
			// execute�������ã� ִ������
			newFixedThreadPool.execute(new Runnable() {

				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName() + ",i:" + temp);

				}
			});
		}
	}

}
