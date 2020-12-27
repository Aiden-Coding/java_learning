package com.itmayiedu.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 监控中心<br>
 * SpringMVC 线程是否安全 <br>
 * 作者: 每特教育-余胜军<br>
 * 联系方式:QQ644064779|WWW.itmayiedu.com<br>
 */
@RestController
public class IndexController {

	@RequestMapping("/index")
	public String index() {
		return "index";
	}

}
