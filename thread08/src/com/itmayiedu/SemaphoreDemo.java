
package com.itmayiedu;

import java.util.concurrent.Semaphore;

public class SemaphoreDemo {

	public static void main(String[] args) {
		// ����һ��������ֵΪ5���ź�������
		// ֻ��5���߳�ͬʱ����
		Semaphore semp = new Semaphore(5);

		try {
			// �������
			semp.acquire();
			try {
				// ҵ���߼�
				System.out.println("ҵ���߼�");
			} catch (Exception e) {

			} finally {
				// �ͷ����
				System.out.println("�ͷ����");
				semp.release();
			}
		} catch (InterruptedException e) {

		}
	}

}
