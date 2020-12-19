
package com.itmayiedu;

//懒汉式

class Singleton {
	private Singleton() {

	}

	private static Singleton singleton = new Singleton();
	//
	// // 懒汉式 第一种写法 效率不高
	// static public synchronized Singleton getSingleton() {
	// if (singleton == null) {
	// singleton = new Singleton();
	// }
	// return singleton;
	// }
	//
	// // 懒汉式 第二种写法 效率高 双重检验锁
	// static public Singleton getSingleton2() {
	//
	// if (singleton == null) { // 第一步检验锁
	// synchronized (Singleton.class) { // 第二步检验锁
	// if (singleton == null) {
	// singleton = new Singleton();
	// }
	//
	// }
	// }
	//
	// return singleton;
	// }

	static public Singleton getSingleton3() {
		return singleton;
	}

}

public class Test001 {
	public static void main(String[] args) {
		// Singleton singleton1 = Singleton.getSingleton();
		// Singleton singleton2 = Singleton.getSingleton();
		// System.out.println(singleton1 == singleton2);
		// Singleton singleton1 = Singleton.getSingleton2();
		// Singleton singleton2 = Singleton.getSingleton2();
		// System.out.println(singleton1 == singleton2);
		Singleton singleton1 = Singleton.getSingleton3();
		Singleton singleton2 = Singleton.getSingleton3();
		System.out.println(singleton1 == singleton2);
	}
}
