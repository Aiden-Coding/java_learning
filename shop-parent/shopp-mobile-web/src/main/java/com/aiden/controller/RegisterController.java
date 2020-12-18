
package com.aiden.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aiden.common.base.controller.BaseController;
import com.aiden.constants.Constants;
import com.aiden.entity.MbUser;
import com.aiden.refactor.RefMbUserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class RegisterController extends BaseController {
	// 跳转到注册
	private static final String LOCAREGISTER = "locaRegister";
	// 登录页面
	private static final String LOGIN = "login";
	@Autowired
	private RefMbUserService refMbUserService;

	@RequestMapping("/locaRegister")
	public String locaRegister() {
		return LOCAREGISTER;
	}

	@RequestMapping("/register")
	public String register(@ModelAttribute("mbUser") MbUser mbUser, HttpServletRequest request) {
		try {
			Map<String, Object> registerResult = refMbUserService.register(mbUser);
			log.info("####refMbUserService.register result:" + registerResult);
			Integer code = (Integer) registerResult.get("code");
			Integer httpResCode200 = Constants.HTTP_RES_CODE_200;
			if (!code.equals(httpResCode200)) {
				String msg = (String) registerResult.get("msg");
				return setError(msg, request, LOCAREGISTER);
			}
			return "redirect:/" + LOGIN;
		} catch (Exception e) {
			log.error("###register() 发送错误###", e);
			return setError("注册失败!请稍后重试!", request, LOCAREGISTER);
		}

	}

	public String setError(String errorMsg, HttpServletRequest request, String addres) {
		request.setAttribute("error", errorMsg);
		return addres;
	}

	public static void main(String[] args) {
		Integer code = 200;
		Integer httpResCode200 = 200;
		System.out.println(code.equals(httpResCode200));
	}

}
