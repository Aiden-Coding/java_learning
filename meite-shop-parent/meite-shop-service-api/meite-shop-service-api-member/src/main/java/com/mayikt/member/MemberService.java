package com.mayikt.member;

import org.springframework.web.bind.annotation.GetMapping;

import com.mayikt.weixin.entity.AppEntity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * 
 * @description: 会员服务接口
 * @author: 97后互联网架构师-余胜军
 * @contact: QQ644064779、微信yushengjun644 www.mayikt.com
 * @date: 2019年1月3日 下午3:03:17
 * @version V1.0
 * @Copyright 该项目“基于SpringCloud2.x构建微服务电商项目”由每特教育|蚂蚁课堂版权所有，未经过允许的情况下，
 *            私自分享视频和源码属于违法行为。
 */

@Api(tags = "会员服务接口")
public interface MemberService {

	/**
	 * 会员服务接口调用微信接口
	 * 
	 * @return
	 */
	@ApiOperation(value = "会员服务调用微信服务")
	@GetMapping("/memberInvokeWeixin")
	public AppEntity memberInvokeWeixin();

}
