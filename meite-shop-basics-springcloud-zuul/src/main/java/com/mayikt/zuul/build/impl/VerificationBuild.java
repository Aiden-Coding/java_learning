package com.mayikt.zuul.build.impl;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mayikt.sign.SignUtil;
import com.mayikt.zuul.build.GatewayBuild;
import com.mayikt.zuul.mapper.BlacklistMapper;
import com.mayikt.zuul.mapper.entity.MeiteBlacklist;
import com.netflix.zuul.context.RequestContext;

import lombok.extern.slf4j.Slf4j;

/**
 * 参数验证
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
@Slf4j
@Component
public class VerificationBuild implements GatewayBuild {
	@Autowired
	private BlacklistMapper blacklistMapper;

	@Override
	public Boolean blackBlock(RequestContext ctx, String ipAddres, HttpServletResponse response) {
		// 2.查询数据库黑名单
		MeiteBlacklist meiteBlacklist = blacklistMapper.findBlacklist(ipAddres);
		if (meiteBlacklist != null) {
			resultError(ctx, "ip:" + ipAddres + ",Insufficient access rights");
			return false;
		}
		log.info(">>>>>>ip:{},验证通过>>>>>>>", ipAddres);
		// 3.将ip地址传递到转发服务中
		response.addHeader("ipAddres", ipAddres);
		log.info(">>>>>>ip:{},验证通过>>>>>>>", ipAddres);
		return true;
	}

	@Override
	public Boolean toVerifyMap(RequestContext ctx, String ipAddres, HttpServletRequest request) {
		// 4.外网传递参数验证
		Map<String, String> verifyMap = SignUtil.toVerifyMap(request.getParameterMap(), false);
		if (!SignUtil.verify(verifyMap)) {
			resultError(ctx, "ip:" + ipAddres + ",Sign fail");
			return false;
		}
		return true;
	}

	private void resultError(RequestContext ctx, String errorMsg) {
		ctx.setResponseStatusCode(401);
		ctx.setSendZuulResponse(false);
		ctx.setResponseBody(errorMsg);

	}
}
