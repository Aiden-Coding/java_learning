package com.itmayiedu.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class OrderMemberService {
	@Autowired
	private RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "orderError")
	public List<String> getOrderUserAll() {
		return restTemplate.getForObject("http://service-member/getMemberAll", List.class);
	}

	public List<String> orderError() {
		List<String> listUser = new ArrayList<String>();
		listUser.add("not orderUser list");
		return listUser;
	}
}
