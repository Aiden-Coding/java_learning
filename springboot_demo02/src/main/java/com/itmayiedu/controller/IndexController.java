package com.itmayiedu.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping("/index")
	public String index() {
		return "index";
	}

	@RequestMapping("/freemarkerIndex")
	public String freemarkerIndex(Map<String, Object> result) {
		result.put("name", "余胜军");
		result.put("age", "20");
		result.put("sex", "0");
		return "freemarkerIndex";
	}
}
