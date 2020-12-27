package com.itmayiedu02;

public class Client02 {

	public static void main(String[] args) {
		Book book1 = new Book();
		book1.setTitle("书1");
		book1.addImage("图1");
		book1.showBook();
		//以原型方式拷貝一份
		Book book2 = book1.clone();
		book2.showBook();
		book2.setTitle("书2");
		book2.addImage("图2");
		book2.showBook();
		//再次还原打印书本
		book1.showBook();
		}

}
