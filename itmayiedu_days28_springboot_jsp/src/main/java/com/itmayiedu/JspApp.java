package com.itmayiedu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync // 开启异步调用
public class JspApp {

	public static void main(String[] args) {
		SpringApplication.run(JspApp.class, args);
	}

}
