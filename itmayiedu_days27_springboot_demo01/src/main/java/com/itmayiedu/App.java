package com.itmayiedu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 这是第二中启动方式 加上扫包 <br>
 * 作者: 每特教育-余胜军<br>
 * 联系方式:QQ644064779|WWW.itmayiedu.com<br>
 * SpringBoot 项目 如何整合静态资源 图片、css、js之类
 * 
 */
// @ComponentScan("com.itmayiedu.controller")
// @EnableAutoConfiguration
@SpringBootApplication
public class App {

	/**
	 * 
	 * // @SpringBootApplication
	 * 等同于@EnableAutoConfiguration+ @ComponentScan("com.itmayiedu") 作者: 每特教育-余胜军
	 * <br>
	 * 联系方式:QQ644064779|WWW.itmayiedu.com<br>
	 * 注意：@SpringBootApplication 扫包范围是在同级包和当前包下
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}
