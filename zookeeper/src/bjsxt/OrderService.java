
package bjsxt;

import java.util.concurrent.CountDownLatch;

public class OrderService implements Runnable {
	private static int count = 100;
	private Lock lock = new ZookeeperDistrbuteLock();
	private OrderNumGenerator ogn = new OrderNumGenerator();
	private static CountDownLatch countDownLatch = new CountDownLatch(count);

	public void run() {
		createOrder();
	}

	public void createOrder() {
		 lock.getLock();
		try {
			Thread.sleep(100);
			String orderNum = ogn.orderNum();
			System.out.println(Thread.currentThread().getName() + "获取订单号:" + orderNum);
		} catch (Exception e) {
			// TODO: handle exception
		}

		 lock.unLock();
	}

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			new Thread(new OrderService()).start();
			countDownLatch.countDown();
		}
	}

}
