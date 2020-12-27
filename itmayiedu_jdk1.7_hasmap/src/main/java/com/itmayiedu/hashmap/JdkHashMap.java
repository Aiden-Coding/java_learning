//package com.itmayiedu.hashmap;
//
//import java.io.Serializable;
//import java.util.AbstractMap;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Objects;
//import java.util.Set;
//
//public class JdkHashMap<K, V> extends AbstractMap<K, V>implements Map<K, V>, Cloneable, Serializable {
//
//	// 默认的HashMap的空间大小16
//	static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;
//
//	// hashMap最大的空间大小
//	static final int MAXIMUM_CAPACITY = 1 << 30;
//
//	// HashMap默认负载因子，负载因子越小，hash冲突机率越低，至于为什么，看完下面源码就知道了
//	static final float DEFAULT_LOAD_FACTOR = 0.75f;
//
//	static final Entry<?, ?>[] EMPTY_TABLE = {};
//
//	// table就是HashMap实际存储数组的地方
//	transient Entry<K, V>[] table = (Entry<K, V>[]) EMPTY_TABLE;
//
//	// HashMap 实际存储的元素个数
//	transient int size;
//
//	// 临界值（即hashMap 实际能存储的大小）,公式为(threshold = capacity * loadFactor)
//	int threshold;
//
//	// HashMap 负载因子
//	final float loadFactor;
//
//	// HashMap的(key -> value)键值对形式其实是由内部类Entry实现，那么此处就先贴上这个内部类
//	static class Entry<K, V> implements Map.Entry<K, V> {
//		final K key;
//		V value;
//		// 保存了对下一个元素的引用，说明此处为链表
//		// 为什么此处会用链表来实现？
//		// 其实此处用链表是为了解决hash一致的时候的冲突
//		// 当两个或者多个hash一致的时候，那么就将这两个或者多个元素存储在一个位置，用next来保存对下个元素的引用
//		Entry<K, V> next;
//		int hash;
//
//		Entry(int h, K k, V v, Entry<K, V> n) {
//			value = v;
//			next = n;
//			key = k;
//			hash = h;
//		}
//
//		public final K getKey() {
//			return key;
//		}
//
//		public final V getValue() {
//			return value;
//		}
//
//		public final V setValue(V newValue) {
//			V oldValue = value;
//			value = newValue;
//			return oldValue;
//		}
//
//		public final boolean equals(Object o) {
//			if (!(o instanceof Map.Entry))
//				return false;
//			Map.Entry e = (Map.Entry) o;
//			Object k1 = getKey();
//			Object k2 = e.getKey();
//			if (k1 == k2 || (k1 != null && k1.equals(k2))) {
//				Object v1 = getValue();
//				Object v2 = e.getValue();
//				if (v1 == v2 || (v1 != null && v1.equals(v2)))
//					return true;
//			}
//			return false;
//		}
//
//		public final int hashCode() {
//			return Objects.hashCode(getKey()) ^ Objects.hashCode(getValue());
//		}
//
//		public final String toString() {
//			return getKey() + "=" + getValue();
//		}
//
//		void recordAccess(HashMap<K, V> m) {
//		}
//
//		void recordRemoval(HashMap<K, V> m) {
//		}
//	}
//	// 以上是内部类Entry
//
//	// 构造方法， 设置HashMap的loadFactor 和 threshold, 方法极其简单，不多说
//	public JdkHashMap(int initialCapacity, float loadFactor) {
//		if (initialCapacity < 0)
//			throw new IllegalArgumentException("Illegal initial capacity: " + initialCapacity);
//		if (initialCapacity > MAXIMUM_CAPACITY)
//			initialCapacity = MAXIMUM_CAPACITY;
//		if (loadFactor <= 0 || Float.isNaN(loadFactor))
//			throw new IllegalArgumentException("Illegal load factor: " + loadFactor);
//
//		this.loadFactor = loadFactor;
//		threshold = initialCapacity;
//		init();
//	}
//
//	public JdkHashMap(int initialCapacity) {
//		this(initialCapacity, DEFAULT_LOAD_FACTOR);
//	}
//
//	public JdkHashMap() {
//		this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
//	}
//
//	// 构造方法，传入Map, 将Map转换为HashMap
//	public JdkHashMap(Map<? extends K, ? extends V> m) {
//		this(Math.max((int) (m.size() / DEFAULT_LOAD_FACTOR) + 1, DEFAULT_INITIAL_CAPACITY), DEFAULT_LOAD_FACTOR);
//		// 初始化HashMap， 这个方法下面会详细分析
//		inflateTable(threshold);
//		// 这就是将指定Map转换为HashMap的方法，后面会详细分析
//		putAllForCreate(m);
//	}
//
//	// 初始化HashMap
//	private void inflateTable(int toSize) {
//		// 计算出大于toSize最临近的2的N此方的值
//		// 假设此处传入6, 那么最临近的值为2的3次方，也就是8
//		int capacity = roundUpToPowerOf2(toSize);
//		// 由此处可知：threshold = capacity * loadFactor
//		threshold = (int) Math.min(capacity * loadFactor, MAXIMUM_CAPACITY + 1);
//		// 创建Entry数组，这个Entry数组就是HashMap所谓的容器
//		table = new Entry[capacity];
//		initHashSeedAsNeeded(capacity);
//	}
//
//	private static int roundUpToPowerOf2(int number) {
//		// 当临界值小于HashMap最大容量时， 返回最接近临界值的2的N次方
//		// Integer.highestOneBit方法的作用是用来计算指定number最临近的2的N此方的数
//		return number >= MAXIMUM_CAPACITY ? MAXIMUM_CAPACITY
//				: (number > 1) ? Integer.highestOneBit((number - 1) << 1) : 1;
//	}
//
//	// 这就是将指定Map转换为HashMap的方法，主要看下面的putForCreate方法
//	private void putAllForCreate(Map<? extends K, ? extends V> m) {
//		for (Map.Entry<? extends K, ? extends V> e : m.entrySet())
//			putForCreate(e.getKey(), e.getValue());
//	}
//
//	private void putForCreate(K key, V value) {
//		// 计算hash值， key为null的时候，hash为0
//		int hash = null == key ? 0 : hash(key);
//		// 根据hash值，找出当前hash在table中的位置
//		int i = indexFor(hash, table.length);
//
//		// 由于table[i]处可能不止有一个元素（多个会形成一个链表），因此，此处写这样一个循环
//		// 当key存在的时候，直接将key的值设置为新值
//		for (Entry<K, V> e = table[i]; e != null; e = e.next) {
//			Object k;
//			if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k)))) {
//				e.value = value;
//				return;
//			}
//		}
//		// 当key不存在的时候，就在table的指定位置新创建一个Entry
//		createEntry(hash, key, value, i);
//	}
//
//	// 在table的指定位置新创建一个Entry
//	void createEntry(int hash, K key, V value, int bucketIndex) {
//		Entry<K, V> e = table[bucketIndex];
//		table[bucketIndex] = new Entry<>(hash, key, value, e);
//		size++;
//	}
//
//	// 下面就开始分析我们常用的方法了（put, remove）
//
//	// 先看put方法
//	public V put(K key, V value) {
//		// table为空，就先初始化
//		if (table == EMPTY_TABLE) {
//			// 这个方法上面已经分析过了，主要是在初始化HashMap,包括创建HashMap保存的元素的数组等操作
//			inflateTable(threshold);
//		}
//
//		// key 为null的情况， 只允许有一个为null的key
//		if (key == null)
//			return putForNullKey(value);
//		// 计算hash
//		int hash = hash(key);
//		// 根据指定hash，找出在table中的位置
//		int i = indexFor(hash, table.length);
//		// table中，同一个位置（也就是同一个hash）可能出现多个元素（链表实现），故此处需要循环
//		// 如果key已经存在，那么直接设置新值
//		for (Entry<K, V> e = table[i]; e != null; e = e.next) {
//			Object k;
//			if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {
//				V oldValue = e.value;
//				e.value = value;
//				e.recordAccess(this);
//				return oldValue;
//			}
//		}
//
//		modCount++;
//		// key 不存在，就在table指定位置之处新增Entry
//		addEntry(hash, key, value, i);
//		return null;
//	}
//
//	// 当key为null 的处理情况
//	private V putForNullKey(V value) {
//    //先看有没有key为null, 有就直接设置新值
//        for (Entry<K,V> e = table[0]; e != null; e = e.next) {
//            if (e.key == null) {
//                V oldValue = e.value;
//                e.value = value;
//                e.recordAccess(this);
//                return oldValue;
//            }
//        }
//        modCount++;
//    //当前没有为null的key就新创建一个entry,其在table的位置为0（也就是第一个）
//        addEntry(0, null, value, 0);
//        return null;
//    }
//
//	// 在table指定位置新增Entry, 这个方法很重要
//	void addEntry(int hash, K key, V value, int bucketIndex) {
//		if ((size >= threshold) && (null != table[bucketIndex])) {
//			// table容量不够, 该扩容了（两倍table），重点来了，下面将会详细分析
//			resize(2 * table.length);
//			// 计算hash, null为0
//			hash = (null != key) ? hash(key) : 0;
//			// 找出指定hash在table中的位置
//			bucketIndex = indexFor(hash, table.length);
//		}
//
//		createEntry(hash, key, value, bucketIndex);
//	}
//
//	// 扩容方法 (newCapacity * loadFactor)
//	void resize(int newCapacity) {
//		Entry[] oldTable = table;
//		int oldCapacity = oldTable.length;
//		// 如果之前的HashMap已经扩充打最大了，那么就将临界值threshold设置为最大的int值
//		if (oldCapacity == MAXIMUM_CAPACITY) {
//			threshold = Integer.MAX_VALUE;
//			return;
//		}
//
//		// 根据新传入的capacity创建新Entry数组，将table引用指向这个新创建的数组，此时即完成扩容
//		Entry[] newTable = new Entry[newCapacity];
//		transfer(newTable, initHashSeedAsNeeded(newCapacity));
//		table = newTable;
//		// 扩容公式在这儿（newCapacity * loadFactor）
//		// 通过这个公式也可看出，loadFactor设置得越小，遇到hash冲突的几率就越小
//		threshold = (int) Math.min(newCapacity * loadFactor, MAXIMUM_CAPACITY + 1);
//	}
//
//	// 扩容之后，重新计算hash，然后再重新根据hash分配位置，
//	// 由此可见，为了保证效率，如果能指定合适的HashMap的容量，会更合适
//	void transfer(Entry[] newTable, boolean rehash) {
//		int newCapacity = newTable.length;
//		for (Entry<K, V> e : table) {
//			while (null != e) {
//				Entry<K, V> next = e.next;
//				if (rehash) {
//					e.hash = null == e.key ? 0 : hash(e.key);
//				}
//				int i = indexFor(e.hash, newCapacity);
//				e.next = newTable[i];
//				newTable[i] = e;
//				e = next;
//			}
//		}
//	}
//
//	// 上面看了put方法，接下来就看看remove
//	public V remove(Object key) {
//		Entry<K, V> e = removeEntryForKey(key);
//		return (e == null ? null : e.value);
//	}
//
//	// 这就是remove的核心方法
//	final Entry<K, V> removeEntryForKey(Object key) {
//		if (size == 0) {
//			return null;
//		}
//		// 老规矩，先计算hash,然后通过hash寻找在table中的位置
//		int hash = (key == null) ? 0 : hash(key);
//		int i = indexFor(hash, table.length);
//		Entry<K, V> prev = table[i];
//		Entry<K, V> e = prev;
//
//		// 这儿又神奇地回到了怎么删除链表的问题（上次介绍linkedList的时候，介绍过）
//		// 李四左手牵着张三，右手牵着王五，要删除李四，那么直接让张三牵着王五的手就OK
//		while (e != null) {
//			Entry<K, V> next = e.next;
//			Object k;
//			if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k)))) {
//				modCount++;
//				size--;
//				if (prev == e)
//					table[i] = next;
//				else
//					prev.next = next;
//				e.recordRemoval(this);
//				return e;
//			}
//			prev = e;
//			e = next;
//		}
//
//		return e;
//	}
//
//	@Override
//	public Set<java.util.Map.Entry<K, V>> entrySet() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//}