package com.itmayiedu.controller;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.itmayiedu.entity.User;
import com.itmayiedu.service.UserService;

@RestController
public class IndexController {

	@Autowired
	private UserService userService;

	@RequestMapping("/index")
	public PageInfo<User> index(int page, int pageSize) {
		return userService.findUserList(page, pageSize);
	}

}
