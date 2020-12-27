package com.mayikt.consumer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
	@Value("${server.port}")
	private String serverPort;

	@StreamListener("my_msg")
	public void listener(String msg) {
		System.out.println("消费者获取生产消息:" + msg + ",端口号:" + serverPort);
	}

}
