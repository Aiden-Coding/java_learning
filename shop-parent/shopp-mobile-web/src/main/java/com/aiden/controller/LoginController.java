
package com.aiden.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aiden.common.base.controller.BaseController;

/**
 *
 * @classDesc: 功能描述:(登录控制器)

 * @createTime: 2017年10月23日 下午1:45:52
 * @version: v1.0

 */
@Controller
public class LoginController extends BaseController {
	private static final String LOGIN = "login";

	@RequestMapping("/login")
	public String login() {
		return LOGIN;
	}


}
