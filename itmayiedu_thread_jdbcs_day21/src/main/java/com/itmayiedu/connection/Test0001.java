package com.itmayiedu.connection;

import java.sql.Connection;

public class Test0001 {

	public static void main(String[] args) throws InterruptedException {
		ThreadConn threadConn = new ThreadConn();
		for (int i = 1; i <= 3; i++) {
			Thread thread = new Thread(threadConn, "线程" + i);
			thread.start();
		}
	}

}

class ThreadConn implements Runnable {

	public void run() {
		for (int i = 0; i < 10; i++) {
			Connection connection = ConnectionPoolManager.getConnectionPool();
			System.out.println(Thread.currentThread().getName() + "," + connection);
			ConnectionPoolManager.closeConnection(connection);
		}
	}

}