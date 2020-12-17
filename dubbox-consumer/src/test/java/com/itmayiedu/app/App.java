
package com.itmayiedu.app;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.itmayiedu.service.UserService;

public class App {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("dubbo-consumer.xml");
		app.start();
		UserService userService = (UserService) app.getBean("userService");
		String result = userService.getUser(2);
		System.out.println("result:"+result);
	}

}
