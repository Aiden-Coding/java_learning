package com.itmayiedu.hashmap;

import org.w3c.dom.Node;

/**
 * 纯手写HashMap集合 基于单向链表+数组实现<br>
 * 作者: 每特教育-余胜军<br>
 * 联系方式:QQ644064779|WWW.itmayiedu.com<br>
 * 
 * @param <K>
 * @param <V>
 */
public class ExtHashMap<K, V> implements ExtMap<K, V> {

	// 存放Map集合容量
	Node<K, V>[] tables = null;
	// 负载因子 默认是0.75,负载因子越小,hash冲突概率越低
	private static final float DEFAULT_LOAD_FACTOR = 0.75f;
	// 默认hashMap初始容量大小 为16
	private static int DEFAULT_INITIAL_CAPACITY = 16; // aka 16
	// 实际长度
	private int size;

	public V put(K k, V v) {

		// 1.判断tables是否为空,如果为空,则初始容量（#####懒加载）
		if (tables == null) {
			tables = new Node[DEFAULT_INITIAL_CAPACITY];
		}

		// 2.计算hash值，指定数组存放位置
		int index = getIndex(k, DEFAULT_INITIAL_CAPACITY);
		// 3.判断tables 是否需要扩容####### 实际容量=初始容量 16 *负载因子 0.75 12#####扩容
		if (size > (DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR)) {
			resize();
		}

		// 4.将该数据存放在tables数组中.
		Node<K, V> node = tables[index];
		if (node == null) {
			// 没有发生hash碰撞
			node = new Node<K, V>(k, v, null);
			size++;
		} else {
			// hash 相同.
			if (node.getKey().equals(k) || node.getKey() == k) {
				return node.setValue(v);
			} else {
				// 在链表后面继续累加
				node = new Node<K, V>(k, v, node);
				size++;
			}

		}
		tables[index] = node;
		return null;
	}

	void print() {
		for (int i = 0; i < tables.length; i++) {
			Node<K, V> node = tables[i];
			System.out.print("下标[" + i + "]");
			while (node != null) {
				System.out.print("[key:" + node.key + " value:" + node.value + "]");
				if (node.next != null) {
					node = node.next;
				} else {
					// 退出循环
					node = null;
				}
			}
			System.out.println();
		}
	}

	// hashMap底层扩容
	private void resize() {

		// 1.先创建新的数组 (hashmap 以2倍进行扩容)
		Node<K, V>[] newTables = new Node[DEFAULT_INITIAL_CAPACITY << 1];
		// 2.将原来的tables 里面hash 重新计算
		for (int i = 0; i < tables.length; i++) {
			Node<K, V> node = tables[i];
			while (node != null) {
				K olNodek = node.key;
				// 获取的新扩容的数组 hash位置
				int index = getIndex(olNodek, newTables.length);
				Node<K, V> next = node.next;
				// 元素的下一个
				node.next = newTables[index];
				newTables[index] = node;

				node = next;
			}
		}
		tables = newTables;
		DEFAULT_INITIAL_CAPACITY = newTables.length;
	}

	public int getIndex(K k, int length) {
		int index = k.hashCode() % (length);
		return index;
	}

	public V get(K tempK) {
		int index = getIndex(tempK, DEFAULT_INITIAL_CAPACITY);
		System.out.println("index:" + index);
		Node node = getNode(tables[index], tempK);
		return (V) (node == null ? null : node.value);
	}

	public Node<K, V> getNode(Node<K, V> node, K tempK) {
		while (node != null) {
			K k = node.key;
			if (tempK.equals(k)) {
				return node;
			}
			node = node.next;
		}
		return null;
	}

	public int size() {

		return size;
	}

	static class Node<K, V> implements Entry<K, V> {

		// key值
		private K key;
		// value值
		private V value;
		// 下一个元素Node
		private Node<K, V> next;

		public Node(K key, V value, Node<K, V> next) {
			super();
			this.key = key;
			this.value = value;
			this.next = next;
		}

		public Node<K, V> getNext() {
			return next;
		}

		public void setNext(Node<K, V> next) {
			this.next = next;
		}

		public void setKey(K key) {
			this.key = key;
		}

		public K getKey() {

			return this.key;
		}

		public V getValue() {

			return this.value;
		}

		public V setValue(V value) {
			V olValue = this.value;
			this.value = value;
			return olValue;
		}

	}

}
