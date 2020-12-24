package com.itmayiedu.controller;

import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itmayiedu.base.ResponseBase;
import com.itmayiedu.constants.Constants;
import com.itmayiedu.entity.UserEntity;
import com.itmayiedu.fegin.MemberServiceFegin;
import com.itmayiedu.utils.CookieUtil;

@Controller
public class IndexController {
	private final static String INDEX = "index";
	private final static String REGISTER = "register";
	@Autowired
	private MemberServiceFegin memberServiceFegin;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(HttpServletRequest reqest) {
		// 1.从cookie 中获取用户信息
		String token = CookieUtil.getUid(reqest, Constants.MEMBER_TOKEN_KEY);
		// 2.获取到token后，查询用户信息
		if (!StringUtils.isEmpty(token)) {
			ResponseBase tokenUser = memberServiceFegin.findByTokenUser(token);
			if (tokenUser.getRtnCode().equals(Constants.HTTP_RES_CODE_200)) {
				LinkedHashMap userData = (LinkedHashMap) tokenUser.getData();
				String username = (String) userData.get("username");
				reqest.setAttribute("userName", username);
			}
		}
		return INDEX;
	}

	@RequestMapping("/index")
	public String indexRequest() {
		return INDEX;
	}

	@RequestMapping("/register")
	public String register() {
		return REGISTER;
	}

}
