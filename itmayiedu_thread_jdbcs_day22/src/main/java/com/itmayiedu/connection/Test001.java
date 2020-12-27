package com.itmayiedu.connection;

import java.sql.Connection;

public class Test001 {

	public static void main(String[] args) {

		DBThread dBThread = new DBThread();
		for (int i = 1; i <= 3; i++) {
			Thread thread = new Thread(dBThread, "用户线程" + i);
			thread.start();
		}

	}

}

class DBThread implements Runnable {

	public void run() {
		for (int i = 0; i < 10; i++) {
			Connection connection = ConnectionPoolManager.getConnection();
			System.out.println(Thread.currentThread().getName() + ",connection:" + connection);
			ConnectionPoolManager.releaseConnection(connection);
		}
	}

}
