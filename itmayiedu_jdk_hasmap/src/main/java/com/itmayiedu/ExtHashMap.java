package com.itmayiedu;

/**
 * 纯手写jdk1.7版本HashMap<br>
 * 作者: 每特教育-余胜军<br>
 * 联系方式:QQ644064779|WWW.itmayiedu.com<br>
 */
public class ExtHashMap<K, V> implements ExtMap<K, V> {

	Entry<K, V> table[] = null;

	private static int defaultLenth = 16;// 默认长度
	private static double defaultLoader = 0.75;// 加载因子
	private int size;// 大小

	public V put(K k, V v) {
		// 1. 判断数组是否为空
		if (table == null) {
			table = new Entry[defaultLenth];
		}
		int index = getIndex(k, defaultLenth);

		// 2.分析是否需要扩容
		if (size > defaultLenth * defaultLoader) {
			resSize();
		}

		Node<K, V> node = (Node<K, V>) table[index];
		if (node == null) {
			table[index] = new Node<K, V>(k, v, null);
			size++;
		} else {
			if (k.equals(node.key) || k == node.getKey()) {
				return node.setValue(v);
			} else {
				table[index] = new Node<K, V>(k, v, node);
				size++;
			}
		}

		return null;
	}

	private void resSize() {
		if (size > defaultLenth * defaultLoader) {
			// hasMap 扩容翻倍
			// 1.创建新的tables
			Entry<K, V> newtable[] = new Entry[defaultLenth << 1];
			// 2.每个值重新散列,插入到新的table上
			Node<K, V> node = null;
			for (int i = 0; i < table.length; i++) {
				node = (Node<K, V>) table[i];
				while (node != null) {
					// 重新散列
					int index = getIndex(node.getKey(), newtable.length);
					Node<K, V> next = node.next;
					node.next = (Node<K, V>) newtable[index];
					newtable[index] = node;
					node = next;
				}
				System.out.println();
			}
			// 3.替换老的table
			table = newtable;
			defaultLenth = newtable.length;
			newtable = null;
		}
	}

	private int getIndex(Object key, int length) {
		// 获取hashCode
		int hash = key.hashCode();
		// 取模算法
		return hash & length - 1;
		// return hash % (length - 1);
	}

	public V get(K k) {
		int index = getIndex(k, defaultLenth);
		Node<K, V> node = getNode((Node<K, V>) table[index], k);
		return node == null ? null : node.value;
	}

	public Node<K, V> getNode(Node<K, V> node, K k) {
		while (node != null) {
			if (node.getKey().equals(k)) {
				return node;
			} else {
				node = node.next;
			}

		}
		return null;
	}

	public void print() {
		System.out.println("###############开始##################");
		for (int i = 0; i < table.length; i++) {
			Node<K, V> node = (Node<K, V>) table[i];
			System.out.print("下标位置[" + i + "]");
			while (node != null) {
				System.out.print("[" + node.key + "," + node.value + "]");
				if (node.next != null) {
					node = node.next;
				} else {
					node = null;
				}

			}
			System.out.println();
		}
		System.out.println("###############结束##################");
	}

	public Node<K, V> getNode(K k) {
		return null;
	}

	public int size() {
		return size;
	}

	static class Node<K, V> implements Entry<K, V> {

		public Node(K key, V value, Node<K, V> next) {
			super();
			this.key = key;
			this.value = value;
			this.next = next;
		}

		K key;
		V value;
		Node<K, V> next;

		public K getKey() {

			return this.key;
		}

		public V getValue() {

			return this.value;
		}

		public V setValue(V value) {
			V oldValue = this.value;
			this.value = value;
			return oldValue;
		}

	}

}
