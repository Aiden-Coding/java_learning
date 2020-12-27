package com.itmayiedu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.itmayiedu.entity.User;
import com.itmayiedu.service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping("/findUserList")
	public PageInfo<User> findUserList(Integer page, Integer size) {
		return userService.findUserList(page, size);
	}

	@RequestMapping("/index")
	public String index() {
		return "index";
	}

}
