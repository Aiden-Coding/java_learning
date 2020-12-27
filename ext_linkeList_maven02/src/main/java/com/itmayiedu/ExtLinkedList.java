package com.itmayiedu;

/**
 * 纯手写 LinkeList<br>
 * 作者: 每特教育-余胜军<br>
 * 联系方式:QQ644064779|WWW.itmayiedu.com<br>
 * 
 * 1.LinkedList 与Arraylist存储或者删除 那个效率高。？<br>
 * 2.答：Arraylist集合实现添加或者删除的时候，数组扩容问题、 3.LinkedList查询效率非常低
 *
 * @param <E>
 */
public class ExtLinkedList<E> {

	// 第一个元素
	private Node first;
	// 最后一个元素
	private Node last;
	// 实际存放在长度
	private int size;

	class Node {
		// 上一个节点
		Node prev;
		// 节点内容
		Object object;
		// 下一个节点
		Node next;
	}

	public void add(E e) {
		// 创建新的节点
		Node node = new Node();
		// 节点内容
		node.object = e;
		if (first == null) {
			// // 上一个节点
			// node.prev = null;
			// // 下一个节点
			// node.next = null;
			// 第一个元素和最后一个元素都是为node
			first = node;
		} else {
			// 存放上一个节点内容
			node.prev = last;
			// 设置上一个节点的next为当前节点
			last.next = node;
		}
		last = node;
		size++;
	}

	public void add(int index, E e) {
		// 1.循环遍历到当前index位置Node
		// 2.新增当前节点
		Node newNode = new Node();
		newNode.object = e;
		// 获取原来的节点
		ExtLinkedList<E>.Node olNode = getNode(index);
		// 获取原来上一个节点
		ExtLinkedList<E>.Node olNodePrev = olNode.prev;

		// 4.新增节点的上一个还是当前Node节点的 上一个节点,下一个就是原来的节点
		// 原来上一个节点变为当前节点
		olNode.prev = newNode;
		if (olNodePrev == null)
			first = newNode;
		else
			// 原来上一个节点的下一个节点变为当前节点
			olNodePrev.next = newNode;

		// 新节点的下一个节点为原来节点
		newNode.next = olNode;
		size++;
	}

	public E get(int index) {
		ExtLinkedList<E>.Node node = getNode(index);
		return (E) node.object;
	}

	public Node getNode(int index) {
		Node node = null;
		if (first != null) {
			node = first;
			for (int i = 0; i < index; i++) {
				node = node.next;
			}
		}
		return node;
	}

	public void remove(int index) {
		checkElementIndex(index);
		ExtLinkedList<E>.Node node = getNode(index);
		if (node != null) {
			ExtLinkedList<E>.Node prevNode = node.prev;
			ExtLinkedList<E>.Node nextNode = node.next;
			// 设置上一个节点的next为当前删除节点的next
			if (prevNode != null) {
				prevNode.next = nextNode;
			}

			// 判断是否是最后一个节点
			if (nextNode != null) {
				nextNode.prev = prevNode;
			}

		}
		size--;
	}

	public static void main(String[] args) {
		ExtLinkedList<String> linkeList = new ExtLinkedList<String>();
		linkeList.add("A");
		linkeList.add("B");
		linkeList.add("C");
		linkeList.add("D");
		linkeList.add(2, "E");
		for (int i = 0; i < linkeList.size; i++) {
			System.out.println(linkeList.get(i));
		}
	}

	private boolean isElementIndex(int index) {
		return index >= 0 && index < size;
	}

	private void checkElementIndex(int index) {
		if (!isElementIndex(index))
			throw new IndexOutOfBoundsException("越界啦!");
	}
}
