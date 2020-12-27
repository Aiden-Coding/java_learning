package com.itmayiedu.list;

/**
 * 自定List接口<br>
 * 作者: 每特教育-余胜军<br>
 * 联系方式:QQ644064779|WWW.itmayiedu.com<br>
 * 
 * @param <E>
 */
public interface ExtList<E> {
	public void add(E object);

	public void add(int index, E object);

	public Object remove(int index);

	public boolean remove(E object);

	public int getSize();

	public Object get(int index);
}
