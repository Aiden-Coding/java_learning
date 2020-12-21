package com.itmayiedu.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {
	@Value("${server.port}")
	private String serverPort;
	private static int count = 0;

	@RequestMapping("/getMemberAll")
	public List<String> getMemberAll() {
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
			// TODO: handle exception
		}
		count++;
		List<String> listUser = new ArrayList<String>();
		listUser.add("zhangsan");
		listUser.add("lisi");
		listUser.add("wangwu");
		listUser.add("count" + count);
		listUser.add("serverPort:" + serverPort);
		System.out.println("count:" + count);
		return listUser;
	}

	@RequestMapping("/getMemberServiceApi")
	public String getMemberServiceApi() {
		return "this is 会员 服务工程";
	}
}
