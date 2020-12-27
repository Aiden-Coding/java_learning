package com.mayikt;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OrderService {

	// 基于uuid 生成订单号
	public static void uuidOrder() {
		ExecutorService newCachedThreadPool = Executors.newFixedThreadPool(100);
		System.out.println("########start##############");
		for (int i = 0; i < 100; i++) {
			newCachedThreadPool.execute(new Runnable() {

				public void run() {
					String uuid = UUID.randomUUID().toString().replace("-", "");
					String insertSQL = "insert into orderNumber value('" + uuid + "');";
					// System.out.println(Thread.currentThread().getName() +
					// ",insertSQL:" + insertSQL);
					System.out.println(insertSQL);
				}
			});
		}

	}

	public static void main(String[] args) {
		uuidOrder();
	}

}
