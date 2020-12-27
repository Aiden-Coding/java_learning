package com.itmayiedu.connection;

import java.sql.Connection;

import com.itmayiedu.DbBean;

// 数据库连接池管理
public class ConnectionPoolManager {
	private static DbBean bBean = new DbBean();
	private static ConnectionPool connectionPool = new ConnectionPool(bBean);

	private ConnectionPoolManager() {
	}

	public static Connection getConnectionPool() {
		return connectionPool.getCurrentConnection();
	}

	public static void closeConnection(Connection connection) {
		connectionPool.releaseConnection(connection);
	}

}
