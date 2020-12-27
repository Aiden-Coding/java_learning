package com.itmayiedu;

/**
 * 自定义Map集合 <br>
 * 作者: 每特教育-余胜军<br>
 * 联系方式:QQ644064779|WWW.itmayiedu.com<br>
 */
public class ExtMap {
	ExtEntry[] arr = new ExtEntry[999];
	int size;

	public void put(Object key, Object value) {
		int a = key.hashCode() % arr.length;
		ExtEntry e = new ExtEntry();
		e.key = key;
		e.value = value;
		arr[a] = e;
	}

	public Object get(Object key) {
		return arr[key.hashCode() % arr.length];
	}

}

class ExtEntry {

	Object key;
	Object value;

}