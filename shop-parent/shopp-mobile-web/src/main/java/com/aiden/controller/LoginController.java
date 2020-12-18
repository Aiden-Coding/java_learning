
package com.aiden.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aiden.common.base.controller.BaseController;

/**
 *
 * @classDesc: 功能描述:(登录控制器)
 * @author: 蚂蚁课堂创始人-余胜军
 * @QQ: 644064779
 * @QQ粉丝群: 116295598
 * @createTime: 2017年10月23日 下午1:45:52
 * @version: v1.0
 * @copyright:每特学院(蚂蚁课堂)上海每特教育科技有限公司
 */
@Controller
public class LoginController extends BaseController {
	private static final String LOGIN = "login";

	@RequestMapping("/login")
	public String login() {
		return LOGIN;
	}


}
