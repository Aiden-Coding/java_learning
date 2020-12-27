package com.itmayiedu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itmayiedu.mapper.UserMapper;

@RestController
public class IndexController {
	@Autowired
	private UserMapper userMapper;

	@RequestMapping("/mybatisIndex")
	public String mybatisIndex(String name, Integer age) {
		userMapper.insert(name, age);
		return "success";
	}

}
