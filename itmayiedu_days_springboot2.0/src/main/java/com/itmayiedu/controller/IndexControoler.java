package com.itmayiedu.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexControoler {

	@RequestMapping("/index")
	public String index() {

		return "index";
	}
}
