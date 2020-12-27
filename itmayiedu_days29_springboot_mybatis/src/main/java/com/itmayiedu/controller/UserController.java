package com.itmayiedu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itmayiedu.service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping("/insertUser")
	public Integer insertUser(String name, Integer age) {
		return userService.insertUser(name, age);
	}

}
