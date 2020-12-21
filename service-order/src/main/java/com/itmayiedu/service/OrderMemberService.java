package com.itmayiedu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderMemberService {
	@Autowired
	private RestTemplate restTemplate;

	public List<String> getOrderUserAll() {
	return restTemplate.getForObject("http://service-member/getMemberAll", List.class);
	}

}
