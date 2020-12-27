package com.itmayiedu.error;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局捕获异常案例<br>
 * 1.捕获返回json格式<br>
 * 2.捕获返回页面<br>
 * 作者: 每特教育-余胜军<br>
 * 联系方式:QQ644064779|WWW.itmayiedu.com<br>
 */
@ControllerAdvice(basePackages = "com.itmayiedu.controller")
public class GlobalExceptionHandler {

	// @ResponseBody 返回json格式
	// modeAndView 返回页面
	@ExceptionHandler(RuntimeException.class)
	@ResponseBody
	public Map<String, Object> errorResult() {
		// 实际开发中，怎么 将错误记录在日志中。
		Map<String, Object> errorResultMap = new HashMap<String, Object>();
		errorResultMap.put("errorCode", "500");
		errorResultMap.put("errorMsg", "全局捕获异常系统错误!");
		return errorResultMap;
	}

}
