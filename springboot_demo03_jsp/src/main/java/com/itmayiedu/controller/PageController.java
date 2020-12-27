package com.itmayiedu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 全局捕获异常案例 <br>
 * 作者: 每特教育-余胜军<br>
 * 联系方式:QQ644064779|WWW.itmayiedu.com<br>
 */

@Controller
public class PageController {

	@RequestMapping("/getPage")
	public String getJSON(int index) {
		int i = 1 / index;
		return "index111";
	}

}
