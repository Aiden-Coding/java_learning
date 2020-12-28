package com.mayikt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.mayikt.repository")
public class AppShardingJdbc {

	public static void main(String[] args) {
		SpringApplication.run(AppShardingJdbc.class, args);
	}
}
