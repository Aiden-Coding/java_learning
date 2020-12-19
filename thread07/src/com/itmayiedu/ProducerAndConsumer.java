
package com.itmayiedu;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import javax.lang.model.element.QualifiedNameable;

class ProducerThread implements Runnable {
	private BlockingQueue queue;
	private volatile boolean flag = true;
	private static AtomicInteger count = new AtomicInteger();
	public ProducerThread(BlockingQueue queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		try {
			System.out.println("�����߳�����...");
			while (flag) {
				System.out.println("������������....");
				String data = count.incrementAndGet()+"";
				// �����ݴ��������
				boolean offer = queue.offer(data, 2, TimeUnit.SECONDS);
				if (offer) {
					System.out.println("������,����" + data + "��������,�ɹ�.");
				} else {
					System.out.println("������,����" + data + "��������,ʧ��.");
				}
				Thread.sleep(1000);
			}
		} catch (Exception e) {

		} finally {
			System.out.println("�������˳��߳�");
		}

	}

	public void stop() {
		this.flag = false;
	}
}

class ConsumerThread implements Runnable {
	private BlockingQueue<String> queue;
	private volatile boolean flag = true;

	public ConsumerThread(BlockingQueue<String> queue) {
		this.queue = queue;

	}

	@Override
	public void run() {
		System.out.println("�����߳�����...");
		try {
			while (flag) {
				System.out.println("������,���ڴӶ����л�ȡ����..");
				String data = queue.poll(2, TimeUnit.SECONDS);
				if (data != null) {
					System.out.println("������,�õ������е�����data:" + data);
					Thread.sleep(1000);
				} else {
					System.out.println("������,����2��δ��ȡ������..");
					flag = false;
				}
		
				
			}
		} catch (Exception e) {
               e.printStackTrace();
		} finally {
			System.out.println("�������˳��߳�...");
		}
		
	}

}

public class ProducerAndConsumer {
	public static void main(String[] args) throws InterruptedException {
		BlockingQueue<String> queue = new LinkedBlockingQueue<String>(10);
		ProducerThread producerThread1 = new ProducerThread(queue);
		ProducerThread producerThread2 = new ProducerThread(queue);
		ConsumerThread consumerThread1 = new ConsumerThread(queue);
        Thread t1 = new Thread(producerThread1);
        Thread t2 = new Thread(producerThread2);
        Thread c1 = new Thread(consumerThread1);
        t1.start();
        t2.start();
        c1.start();

        // ִ��10s
        Thread.sleep(10 * 1000);
        producerThread1.stop();
        producerThread2.stop();
   
	}
}
