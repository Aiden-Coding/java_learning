
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
		// q.offer("��ʤ��");
		// q.offer("����");
		// q.offer("���Ͽ���");
		// q.offer("�Ž�");
		// q.offer("����");
		// //��ͷ��ȡԪ��,ɾ����Ԫ��
		// System.out.println(q.poll());
		// //��ͷ��ȡԪ��,���h����Ԫ��
		// System.out.println(q.peek());
		// //��ȡ�ܳ���
		// System.out.println(q.size());
		// ���ܳ�����ʼ������
		// ArrayBlockingQueue<String> arrays = new
		// ArrayBlockingQueue<String>(3);
		// arrays.add("����");
		// arrays.add("�ž�");
		// arrays.add("�ž�");
		// // �����������
		// arrays.offer("����", 1, TimeUnit.SECONDS);

		// LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue(3);
		// linkedBlockingQueue.add("����");
		// linkedBlockingQueue.add("����");
		// linkedBlockingQueue.add("����");
		// System.out.println(linkedBlockingQueue.size());
		SynchronousQueue synchronousQueue = new SynchronousQueue<String>();
		synchronousQueue.add("����");

	}

}
