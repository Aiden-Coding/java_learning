package com.itmayiedu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.itmayiedu.dao")
@EntityScan(basePackages = "com.itmayiedu.entity")
@SpringBootApplication
public class JpaApp {

	public static void main(String[] args) {
		SpringApplication.run(JpaApp.class, args);
	}

}
