package com.itmayiedu.order.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import com.alibaba.dubbo.config.annotation.Service;
import com.itmayiedu.service.UserService;

public class OrderService {

	public static void addOrder() throws IOException {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("consumer.xml");
		applicationContext.start();
		System.out.println("###order服務,开始调用会员服务");
		UserService userService = (UserService) applicationContext.getBean("userService");
		String userName = userService.getUser(1l);
		System.out.println("###order服務,结束调用会员服务,userName:" + userName);
		System.in.read();
	}

	public static void main(String[] args) throws IOException {
		addOrder();

	}

}
