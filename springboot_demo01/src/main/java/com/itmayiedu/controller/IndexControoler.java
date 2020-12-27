package com.itmayiedu.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * springboot快速入门<br>
 * 作者: 每特教育-余胜军<br>
 * 联系方式:QQ644064779|WWW.itmayiedu.com<br>
 */
@RestController
@EnableAutoConfiguration
public class IndexControoler {

	@RequestMapping("/index")
	public String result() {
		return "这是我的第一个springboot2.0项目###学习更多分布式微服务视频教程###请上蚂蚁课堂www.itmayiedu.com";
	}

	public static void main(String[] args) {
		SpringApplication.run(IndexControoler.class, args);
	}

}
