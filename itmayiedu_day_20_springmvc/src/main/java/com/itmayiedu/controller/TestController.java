package com.itmayiedu.controller;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class TestController {

	public String test(String name) {

		System.out.println("test...name:" + name);
		return "success";
	}

	@ResponseBody
	@RequestMapping("/test")
	public String test(String name, Integer age) {
		System.out.println("test...name:" + name + ",age:" + age);
		return "success";
	}

}
