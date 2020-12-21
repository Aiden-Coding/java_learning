
package com.itmayiedu;

import java.util.HashMap;
import java.util.Map;

import redis.clients.jedis.Jedis;

public class RedisTest {
	private static Jedis jedis=null;

	public static void main(String[] args) {
		jedis=new Jedis("192.168.110.181",6379);
		jedis.auth("123456");
//		setString();
//		setMap();
//		setList();
		setSet();
	}
	public static void  setString(){
		jedis.set("name", "123456");
		System.out.println("添加成功..");
	}
	public static void setMap(){
		Map<String, String>  map= new HashMap<String, String>();
		map.put("cangtian", "15000");
		map.put("yifan", "18000");
		jedis.hmset("jiuyexinzi", map);
		System.out.println("添加成功..");
	}
	
	public static void setList(){
		jedis.lpush("bingbing", "20000");
		jedis.lpush("bingbing", "25000");
	}
	public static void setSet(){
		jedis.sadd("xiaobai","12000");
		jedis.sadd("xiaobai","18000");
		jedis.sadd("xiaobai","25000");
	}
	
}
