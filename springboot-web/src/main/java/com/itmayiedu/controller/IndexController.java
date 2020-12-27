
package com.itmayiedu.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	// 转发到index页面
	@RequestMapping("/index")
	public String index() {
		return "index";
	}

	// 接受頁面 參數
	@RequestMapping("/postIndex")
	public String postIndex(HttpServletRequest request) {

		request.setAttribute("name", request.getParameter("name"));
		return "forward";
	}

}
