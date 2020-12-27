package com.itmayiedu.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableAutoConfiguration
@RestController
public class IndexController {
	@Value("${server.port}")
	private String port;

	@RequestMapping("/")
	public String index() {
		return "springboot2.0:" + port;
	}

	public static void main(String[] args) {
		SpringApplication.run(IndexController.class, args);
	}

}
