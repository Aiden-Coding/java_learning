package com.itmayiedu.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 这是我们第 二个 Controller<br>
 * 作者: 每特教育-余胜军<br>
 * 联系方式:QQ644064779|WWW.itmayiedu.com<br>
 */
@RestController
public class MemberController {

	@RequestMapping("/getMember")
	public Map<String, Object> getMember() {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("errorCode", 200);
		hashMap.put("errorMsg", "蚂蚁课堂");
		return hashMap;
	}

}
