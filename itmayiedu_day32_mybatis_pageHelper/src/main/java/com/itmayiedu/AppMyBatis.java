package com.itmayiedu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.itmayiedu.controller.IndexController;

@MapperScan("com.itmayiedu.mapper")
@SpringBootApplication
public class AppMyBatis {

	public static void main(String[] args) {
		SpringApplication.run(AppMyBatis.class, args);
	}

}
