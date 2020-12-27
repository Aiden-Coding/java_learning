package com.itmayiedu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 跳转页面<br>
 * 
 * @作者说明 每特教育-余胜军 <br>
 * @联系方式 qq644064779|www.itmayieducom-蚂蚁课堂<br>
 */
@Controller
public class UserController {
  
	@RequestMapping("/pageIndex")
	public String pageIndex() {
		return "pageIndex";
	}

}
