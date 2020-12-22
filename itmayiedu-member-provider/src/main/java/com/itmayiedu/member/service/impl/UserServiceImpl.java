package com.itmayiedu.member.service.impl;

import com.itmayiedu.service.UserService;

public class UserServiceImpl implements UserService {

	public String getUser(Long userId) {
		System.out.println("###会员服务接受参数开始userId:" + userId);
		if (userId == 1) {
			return "余胜军";
		}
		if (userId == 2) {
			return "张杰";
		}
		System.out.println("###会员服务接受参数结束###");
		return "未找到用户...";
	}

}
