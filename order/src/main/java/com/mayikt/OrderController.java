package com.mayikt;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
	@Autowired
	private RedisTemplate redisTemplate;

	@RequestMapping("/order")
	public String order(String key, Long timeOut) {
		RedisAtomicLong redisAtomicLong = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
		redisAtomicLong.set(1);
		// 设置redis步长增长为2
		redisAtomicLong.addAndGet(1);
		// for (int i = 0; i < 100; i++) {
		long andIncrement = redisAtomicLong.getAndIncrement();
		String orderId = prefix() + String.format("%1$05d", andIncrement);
		String insertSQL = "insert into orderNumber value('" + orderId + "');";
		// System.out.println(Thread.currentThread().getName() +
		// ",insertSQL:" + insertSQL);
		System.out.println(insertSQL);
		if ((null == redisAtomicLong || redisAtomicLong.longValue() == 0) && timeOut > 0) {// 初始设置过期时间
			redisAtomicLong.expire(timeOut, TimeUnit.SECONDS);
		}

		// }

		return "success";
	}

	public static String prefix() {
		String temp_str = "";
		Date dt = new Date();
		// 最后的aa表示“上午”或“下午” HH表示24小时制 如果换成hh表示12小时制
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		temp_str = sdf.format(dt);
		return temp_str;
	}

	public static void main(String[] args) {
		System.out.println(prefix());
	}

}
