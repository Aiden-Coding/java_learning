package com.itmayiedu.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Vector;

/**
 * 白话文描述 数据库连接池实现原理 <br>
 * ####核心参数######<br>
 * 1.空闲线程 容器 没有被使用的连接存放 2.活动线程 容器正在使用的连接<br>
 * ###核心步骤#####<br>
 * 2.1 初始化线程池(初始化空闲线程)<br>
 * 3.1 调用getConnection方法 --- 获取连接 <br>
 * ####3.1.1 先去freeConnection获取当前连接,存放在activeConnection<br>
 * 4.1 调用releaseConnection方法 ----释放连接----资源回收<br>
 * ####4.1.1 获取activeConnection集合连接,转移到 freeConnection集合中<br>
 * 作者: 每特教育-余胜军<br>
 * 联系方式:QQ644064779|WWW.itmayiedu.com<br>
 */

public class ConnectionPool implements IConnectionPool {
	// 使用线程安全的集合 空闲线程 容器 没有被使用的连接存放
	private List<Connection> freeConnection = new Vector<Connection>();
	// 使用线程安全的集合 活动线程 容器 容器正在使用的连接
	private List<Connection> activeConnection = new Vector<Connection>();
	private DbBean dbBean;

	private int countConne = 0;

	public ConnectionPool(DbBean dbBean) {
		// 获取配置文件信息
		this.dbBean = dbBean;
		init();
	}

	// 初始化线程池(初始化空闲线程)
	private void init() {
		if (dbBean == null) {
			return;// 注意最好抛出异常
		}
		// 1.获取初始化连接数
		for (int i = 0; i < dbBean.getInitConnections(); i++) {
			// 2.创建Connection连接
			Connection newConnection = newConnection();
			if (newConnection != null) {
				// 3.存放在freeConnection集合
				freeConnection.add(newConnection);
			}
		}

	}

	// 创建Connection连接
	private synchronized Connection newConnection() {
		try {
			Class.forName(dbBean.getDriverName());
			Connection connection = DriverManager.getConnection(dbBean.getUrl(), dbBean.getUserName(),
					dbBean.getPassword());
			countConne++;
			return connection;
		} catch (Exception e) {
			return null;
		}

	}

	// 调用getConnection方法 --- 获取连接
	public synchronized Connection getConnection() {

		try {
			Connection connection = null;
			// 思考：怎么知道当前创建的连接>最大连接数
			if (countConne < dbBean.getMaxActiveConnections()) {
				// 小于最大活动连接数
				// 1.判断空闲线程是否有数据
				if (freeConnection.size() > 0) {
					// 空闲线程有存在连接
					// ==freeConnection.get(0);freeConnection.remove(0)
					// 拿到在删除
					connection = freeConnection.remove(0);
				} else {
					// 创建新的连接
					connection = newConnection();
				}
				// 判断连接是否可用
				boolean available = isAvailable(connection);
				if (available) {
					// 存放在活动线程池
					activeConnection.add(connection);
					countConne++;
				} else {
					countConne--;
					connection = getConnection();// 怎么使用重试？ 递归算法
				}

			} else {
				// 大于最大活动连接数，进行等待
				wait(dbBean.getConnTimeOut());
				// 重试
				connection = getConnection();
			}
			return connection;
		} catch (Exception e) {
			return null;
		}

	}

	// 判断连接是否可用
	public boolean isAvailable(Connection connection) {
		try {
			if (connection == null || connection.isClosed()) {
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return true;

	}

	// 释放连接 回收
	public synchronized void releaseConnection(Connection connection) {
		try {
			// 1.判断连接是否可用
			if (isAvailable(connection)) {
				// 2.判断空闲线程是否已满
				if (freeConnection.size() < dbBean.getMaxConnections()) {
					// 空闲线程没有满
					freeConnection.add(connection);// 回收连接
				} else {
					// 空闲线程已经满
					connection.close();
				}
				activeConnection.remove(connection);
				countConne--;
				notifyAll();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}

//
/**
 * 数据库连接池实现原理实现思路<br>
 * ###1.核心参数:空闲线程池(最小空闲连接数、最大空闲连接数)、活动线程池(最小空闲连接数、最大空闲连接数)、初始化连接数、允许最大连接数<br>
 * ######1.1:空闲连接:没有被使用的连接<br>
 * ######1.2:活动连接:正在被使用的连接<br>
 * ###2.初始化操作<br>
 * #######2.1 空闲连接容器和活动连接容器<br>
 * ###3.获取连接操作<br>
 * ###4.关闭连接<br>
 * 作者: 每特教育-余胜军<br>
 * 联系方式:QQ644064779F|WWW.itmayiedu.com<br>
 */
//public class ConnectionPool implements IConnectionPool {
//  // 连接池信息配置
//  private DbBean dbBean;
//  // 空闲连接容器
//  private List<Connection> freeConnection = new Vector<Connection>();
//  // 活动连接数
//  private List<Connection> activeConnection = new Vector<Connection>();
//  // 记录总连接数
//  private int activeConneCount = 0;
//  // 记录当前线程和连接绑定
//  private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();
//
//  public ConnectionPool(DbBean dbBean) {
//    this.dbBean = dbBean;
//    init();
//  }
//
//  // 初始化连接
//  public void init() {
//    try {
//      // 初始化连接
//      for (int i = 0; i < dbBean.getInitConnections(); i++) {
//        // 获取新的连接,存放在空闲线程池中
//        Connection newConnection = newConnection();
//        if (newConnection != null) {
//          freeConnection.add(newConnection);
//        }
//      }
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
//  }
//
//  public Connection newConnection() throws ClassNotFoundException, SQLException {
//    Connection conn = null;
//    if (dbBean != null) {
//      Class.forName(dbBean.getDriverName());
//      conn = DriverManager.getConnection(dbBean.getUrl(), dbBean.getUserName(), dbBean.getPassword());
//      // 连接总数++
//      activeConneCount++;
//    }
//    return conn;
//  }
//
//  // 获取连接
//  public synchronized Connection getConnection() {
//    Connection connection = null;
//    try {
//      // 判断连接数是否大于最大连接数
//      if (activeConneCount < this.dbBean.getMaxActiveConnections()) {
//        // 判断空闲连接数是否存在连接
//        if (freeConnection.size() > 0) {
//          // 删除当前空闲线程
//          connection = freeConnection.remove(0);
//        } else {
//          // 空闲连接数没有连接,创建连接
//          connection = newConnection();
//          activeConneCount++;
//        }
//        if (isAvailable(connection)) {
//          // 如果连接可以用,存入活动线程池中.
//          activeConnection.add(connection);
//        } else {
//          activeConneCount--;
//          connection = getConnection();// 递归调用
//        }
//      } else {
//        // 如果大于最大连接数,则进行等待重新获取连接
//        wait(dbBean.getConnTimeOut());
//        connection = getConnection();// 递归调用
//      }
//    } catch (Exception e) {
//
//    }
//    // 存放在threadLocal 中
//    threadLocal.set(connection);
//    return connection;
//  }
//
//  // 获取当前线程绑定连接
//  public Connection getCurrentConnection() {
//    try {
//      // 从当前线程中获取线程连接
//      Connection connection = threadLocal.get();
//      if (!isAvailable(connection)) {
//        connection = getConnection();
//      }
//      return connection;
//    } catch (Exception e) {
//      return null;
//    }
//  }
//
//  public synchronized void releaseConnection(Connection connection) {
//    if (isAvailable(connection)) {
//      // 如果空闲连接数,小于最大连接数,则收回该连接
//      if (freeConnection.size() < dbBean.getMaxConnections()) {
//        freeConnection.add(connection);
//      } else {
//        try {
//          // 已经达到最大连接数量,关闭当前连接
//          connection.close();
//        } catch (Exception e) {
//          // TODO: handle exception
//        }
//      }
//      activeConnection.remove(connection);
//      activeConneCount--;
//      threadLocal.remove();
//      notifyAll();
//    }
//
//  }
//
//  // 判断连接是否可以用
//  public boolean isAvailable(Connection connection) {
//    try {
//      if (connection == null || connection.isClosed()) {
//        return false;
//      }
//    } catch (Exception e) {
//
//    }
//    return true;
//  }
//
//}

