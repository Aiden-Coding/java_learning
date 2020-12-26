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
	 * 声明两个私有的int属性，分别命名为first和last。这两个属性将决定任务执行时对产品的分块
	 */
	private int first;
	private int last;
	/**
	 * 声明一个名为increment的私有double属性，用来存储产品价格的增加额。
	 */
	private double increment;

	public Task(List<Product> products, int first, int last, double increment) {
		this.products = products;
		this.first = first;
		this.last = last;
		this.increment = increment;
	}

	/**
	 * 这个方法用来更新在产品列表中处于first和last属性之间的产品。
	 */
	private void updatePrices() {

		for (int i = first; i < last; i++) {

			Product product = products.get(i);

			product.setPrice(product.getPrice() * (1 + increment));

		}

	}

	@Override
	protected void compute() {
		// 如果last和first属性值的差异小于10（一个任务只能更新少于10件产品的价格），则调用updatePrices()方法增加这些产品的价格。
		if (last - first < 10) {

			updatePrices();

		} else {// 如果last和first属性值的差异大于或等于10，
				// 就创建两个新的Task对象，一个处理前一半的产品，
				// 另一个处理后一半的产品，然后调用ForkJoinPool的invokeAll()方法来执行这两个新的任务

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

		// 调用execute()方法执行任务。
		forkJoinPool.execute(task);

		// 显示关于线程池演变的信息，每5毫秒在控制台上输出线程池的一些参数值，直到任务执行结束
		do {
			System.out.println(" Main : Thread Count : " + forkJoinPool.getActiveThreadCount());// 打印运行的线程数

			System.out.println(" Main : Thread Steal : " + forkJoinPool.getStealCount());// 窃取的线程数

			System.out.println(" Main :  Parallelism : " + forkJoinPool.getParallelism()); // 并发
			try {

				TimeUnit.MILLISECONDS.sleep(5);

			} catch (InterruptedException e) {

				e.printStackTrace();

			}

		} while (!task.isDone());

		// 调用shutdown()方法关闭线程池
		forkJoinPool.shutdown();

		// 调用isCompletedNormally()方法，检查任务是否已经完成并且没有错误
		if (task.isCompletedNormally()) {
			System.out.println("Main: The process has completed normally.\n");
		}

		// 在增加之后，所有产品的期望价格是15元。
		// 在控制台输出所有产品的名称和价格，
		// 如果产品的价格不是15元，就将产品信息打印出来，
		// 以便确认所有的产品价格都正确地增加了
		for (int i = 0; i < products.size(); i++) {

			Product product = products.get(i);

			if (product.getPrice() != 15) {

				System.out.printf("Product %s: %f\n", product.getName(), product.getPrice());

			}

		}

		System.out.println("Main: End of the program.\n");
	}
}