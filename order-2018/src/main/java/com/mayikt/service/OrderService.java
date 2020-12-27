package com.mayikt.service;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OrderService {

	public static String order() {
		ExecutorService thread = Executors.newFixedThreadPool(100);
		for (int i = 0; i < 100; i++) {
			thread.execute(new Runnable() {

				public void run() {
					String orderId = UUID.randomUUID().toString();
					String inserSQL = "insert into orderNumber value('" + orderId + "');";
					System.out.println(inserSQL);
				}
			});
		}

		return null;
	}

	public static void main(String[] args) {
		order();
	}

}
