package com.itmayiedu.service.impl;

import com.itmayiedu.service.OrderService;
import com.itmayiedu.spring.extannotation.ExtService;

@ExtService
public class OrderServiceImpl implements OrderService {
	public void addOrder() {
		System.out.println("addOrder");
	}
}
