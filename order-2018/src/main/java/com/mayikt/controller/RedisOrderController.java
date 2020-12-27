package com.mayikt.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisOrderController {
	@Autowired
	private RedisTemplate redisTemplate;

	@RequestMapping("/order")
	public String order(String key) {
		RedisAtomicLong redisAtomicLong = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
		long andIncrement = redisAtomicLong.getAndIncrement();
		String orderId = prefix() + "-" + String.format("%1$010d", andIncrement);
		return orderId;
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
