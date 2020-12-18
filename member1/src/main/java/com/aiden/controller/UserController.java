package com.aiden.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aiden.service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping("/regit")
	public String regit(String name, String pwd) {
		return userService.regit(name, pwd);
	}

	@RequestMapping("/get")
	public String get(Long id) {
		String name = userService.get(id);
		return name;
	}

}
