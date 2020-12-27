package com.itmayiedu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itmayiedu.service.UserService;

/**
 * 测试mybatis整合springboot 作者: 每特教育-余胜军<br>
 * 联系方式:QQ644064779|WWW.itmayiedu.com<br>
 */
@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("/insertUser")
	public Integer insertUser(String name, Integer age) {
		return userService.insertUser(name, age);
	}

}
