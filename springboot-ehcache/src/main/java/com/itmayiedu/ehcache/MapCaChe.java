/**
 * 功能说明:
 * 功能作者:
 * 创建日期:
 * 版权归属:每特教育|蚂蚁课堂所有 www.itmayiedu.com
 */
package com.itmayiedu.ehcache;

import java.util.Map;

import org.springframework.stereotype.Component;

import net.sf.ehcache.util.concurrent.ConcurrentHashMap;

/**
 * 功能说明: <br>
 * 创建作者:每特教育-余胜军<br>
 * 创建时间:2018年7月30日 下午9:12:19<br>
 * 教育机构:每特教育|蚂蚁课堂<br>
 * 版权说明:上海每特教育科技有限公司版权所有<br>
 * 官方网站:www.itmayiedu.com|www.meitedu.com<br>
 * 联系方式:qq644064779<br>
 * 注意:本内容有每特教育学员共同研发,请尊重原创版权
 */
@Component
public class MapCaChe<K, V> {

	// 存放缓存容器
	public Map<K, V> concurrentHashMap = new ConcurrentHashMap<K, V>();

	// 纯手写单个JVM缓存框架 缓存概念偏向于临时
	// 白话文代码分析 容器Map集合 如何设计时间有效期 开两个两个线程 主线程存放 定时job每隔一秒钟时间

	// 存储
	public void put(K k, V v) {
		concurrentHashMap.put(k, v);
	}

	// 查询
	public V get(K k) {
		return concurrentHashMap.get(k);
	}

	public void remove(K k) {
		concurrentHashMap.remove(k);
	}
}
