package com.itmayiedu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.itmayiedu.config.DBConfig1;
import com.itmayiedu.config.DBConfig2;

@SpringBootApplication
@EnableTransactionManagement
@EnableConfigurationProperties(value = { DBConfig1.class, DBConfig2.class })
public class MybatisApp0003 {

	public static void main(String[] args) {
		SpringApplication.run(MybatisApp0003.class, args);
	}

}
