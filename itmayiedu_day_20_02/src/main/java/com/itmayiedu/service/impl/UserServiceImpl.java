package com.itmayiedu.service.impl;

import com.itmayiedu.service.OrderService;
import com.itmayiedu.service.UserService;
import com.itmayiedu.spring.extannotation.ExtResource;
import com.itmayiedu.spring.extannotation.ExtService;

//user 服务层
@ExtService
public class UserServiceImpl implements UserService {

	@ExtResource
	private OrderService orderServiceImpl;

	public void add() {
		orderServiceImpl.addOrder();
		System.out.println("使用java反射机制初始化对象..");
	}

}
