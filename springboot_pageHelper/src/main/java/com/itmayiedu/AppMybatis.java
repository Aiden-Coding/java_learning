package com.itmayiedu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = { "com.itmayiedu.mapper" })
@SpringBootApplication
public class AppMybatis {

	public static void main(String[] args) {
		SpringApplication.run(AppMybatis.class, args);
	}

}
