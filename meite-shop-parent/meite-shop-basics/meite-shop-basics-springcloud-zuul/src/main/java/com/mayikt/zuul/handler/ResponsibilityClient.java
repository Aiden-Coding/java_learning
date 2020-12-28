package com.mayikt.zuul.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.mayikt.zuul.handler.factory.FactoryHandler;
import com.netflix.zuul.context.RequestContext;

/**
 * 责任链调用方
 * 
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
public class ResponsibilityClient {

	/**
	 * 责任链调用
	 */
	public void responsibility(RequestContext ctx, String ipAddres, HttpServletRequest request,
			HttpServletResponse response) {
		GatewayHandler oneHandler = FactoryHandler.getOneHandler();
		oneHandler.service(ctx, ipAddres, request, response);
	}
}
