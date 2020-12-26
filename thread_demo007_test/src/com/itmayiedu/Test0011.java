package com.itmayiedu;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

// �Զ����̳߳�
public class Test0011 {

	public static void main(String[] args) {
		// ����̳߳ر�ﺬ�� �����߳� 1 ��ഴ���߳�1
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 2, 0L, TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<Runnable>(3));
		// ����1�̴߳����̲߳���ִ��
		threadPoolExecutor.execute(new TaskThread("����1"));
		// ����2�����LinkedBlockingQueue
		threadPoolExecutor.execute(new TaskThread("����2"));
		// ����3�����LinkedBlockingQueue
		threadPoolExecutor.execute(new TaskThread("����3"));
		// ����4�����LinkedBlockingQueue
		threadPoolExecutor.execute(new TaskThread("����4"));

		// ### ����2��3��4���Ǵ���ڻ��������

		// // ###�ж�ʵ�ʴ����߳���<2 ����5�����LinkedBlockingQueue �����߳�
		threadPoolExecutor.execute(new TaskThread("����5"));
//		 // ����6�����LinkedBlockingQueue
//		 threadPoolExecutor.execute(new TaskThread("����6"));
	}

}

// �߳�ִ������
class TaskThread implements Runnable {
	private String taskName;

	public TaskThread(String taskName) {
		this.taskName = taskName;
	}

	public void run() {
		System.out.println(Thread.currentThread().getName() + taskName);
	}
}
