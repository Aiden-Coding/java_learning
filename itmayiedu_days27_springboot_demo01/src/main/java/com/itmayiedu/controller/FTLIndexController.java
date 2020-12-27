package com.itmayiedu.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 整合framework <br>
 * 作者: 每特教育-余胜军<br>
 * 联系方式:QQ644064779|WWW.itmayiedu.com<br>
 */
@Controller
public class FTLIndexController {

	@RequestMapping("/ftlIndex")
	public String ftlIndex(Map<String, Object> map) {
		map.put("name", "余胜军");
		map.put("age", "20");
		map.put("sex", "0");
		return "ftlIndex";
	}

}
