package com.itmayiedu;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.itmayiedu.service.UserService;

public class Test0001 {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring_aop.xml");
		UserService userService = (UserService) applicationContext.getBean("userService");
		userService.add();
	}
}
