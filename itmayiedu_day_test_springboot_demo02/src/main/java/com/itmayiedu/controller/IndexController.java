package com.itmayiedu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itmayiedu.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class IndexController {

	@Autowired
	private UserService userService;

	@Value("${name}")
	private String name;
	@Value("${http_url}")
	private String httpUrl;

	@RequestMapping("/index")
	public String index() {
		log.info("##01##");
		userService.userThread();
		log.info("##04##");
		return "success";
	}

	@RequestMapping("/getName")
	public String getName() {
		return name;
	}

	@RequestMapping("/getHttpUrl")
	public String getHttpUrl() {

		return httpUrl;
	}

}
