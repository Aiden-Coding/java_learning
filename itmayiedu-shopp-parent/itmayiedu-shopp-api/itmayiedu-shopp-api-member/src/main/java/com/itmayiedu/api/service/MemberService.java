package com.itmayiedu.api.service;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itmayiedu.base.ResponseBase;
import com.itmayiedu.entity.UserEntity;

import feign.Param;

@RequestMapping("/member")
public interface MemberService {

	// 使用userId查找用户信息
	@RequestMapping("/findUserById")
	ResponseBase findUserById(Long userId);

	@RequestMapping("/regUser")
	ResponseBase regUser(@RequestBody UserEntity user);

	// 用户登录
	@RequestMapping("/login")
	ResponseBase login(@RequestBody UserEntity user);

	// 使用token进行登录
	@RequestMapping("/findByTokenUser")
	ResponseBase findByTokenUser(@RequestParam("token") String token);
	//使用openid 查找是否已经绑定
	@RequestMapping("/findByOpenIdUser")
	ResponseBase findByOpenIdUser(@RequestParam("openId") String openId);
	@RequestMapping("/qqLoginOpenId")
	ResponseBase  qqLoginOpenId(@RequestBody UserEntity user);
}
