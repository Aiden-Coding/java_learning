package com.itmayiedu.spring;

import org.dom4j.DocumentException;

import com.itmayiedu.spring.entity.User;

public class Test002 {

	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
		User user = (User) applicationContext.getBean("user");
		System.out.println(user);
	}

}
