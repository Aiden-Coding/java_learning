package com.itmayiedu.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

	@RequestMapping("/getMemberAll")
	public List<String> getMemberAll() {
		List<String> listUser = new ArrayList<String>();
		listUser.add("zhangsan");
		listUser.add("lisi");
		listUser.add("wangwu");
		return listUser;
	}

}
