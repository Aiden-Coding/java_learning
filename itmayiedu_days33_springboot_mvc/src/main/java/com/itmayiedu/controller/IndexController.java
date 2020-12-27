package com.itmayiedu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itmayiedu.service.UserService;

@RestController
public class IndexController {
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/index", produces = "text/html;charset=UTF-8")
	public String index() {
		// return "纯手写SpringBoot ok啦！！！更多免费教程 请上蚂蚁课堂!";\
		return userService.index();
	}

}
