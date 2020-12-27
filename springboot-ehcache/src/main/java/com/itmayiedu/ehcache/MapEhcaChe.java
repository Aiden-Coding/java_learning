/**
 * 功能说明:
 * 功能作者:
 * 创建日期:
 * 版权归属:每特教育|蚂蚁课堂所有 www.itmayiedu.com
 */
package com.itmayiedu.ehcache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

/**
 * 功能说明: <br>
 * 创建作者:每特教育-余胜军<br>
 * 创建时间:2018年7月30日 下午6:48:30<br>
 * 教育机构:每特教育|蚂蚁课堂<br>
 * 版权说明:上海每特教育科技有限公司版权所有<br>
 * 官方网站:www.itmayiedu.com|www.meitedu.com<br>
 * 联系方式:qq644064779<br>
 * 注意:本内容有每特教育学员共同研发,请尊重原创版权
 */
@Component
public class MapEhcaChe<K, V> {
	private Map<K, V> ehcaCheMap = new ConcurrentHashMap<K, V>();

	public void put(K k, V value) {
		ehcaCheMap.put(k, value);
	}

	public V get(K k) {
		return ehcaCheMap.get(k);
	}

	public void remove(K k) {
		ehcaCheMap.remove(k);
	}
}
