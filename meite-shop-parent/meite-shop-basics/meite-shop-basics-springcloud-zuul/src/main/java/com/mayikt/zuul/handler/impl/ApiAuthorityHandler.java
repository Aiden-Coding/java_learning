package com.mayikt.zuul.handler.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.mayikt.zuul.handler.GatewayHandler;
import com.netflix.zuul.context.RequestContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ApiAuthorityHandler extends BaseHandler implements GatewayHandler {

	@Override
	public void service(RequestContext ctx, String ipAddres, HttpServletRequest request, HttpServletResponse response) {
		log.info("<<<<流程2判断api验证签名判断>>>>>>>>");
		this.nextGatewayHandler.service(ctx, ipAddres, request, response);
 
	}

	

	// 责任链关联 是有两种实现思路

}