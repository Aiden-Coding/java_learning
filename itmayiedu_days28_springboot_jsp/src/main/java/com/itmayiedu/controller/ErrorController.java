package com.itmayiedu.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 全局捕获异常案例<br>
 * 作者: 每特教育-余胜军<br>
 * 联系方式:QQ644064779|WWW.itmayiedu.com<br>
 */
@RestController
public class ErrorController {
	// private static final Logger logger =
	// LoggerFactory.getLogger(JspController.class);

	// 分布式日志收集系统##
	// 全局捕获异常 使用AOP技术，采用异常通知
	// 如果每个方法都可能会发生异常，每个方法上都加上try。
	@RequestMapping("/getUser")
	public String getUser(int i) {
		int j = 1 / i;
		return "success" + j;
	}

	@RequestMapping("/getMember")
	public String getMember(String name, Integer age) {
		// logger.info("name:" + name + ",age:" + age);
		return "success";

	}

}
