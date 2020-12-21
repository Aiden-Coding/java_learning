
package com.itmayiedu.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@SpringBootApplication
@Component
public class Consumer {

	@JmsListener(destination = "${queue}")
	public void receive(String msg) {
		System.out.println("消费者收到消息:" + msg);
	}

	public static void main(String[] args) {
		SpringApplication.run(Consumer.class, args);
	}
}
