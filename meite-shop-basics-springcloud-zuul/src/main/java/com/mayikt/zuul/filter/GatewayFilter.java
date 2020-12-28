package com.mayikt.zuul.filter;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mayikt.sign.SignUtil;
import com.mayikt.zuul.build.GatewayDirector;
import com.mayikt.zuul.mapper.BlacklistMapper;
import com.mayikt.zuul.mapper.entity.MeiteBlacklist;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 
 * 网关拦截
 * 
 * @description:
 * @author: 97后互联网架构师-余胜军
 * @contact: QQ644064779、微信yushengjun644 www.mayikt.com
 * @date: 2019年1月3日 下午3:03:17
 * @version V1.0
 * @Copyright 该项目“基于SpringCloud2.x构建微服务电商项目”由每特教育|蚂蚁课堂版权所有，未经过允许的情况下，
 *            私自分享视频和源码属于违法行为。
 */
@Component
@Slf4j
public class GatewayFilter extends ZuulFilter {

	@Autowired
	private GatewayDirector gatewayDirector;

	@Override
	public Object run() throws ZuulException {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		HttpServletResponse response = ctx.getResponse();
		response.setContentType("UTF-8");
		// 1.获取ip地址
		String ipAddres = getIpAddr(request);
		if (StringUtils.isEmpty(ipAddres)) {
			resultError(ctx, "未能够获取到ip地址");
		}
		gatewayDirector.direcot(ctx, ipAddres, response, request);
		return null;
	}

	private void resultError(RequestContext ctx, String errorMsg) {
		ctx.setResponseStatusCode(401);
		ctx.setSendZuulResponse(false);
		ctx.setResponseBody(errorMsg);

	}

	@Override
	public boolean shouldFilter() {

		return true;
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	/**
	 * 在方法之前拦截
	 * 
	 * @return
	 */
	@Override
	public String filterType() {

		return "pre";
	}

	/**
	 * 获取Ip地址
	 * 
	 * @param request
	 * @return
	 */
	public String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

}
