package com.mayikt.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AppClientConfig {

	public static void main(String[] args) {
		SpringApplication.run(AppClientConfig.class, args);
	}

}
