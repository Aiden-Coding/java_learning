package com.itmayiedu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@SuppressWarnings("unchecked")
@Service
public class MemberService {
	@Autowired
	RestTemplate restTemplate;

	public List<String> getOrderByUserList() {
		return restTemplate.getForObject("http://service-member/getUserList", List.class);
	}
}
