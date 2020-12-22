package com.itmayiedu.app;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Provider {

	public static void main(String[] args) throws IOException {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("dubbo-provider.xml");
		applicationContext.start();
		System.out.println("生产者已经启动...");
		System.in.read();
		
	}

}
