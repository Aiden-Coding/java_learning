
package com.itmayiedu.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itmayiedu.service.RedisService;

@RestController
public class IndexController {
	@Autowired
	private RedisService redisService;

	@RequestMapping("/setObject")
	public String setString(String key, String value) {
		redisService.setObject(key, value, 30l);
		return "success";
	}

	@RequestMapping("/setList")
	public String setList(String key) {
		List<String> listValue = new ArrayList<String>();
		listValue.add("zhangsan");
		listValue.add("lisi");
		redisService.setObject(key, listValue);
		return "success";
	}

	@RequestMapping("/getString")
	public String getString(String key) {
		return redisService.getString(key);
	}

}
