package com.itmayiedu;

import com.itmayiedu.service.UserService;
import com.itmayiedu.spring.ext.ExtClassPathXmlApplicationContext;

public class Test001 {

	public static void main(String[] args) throws Exception {
		ExtClassPathXmlApplicationContext app = new ExtClassPathXmlApplicationContext("com.itmayiedu.service.impl");
		UserService userService = (UserService) app.getBean("userServiceImpl");
		userService.add();
		System.out.println(userService);
	}

}
