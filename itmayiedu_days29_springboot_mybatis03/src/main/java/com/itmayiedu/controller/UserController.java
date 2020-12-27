package com.itmayiedu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itmayiedu.test01.service.UserServiceTest01;
import com.itmayiedu.test02.service.UserServiceTest02;

@RestController
public class UserController {

	@Autowired
	private UserServiceTest01 userServiceTest01;
	@Autowired
	private UserServiceTest02 userServiceTest02;

	@RequestMapping("/insertUserTest001")
	public Integer insertUserTest001(String name, Integer age) {
		return userServiceTest01.insertUser(name, age);
	}

	@RequestMapping("/insertUserTest002")
	public Integer insertUserTest002(String name, Integer age) {
		return userServiceTest02.insertUser(name, age);
	}

	@RequestMapping("/inserUserTest01AndTest02")
	public Integer inserUserTest01AndTest02(String name, Integer age) {
		return userServiceTest02.inserUserTest01AndTest02(name, age);
	}
}
