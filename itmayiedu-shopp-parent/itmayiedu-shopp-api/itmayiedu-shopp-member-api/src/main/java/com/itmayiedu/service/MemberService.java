package com.itmayiedu.service;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itmayiedu.base.ResponseBase;
import com.itmayiedu.entity.UserEntity;

@RequestMapping("/member")
public interface MemberService {



	@RequestMapping("/findUser")
	public ResponseBase findUser(Long userId);

	@RequestMapping("/register")
	public ResponseBase register(@RequestBody UserEntity user);
}
