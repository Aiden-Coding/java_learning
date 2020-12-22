package com.itmayiedu;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

// 启动会员服务
public class MemberServer {

	public static void main(String[] args) throws IOException {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("provider.xml");
		applicationContext.start();
		System.out.println("会员服务启动成功...");
		System.in.read();
	}

}
