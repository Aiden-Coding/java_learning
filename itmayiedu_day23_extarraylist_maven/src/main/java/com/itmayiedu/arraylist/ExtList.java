package com.itmayiedu.arraylist;

/**
 * 自定义list接口 <br>
 * 作者: 每特教育-余胜军<br>
 * 联系方式:QQ644064779|WWW.itmayiedu.com<br>
 */
public interface ExtList<E> {
	public void add(E e);

	public int getSize();

	public E get(int index);
}
