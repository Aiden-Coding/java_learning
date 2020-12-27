package com.itmayiedu.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 全局捕获异常案例 <br>
 * 作者: 每特教育-余胜军<br>
 * 联系方式:QQ644064779|WWW.itmayiedu.com<br>
 */

@RestController
public class ErrorJSONController {

	@RequestMapping("/getJSON")
	public String getJSON(int index) {
		int i = 1 / index;

		return "succes";
	}

}
