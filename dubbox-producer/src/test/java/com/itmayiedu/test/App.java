
package com.itmayiedu.test;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

	public static void main(String[] args) throws IOException {
		ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("dubbox-producer.xml");
		app.start();
		System.out.println("服务发布成功...");
		System.in.read();
	}

}
