package com.itmayiedu;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

public class Task extends RecursiveAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Product> products;
	/**
	 * ��������˽�е�int���ԣ��ֱ�����Ϊfirst��last�����������Խ���������ִ��ʱ�Բ�Ʒ�ķֿ�
	 */
	private int first;
	private int last;
	/**
	 * ����һ����Ϊincrement��˽��double���ԣ������洢��Ʒ�۸�����Ӷ
	 */
	private double increment;

	public Task(List<Product> products, int first, int last, double increment) {
		this.products = products;
		this.first = first;
		this.last = last;
		this.increment = increment;
	}

	/**
	 * ����������������ڲ�Ʒ�б��д���first��last����֮��Ĳ�Ʒ��
	 */
	private void updatePrices() {

		for (int i = first; i < last; i++) {

			Product product = products.get(i);

			product.setPrice(product.getPrice() * (1 + increment));

		}

	}

	@Override
	protected void compute() {
		// ���last��first����ֵ�Ĳ���С��10��һ������ֻ�ܸ�������10����Ʒ�ļ۸񣩣������updatePrices()����������Щ��Ʒ�ļ۸�
		if (last - first < 10) {

			updatePrices();

		} else {// ���last��first����ֵ�Ĳ�����ڻ����10��
				// �ʹ��������µ�Task����һ������ǰһ��Ĳ�Ʒ��
				// ��һ�������һ��Ĳ�Ʒ��Ȼ�����ForkJoinPool��invokeAll()������ִ���������µ�����

			int middle = (last + first) / 2;

			System.out.printf("Task: Pending tasks: %s\n ", getQueuedTaskCount());

			Task t1 = new Task(products, first, middle + 1, increment);

			Task t2 = new Task(products, middle + 1, last, increment);

			invokeAll(t1, t2);

		}
	}

	public static void main(String[] args) {

		ProductListGenerator productListGenerator = new ProductListGenerator();

		List<Product> products = productListGenerator.generate(1000);

		Task task = new Task(products, 0, products.size(), 0.5);

		ForkJoinPool forkJoinPool = new ForkJoinPool();

		// ����execute()����ִ������
		forkJoinPool.execute(task);

		// ��ʾ�����̳߳��ݱ����Ϣ��ÿ5�����ڿ���̨������̳߳ص�һЩ����ֵ��ֱ������ִ�н���
		do {
			System.out.println(" Main : Thread Count : " + forkJoinPool.getActiveThreadCount());// ��ӡ���е��߳���

			System.out.println(" Main : Thread Steal : " + forkJoinPool.getStealCount());// ��ȡ���߳���

			System.out.println(" Main :  Parallelism : " + forkJoinPool.getParallelism()); // ����
			try {

				TimeUnit.MILLISECONDS.sleep(5);

			} catch (InterruptedException e) {

				e.printStackTrace();

			}

		} while (!task.isDone());

		// ����shutdown()�����ر��̳߳�
		forkJoinPool.shutdown();

		// ����isCompletedNormally()��������������Ƿ��Ѿ���ɲ���û�д���
		if (task.isCompletedNormally()) {
			System.out.println("Main: The process has completed normally.\n");
		}

		// ������֮�����в�Ʒ�������۸���15Ԫ��
		// �ڿ���̨������в�Ʒ�����ƺͼ۸�
		// �����Ʒ�ļ۸���15Ԫ���ͽ���Ʒ��Ϣ��ӡ������
		// �Ա�ȷ�����еĲ�Ʒ�۸���ȷ��������
		for (int i = 0; i < products.size(); i++) {

			Product product = products.get(i);

			if (product.getPrice() != 15) {

				System.out.printf("Product %s: %f\n", product.getName(), product.getPrice());

			}

		}

		System.out.println("Main: End of the program.\n");
	}
}