package com.itmayiedu.error;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 定义全局捕获异常 底层实现原理使用AOP技术 <br>
 * 作者: 每特教育-余胜军<br>
 * 联系方式:QQ644064779|WWW.itmayiedu.com<br>
 */

@ControllerAdvice("com.itmayiedu.controller")
public class GlobalExceptionHandler {

	// 拦截json格式
	@ExceptionHandler(RuntimeException.class)
	@ResponseBody
	public Map<String, Object> JSONErrorHandler() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("errorCode", "500");
		map.put("errorMsg", "系统错误!");
		return map;
	}

}
