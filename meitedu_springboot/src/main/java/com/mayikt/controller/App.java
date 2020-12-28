package com.mayikt.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//1
@SpringBootApplication
@RestController
public class App {

	@RequestMapping("/")
	public String index() {
		return "每特教育独创基于 Jenkins+docker部署SpringBoot项目QQ644064779.....www.mayikt.com";
	}

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}
