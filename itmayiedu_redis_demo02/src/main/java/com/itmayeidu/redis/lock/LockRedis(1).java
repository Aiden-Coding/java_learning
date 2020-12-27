/**
 * 功能说明:
 * 功能作者:
 * 创建日期:
 * 版权归属:每特教育|蚂蚁课堂所有 www.itmayiedu.com
 */
package com.itmayeidu.redis.lock;

import java.util.UUID;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 功能说明: <br>
 * 创建作者:每特教育-余胜军<br>
 * 创建时间:2018年8月30日 下午4:32:02<br>
 * 教育机构:每特教育|蚂蚁课堂<br>
 * 版权说明:上海每特教育科技有限公司版权所有<br>
 * 官方网站:www.itmayiedu.com|www.meitedu.com<br>
 * 联系方式:qq644064779<br>
 * 注意:本内容有每特教育学员共同研发,请尊重原创版权
 */
public class LockRedis {

	private JedisPool jedisPool;

	public LockRedis(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}

	/**
	 * redis 上锁方法 作者：余胜军
	 * 
	 * @param lockKey
	 *            锁的key<br>
	 * @param acquireTimeout
	 *            在没有上锁之前,获取锁的超时时间<br>
	 * @param timeOut
	 *            上锁成功后,锁的超时时间<br>
	 * @return
	 */
	public String lockWithTimeout(String lockKey, Long acquireTimeout, Long timeOut) {
		Jedis conn = null;
		String retIdentifierValue = null;
		try {
			// 1.建立redis连接
			conn = jedisPool.getResource();
			// 2.随机生成一个value
			String identifierValue = UUID.randomUUID().toString();
			// 3.定义锁的名称
			String lockName = "redis_lock" + lockKey;
			// 4.定义上锁成功之后,锁的超时时间
			int expireLock = (int) (timeOut / 1000);
			// 5.定义在没有获取锁之前,锁的超时时间
			Long endTime = System.currentTimeMillis() + acquireTimeout;
			while (System.currentTimeMillis() < endTime) {
				// 6.使用setnx方法设置锁值
				if (conn.setnx(lockName, identifierValue) == 1) {
					// 7.判断返回结果如果为1,则可以成功获取锁,并且设置锁的超时时间
					conn.expire(lockName, expireLock);
					retIdentifierValue = identifierValue;
					return retIdentifierValue;
				}
				// 8.否则情况下继续循环等待
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return retIdentifierValue;
	}

	/**
	 * 释放锁
	 * 
	 * @return
	 */
	public boolean releaseLock(String lockKey, String identifier) {

		Jedis conn = null;
		boolean flag = false;
		try {

			// 1.建立redis连接
			conn = jedisPool.getResource();
			// 2.定义锁的名称
			String lockName = "redis_lock" + lockKey;
			// 3.如果value与redis中一直直接删除，否则等待超时
			if (identifier.equals(conn.get(lockName))) {
				conn.del(lockName);
				System.out.println(identifier + "解锁成功......");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return flag;
	}
}
