package com.itmayiedu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itmayiedu.service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/index", produces = "text/html;charset=UTF-8")
	public String index() {
		return userService.index();
	}

}
