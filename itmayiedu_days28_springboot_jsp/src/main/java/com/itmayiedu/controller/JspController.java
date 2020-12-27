package com.itmayiedu.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 这是JSP整合SPringboot<br>
 * 作者: 每特教育-余胜军<br>
 * 联系方式:QQ644064779|WWW.itmayiedu.com<br>
 */
@Controller
public class JspController {
	private static final Logger logger = LoggerFactory.getLogger(JspController.class);

	@RequestMapping("/jspIndex")
	public String jspIndex() {

		logger.info("springboot 集成logger 日志成功!!!");

		return "jspIndex";
	}

}
