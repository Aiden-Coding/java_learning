
package com.aiden.controller;

import com.aiden.base.controller.BaseController;
import com.aiden.constants.BaseApiConstants;
import com.aiden.constants.Constants;
import com.aiden.entity.UserEntity;
import com.aiden.feign.UserFeign;
import com.aiden.web.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class LoginConroller extends BaseController {
	private static final String LGOIN = "login";
	private static final String INDEX = "index";
	@Autowired
	private UserFeign userFeign;

	@RequestMapping("/locaLogin")
	public String locaLogin() {
		return LGOIN;
	}

	@RequestMapping("/login")
	public String login(UserEntity userEntity, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> login = userFeign.login(userEntity);
		Integer code = (Integer) login.get(BaseApiConstants.HTTP_CODE_NAME);
		if (!code.equals(BaseApiConstants.HTTP_200_CODE)) {
			String msg = (String) login.get("msg");
			return setError(request, msg, LGOIN);
		}
		// 登录成功之后,获取token.将这个token存放在cookie
		String token = (String) login.get("data");
		CookieUtil.addCookie(response, Constants.USER_TOKEN, token, Constants.WEBUSER_COOKIE_TOKEN_TERMVALIDITY);
		return INDEX;

	}

}
