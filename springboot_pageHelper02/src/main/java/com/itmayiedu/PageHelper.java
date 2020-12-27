package com.itmayiedu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.itmayiedu.mapper")
@SpringBootApplication
public class PageHelper {

	public static void main(String[] args) {
		SpringApplication.run(PageHelper.class, args);
	}

}
