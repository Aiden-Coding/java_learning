package com.itmayiedu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itmayiedu.service.MemberFeign;

@RestController
public class OrderFeginController {
	@Autowired
	private MemberFeign memberFeign;

	@RequestMapping("/getToOrderMemberAll")
	public List<String> getToOrderMemberAll() {
		System.out.println("order fegin 工程调用member工程");
		return memberFeign.getToOrderMemberAll();
	}
	
	@RequestMapping("/getOrderInfo")
	public String getOrderInfo(){
		return "getOrderInfo";
	}

}
