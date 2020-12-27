package com.itmayiedu.db;

/**
 * 数据库连接池信息 <br>
 * 作者: 每特教育-余胜军<br>
 * 联系方式:QQ644064779|WWW.itmayiedu.com<br>
 */
public class PoolConfig {

	private String driverName;
	private String url;
	private String userName;
	private String passWord;

	/**
	 * 空闲连接数、空闲最大连接数、初始化连接数
	 */
	private int minCon = 5;
	private int maxCon = 5;
	private int initCon = 5;
	/**
	 * 连接池允许最大活动连接数
	 */
	private int maxActiveConn = 10;
	/**
	 * 连接不够时,等待时间,单位为毫秒
	 */
	private int waitTime = 1000;

}
