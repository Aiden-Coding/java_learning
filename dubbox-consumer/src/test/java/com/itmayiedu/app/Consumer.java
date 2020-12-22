package com.itmayiedu.app;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.itmayiedu.service.UserService;

public class Consumer {

	 public static void main(String[] args) {
		 ClassPathXmlApplicationContext applicationContext= new ClassPathXmlApplicationContext("dubbo-consumer.xml");
		 applicationContext.start();
		 System.out.println("###消费者启动####");
		 UserService userService=(UserService) applicationContext.getBean("userService");
		 System.out.println("消费者调用生产者服务开始");
		 String user = userService.getUser(1);
		 System.out.println("消费者调用生产者服务结束 user:"+user);
		 
	 }
	
}
