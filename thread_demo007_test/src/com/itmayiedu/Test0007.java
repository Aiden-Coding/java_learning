package com.itmayiedu;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//�̳߳ػ���ʹ�÷�ʽ
public class Test0007 {
	// �߳�����������ʲô��
	public static void main(String[] args) {
		// �����߳�(���ַ�ʽ) 1.�ɻ��桢��������ʱ������
		ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
		for (int i = 0; i < 10; i++) {
			final int temp = i;
			// execute�������ã� ִ������
			newCachedThreadPool.execute(new Runnable() {

				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName() + ",i:" + temp);

				}
			});
		}

	}

}
