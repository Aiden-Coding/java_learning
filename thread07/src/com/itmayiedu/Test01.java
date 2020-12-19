
package com.itmayiedu;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class Test01 {

	public static void main(String[] args) throws InterruptedException {
		// Vector vector = new Vector();
		// vector.add("");
		// ArrayList arrayList = new ArrayList<>();
		// arrayList.add("");
		// HashMap HashMa=new HashMap<>();
		// HashMa.put(key, value)
		// Hashtable hashtable = new Hashtable<>();
		// hashtable.put("", "");
		// ConcurrentLinkedDeque q = new ConcurrentLinkedDeque();
		// q.offer("余胜军");
		// q.offer("码云");
		// q.offer("蚂蚁课堂");
		// q.offer("张杰");
		// q.offer("艾姐");
		// //从头获取元素,删除该元素
		// System.out.println(q.poll());
		// //从头获取元素,不刪除该元素
		// System.out.println(q.peek());
		// //获取总长度
		// System.out.println(q.size());
		// 不能超过初始化长度
		// ArrayBlockingQueue<String> arrays = new
		// ArrayBlockingQueue<String>(3);
		// arrays.add("李四");
		// arrays.add("张军");
		// arrays.add("张军");
		// // 添加阻塞队列
		// arrays.offer("张三", 1, TimeUnit.SECONDS);

		// LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue(3);
		// linkedBlockingQueue.add("张三");
		// linkedBlockingQueue.add("李四");
		// linkedBlockingQueue.add("李四");
		// System.out.println(linkedBlockingQueue.size());
		SynchronousQueue synchronousQueue = new SynchronousQueue<String>();
		synchronousQueue.add("張三");

	}

}
