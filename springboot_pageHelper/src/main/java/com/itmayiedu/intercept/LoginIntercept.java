package com.itmayiedu.intercept;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LoginIntercept implements HandlerInterceptor {

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.info("开始拦截登录请求....");
		String token = request.getParameter("token");
		if (StringUtils.isEmpty(token)) {
			response.getWriter().println("not found token");
			return false;
		}
		return true;
	}

}
