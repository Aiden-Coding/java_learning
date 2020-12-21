package com.itmayiedu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itmayiedu.service.MemberService;

@RestController
public class OrderController {

	@Autowired
	private MemberService memberService;

	@RequestMapping("/getOrderByUserList")
	public List<String> getOrderByUserList() {
		return memberService.getOrderByUserList();
	}

}
