package com.itmayiedu.connection;

import java.sql.Connection;

public interface IConnectionPool {

	// 获取jdbc连接请求
	public Connection getConnection();

	// 释放jdbc连接请求
	public void releaseConnection(Connection connection);
}
