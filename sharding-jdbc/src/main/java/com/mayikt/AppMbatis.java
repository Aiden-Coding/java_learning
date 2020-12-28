package com.mayikt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.mayikt.mapper")
public class AppMbatis {
	public static void main(String[] args) {

		SpringApplication.run(AppMbatis.class, args);
	}
}
