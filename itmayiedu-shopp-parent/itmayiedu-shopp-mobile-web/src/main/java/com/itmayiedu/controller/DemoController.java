
package com.itmayiedu.controller;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itmayiedu.base.controller.BaseController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class DemoController extends BaseController {
	// index页面
	public static final String INDEX = "index";

	@RequestMapping("/index")
	public String index(HttpServletRequest request) {
		log.info(" 我的web工程搭建成功啦!,userName:{}");
		return INDEX;
	}

	@ResponseBody
	@RequestMapping("/getUser")
	public String getUser(String userName) {
		log.info("userName:" + userName);
		return "success";
	}

	public static void main(String[] args) {
		String userName = "123&";
		String url = URLEncoder.encode(userName);
		System.out.println("encode,url"+url);
		String dUrl=URLDecoder.decode(url);
		System.out.println("decode,dUrl"+dUrl);
	}
}
