package com.itmayiedu.intercept;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import lombok.extern.slf4j.Slf4j;

/**
 * 微服务登录 通过CAS验证（SSO），令牌（Token） <br>
 * 判断请求 如果没有传入token，就报错（需要登录） 正常公司token存放在请求头<br>
 * 作者: 每特教育-余胜军<br>
 * 联系方式:QQ644064779|WWW.itmayiedu.com<br>
 */
@Component
@Slf4j
public class LoginIntercept implements HandlerInterceptor {

	/**
	 * 请求之前拦截
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String token = request.getParameter("token");
		if (StringUtils.isEmpty(token)) {
			response.getWriter().println("<h1>not found token</h1>");
			// 不会继续往下继续执行...
			return false;

		}
		// true 继续往接口下执行
		return true;
	}

}
