package com.itmayiedu.controller;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/test")
public class TestController {

	@ResponseBody
	@RequestMapping("/test")
	public String test() {
		System.out.println("我是springmvc框架");
		return "success";
	}

}
