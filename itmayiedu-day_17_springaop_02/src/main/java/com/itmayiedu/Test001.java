package com.itmayiedu;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.itmayiedu.service.UserService;

public class Test001 {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
		UserService userService = (UserService) applicationContext.getBean("userServiceImpl");
		userService.add();
	}

}
