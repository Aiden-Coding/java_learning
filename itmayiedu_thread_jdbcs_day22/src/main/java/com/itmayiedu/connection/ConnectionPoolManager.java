package com.itmayiedu.connection;

import java.sql.Connection;

public class ConnectionPoolManager {
	private static DbBean dbBean = new DbBean();
	private static ConnectionPool connectionPool = new ConnectionPool(dbBean);

	// 获取连接
	public static Connection getConnection() {
		return connectionPool.getConnection();
	}

	// 释放连接
	public static void releaseConnection(Connection connection) {
		connectionPool.releaseConnection(connection);
	}
}
