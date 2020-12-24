package com.itmayiedu.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itmayiedu.base.ResponseBase;
import com.itmayiedu.constants.Constants;
import com.itmayiedu.entity.UserEntity;
import com.itmayiedu.fegin.MemberServiceFegin;

@Controller
public class RegisterController {
	@Autowired
	private MemberServiceFegin memberServiceFegin;
	private static final String LOGIN = "login";
	private static final String REGISTER = "register";

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register() {
		return REGISTER;

	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(UserEntity user, HttpServletRequest reqest) {
		// 1.调用会员服务注册
		ResponseBase regUser = memberServiceFegin.regUser(user);
		if (!regUser.getRtnCode().equals(Constants.HTTP_RES_CODE_200)) {
			reqest.setAttribute("error", regUser.getMsg());
			return REGISTER;
		}
		// 2.注册成功，跳转到登录页面
		return LOGIN;
	}
}
