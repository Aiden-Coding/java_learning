package com.mayikt.pay.strategy.impl;

import com.mayikt.pay.mapper.entity.PaymentChannelEntity;
import com.mayikt.pay.strategy.PayStrategy;
import com.mayikt.weixin.out.dto.PayMentTransacDTO;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 
 * 
 * @description: 银联支付渠道实现
 * @author: 97后互联网架构师-余胜军
 * @contact: QQ644064779、微信yushengjun644 www.mayikt.com
 * @date: 2019年1月3日 下午3:03:17
 * @version V1.0
 * @Copyright 该项目“基于SpringCloud2.x构建微服务电商项目”由每特教育|蚂蚁课堂版权所有，未经过允许的情况下，
 *            私自分享视频和源码属于违法行为。
 */
@Slf4j
public class UnionPayStrategy implements PayStrategy {

	@Override
	public String toPayHtml(PaymentChannelEntity pymentChannel, PayMentTransacDTO payMentTransacDTO) {
		log.info(">>>>>银联参数封装开始<<<<<<<<");
		// Plugin
		return "银联支付from表单提交";
	}

}
