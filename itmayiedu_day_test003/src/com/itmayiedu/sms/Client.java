package com.itmayiedu.sms;

public class Client {

	public static void main(String[] args) {
		// 目标对象
		RealObserver subject = new RealObserver();
		// 创建多个观察者
		ObserverA obs1 = new ObserverA();
		ObserverA obs2 = new ObserverA();
		ObserverA obs3 = new ObserverA();
		// 注册到观察队列中
		subject.registerObserver(obs1);
		subject.registerObserver(obs2);
		subject.registerObserver(obs3);
		// 改变State状态
		subject.setState(300);
		System.out.println(obs1.getMyState());
		System.out.println(obs2.getMyState());
		System.out.println(obs3.getMyState());
		// 改变State状态
		subject.setState(400);
		System.out.println(obs1.getMyState());
		System.out.println(obs2.getMyState());
		System.out.println(obs3.getMyState());
	}

}
