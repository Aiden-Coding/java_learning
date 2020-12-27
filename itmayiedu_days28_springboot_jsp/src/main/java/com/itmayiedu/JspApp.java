package com.itmayiedu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 1.扫包优化 是在启动优化。<br>
 * 
 * @SpringBootApplication 缺点： <br>
 * @SpringBootApplication 等同于 @ComponentScan("com.itmayiedu")
 * @Configuration
 * @EnableAutoConfiguration 扫包核心：同级包 递归遍历 包下子类 影响到启动项目时间 扫到无用包 <br>
 * 
 * 
 * 
 *                          作者: 每特教育-余胜军<br>
 *                          联系方式:QQ644064779|WWW.itmayiedu.com<br>
 */

@SpringBootApplication
@EnableAsync // 开启异步调用
public class JspApp {

	public static void main(String[] args) {
		SpringApplication.run(JspApp.class, args);
	}

}
