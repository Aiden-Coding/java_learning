package com.mayikt;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OrderService {

	public static String order() {
		ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(100);
		for (int i = 0; i < 100; i++) {
			newFixedThreadPool.execute(new Runnable() {
				public void run() {
					String orderId = UUID.randomUUID().toString().replace("-", "");
					String sqlInserSQL = "insert into orderNumber value('" + orderId + "');";
					System.out.println(sqlInserSQL);

				}
			});
		}

		return null;
	}

	// 网上讨论 uuid 是否会产生重复
	public static void main(String[] args) {
		System.out.println(order());
	}
	// uuid生成 应用场景：数据库主键 或者Token 不占用宽带
	// 数据库 递增 1 2 3 4 6 数据库集群 分表分库
	// 支付id 序列 32位

}
