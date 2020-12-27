package com.mayikt.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 基于Redis 实现分布式全局id
@RestController
public class OrderController {
	@Autowired
	private RedisTemplate redisTemplate;

	// 基于Redis 实现分布式全局id原理
	/**
	 * 15 18位 前缀=当前日期=2018112921303030-5位自增id（高并发请下 先天性安全） 00001<br>
	 * 00010<br>
	 * 00100<br>
	 * 01000<br>
	 * 11000<br>
	 * 在相同毫秒情况下，最多只能生成10万-1=99999订单号<br>
	 * 假设：双11每毫秒99万笔 <br>
	 * 提前生成号订单号码存放在redis中
	 * 
	 * 9.9万*1000=900万<br>
	 * 考虑失效时间问题 24小时
	 * 
	 * 
	 * @return
	 */
	@RequestMapping("/order")
	public String order(String key) {
		RedisAtomicLong redisAtomicLong = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
		for (int i = 0; i < 100; i++) {
			long incrementAndGet = redisAtomicLong.incrementAndGet();
			// 5位
			String orderId = prefix() + "-" + String.format("%1$05d", incrementAndGet);
			String orderSQL = "insert into orderNumber value('" + orderId + "');";
			System.out.println(orderSQL);
		}

		return "success";
	}

	@RequestMapping("/order1")
	public String order1(String key) {
		RedisAtomicLong redisAtomicLong = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
		// // 起始值
		// redisAtomicLong.set(10);
		// 设置步长加10
		redisAtomicLong.addAndGet(9);
		return redisAtomicLong.incrementAndGet() + "";
	}

	public static String prefix() {
		String temp_str = "";
		Date dt = new Date();
		// 最后的aa表示“上午”或“下午” HH表示24小时制 如果换成hh表示12小时制
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		temp_str = sdf.format(dt);
		return temp_str;
	}

}
