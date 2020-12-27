package com.itmayiedu.connection;

import java.sql.Connection;

public interface IConnectionPool {

	// 获取连接
	public Connection getConnection();

	// 获取当前连接
	public Connection getCurrentConnection();
}
