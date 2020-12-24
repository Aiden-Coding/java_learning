package com.itmayiedu.controller;

import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.oauth.Oauth;

@Controller
public class LoginController {
	private static final String LOGIN = "login";
	private static final String INDEX = "redirect:/";

	private static final String ERROR = "error";
	//
	private static final String RELATION = "relation";

	@Autowired
	private MemberServiceFegin memberServiceFegin;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return LOGIN;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(UserEntity userEntity, HttpServletRequest reqest, HttpServletResponse response) {
		// 1.调用登录接口
		ResponseBase login = memberServiceFegin.login(userEntity);
		if (!login.getRtnCode().equals(Constants.HTTP_RES_CODE_200)) {
			reqest.setAttribute("error", "账号或密码错误!");
			return LOGIN;
		}
		LinkedHashMap linkedHashMap = (LinkedHashMap) login.getData();
		return cookieLogin(reqest, response, linkedHashMap);
	}

	public String cookieLogin(HttpServletRequest request, HttpServletResponse response, LinkedHashMap linkedHashMap) {
		// 2.登录成功，获取token信息
		String memberToken = (String) linkedHashMap.get("memberToken");
		if (StringUtils.isEmpty(memberToken)) {
			request.setAttribute("error", "token已经失效!");
			return LOGIN;
		}
		// 3.将token存放在cookie中
		CookieUtil.addCookie(response, Constants.MEMBER_TOKEN_KEY, memberToken, Constants.MEMBER_TOKEN_COOKIE);
		return INDEX;
	}

	@RequestMapping("/locaQQlogin")
	public String locaQQlogin(HttpServletRequest request) throws QQConnectException {
		String authorizeURL = new Oauth().getAuthorizeURL(request);
		return "redirect:" + authorizeURL;
	}

	@RequestMapping("/qqLoginCallback")
	public String qqLoginCallback(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession)
			throws QQConnectException {
		AccessToken accessTokenObj = new Oauth().getAccessTokenByRequest(request);
		if (accessTokenObj == null) {
			request.setAttribute("error", "qq授权失败!");
			return ERROR;
		}
		String accessToken = accessTokenObj.getAccessToken();
		if (StringUtils.isEmpty(accessToken)) {
			request.setAttribute("error", "qq授权失败!");
			return ERROR;
		}
		// 获取openid
		OpenID openIdObj = new OpenID(accessToken);
		String userOpenID = openIdObj.getUserOpenID();
		ResponseBase openIdUser = memberServiceFegin.findByOpenIdUser(userOpenID);
		// 用戶沒有关联QQ账号
		if (openIdUser.getRtnCode().equals(Constants.HTTP_RES_CODE_201)) {
			// 跳转到管理账号
			httpSession.setAttribute("qqOpenid", userOpenID);
			return RELATION;
		}
		// 如果用户关联账号 直接登录
		LinkedHashMap dataMap = (LinkedHashMap) openIdUser.getData();
		return cookieLogin(request, response, dataMap);
	}

	// qq授权登录
	@RequestMapping(value = "/qqRelation", method = RequestMethod.POST)
	public String qqRelation(UserEntity userEntity, HttpServletRequest reqest, HttpServletResponse response,HttpSession  httpSession) {
		String openid = (String) httpSession.getAttribute("qqOpenid");
		userEntity.setOpenid(openid);
		// 1.调用登录接口
		ResponseBase login = memberServiceFegin.qqLoginOpenId(userEntity);
		if (!login.getRtnCode().equals(Constants.HTTP_RES_CODE_200)) {
			reqest.setAttribute("error", "账号或密码错误!");
			return RELATION;
		}
		LinkedHashMap linkedHashMap = (LinkedHashMap) login.getData();
		return cookieLogin(reqest, response, linkedHashMap);
	}

}
