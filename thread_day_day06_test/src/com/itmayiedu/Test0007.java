package com.itmayiedu;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Test0007 {

	public static void main(String[] args) {
		// Executors.newCachedThreadPool();
		//�ύһ�������̳߳��У��̳߳صĴ����������£�
		//1���ж��̳߳���ĺ����߳��Ƿ���ִ������������ǣ������߳̿��л��߻��к����߳�û�б��������򴴽�һ���µĹ����߳���ִ��������������̶߳���ִ������������¸����̡�
		//2���̳߳��жϹ��������Ƿ������������������û�����������ύ������洢����������������������������ˣ�������¸����̡�
		//3���ж��̳߳�����߳��Ƿ񶼴��ڹ���״̬�����û�У��򴴽�һ���µĹ����߳���ִ����������Ѿ����ˣ��򽻸����Ͳ����������������

		ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 2, 60L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(3));
		for (int i = 1; i <= 6; i++) {
			TaskThred t1 = new TaskThred("����" + i);
			executor.execute(t1);
		}
		executor.shutdown();
	}
}

class TaskThred implements Runnable {
	private String taskName;

	public TaskThred(String taskName) {
		this.taskName = taskName;
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName()+taskName);
	}

}
