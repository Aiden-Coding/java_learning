package com.itmayiedu;

//双重检验锁 双重检验锁模式（double checked locking pattern），是一种使用同步块加锁的方法。程序员称其为双重检查锁，因为会有两次检查 instance == null，一次是在同步块外，一次是在同步块内。为什么在同步块内还要再检验一次？因为可能会有多个线程一起进入同步块外的 if，如果在同步块内不进行二次检验的话就会生成多个实例了。
public class SingletonDemo04 {
	private SingletonDemo04 singletonDemo04;
	private static boolean flag = false;

	private SingletonDemo04() {

		if (flag == false) {
			flag = !flag;
		} else {
			throw new RuntimeException("单例模式被侵犯！");
		}
	}

	public static void main(String[] args) {

	}

	public SingletonDemo04 getInstance() {
		if (singletonDemo04 == null) {
			synchronized (this) {
				if (singletonDemo04 == null) {
					singletonDemo04 = new SingletonDemo04();
				}
			}
		}
		return singletonDemo04;
	}

}
