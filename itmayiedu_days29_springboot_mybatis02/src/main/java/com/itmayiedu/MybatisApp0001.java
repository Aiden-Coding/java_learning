package com.itmayiedu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan(basePackages = { "com.itmayiedu.mapper" })
public class MybatisApp0001 {

	public static void main(String[] args) {
		SpringApplication.run(MybatisApp0001.class, args);
	}

}
