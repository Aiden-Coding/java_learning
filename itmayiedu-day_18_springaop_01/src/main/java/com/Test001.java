package com;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.itmayeidu.service.UserService;
import com.itmayiedu.service.impl.UserServiceImpl;

public class Test001 {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring2.xml");
		UserService userService = (UserService) applicationContext.getBean("userServiceImpl");
		userService.add();
	}

}
