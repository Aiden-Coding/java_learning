package com.mayikt.zuul.handler.factory;

import java.util.ArrayList;
import java.util.List;

import com.mayikt.core.utils.SpringContextUtil;
import com.mayikt.zuul.handler.GatewayHandler;

/**
 * 使用工厂获取Handler
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
public class FactoryHandler {

	/**
	 * 责任链启动方式：
	 * 
	 * 1.将每一个Handler存放到集合中实现遍历
	 * 
	 * @return
	 */
	public static GatewayHandler getOneHandler() {
		// 1.黑名单判断
		GatewayHandler gatewayHandler1 = (GatewayHandler) SpringContextUtil.getBean("blacklistHandler");
		// 2.api验证签名
		GatewayHandler gatewayHandler2 = (GatewayHandler) SpringContextUtil.getBean("apiAuthorityHandler");
		gatewayHandler1.setNextHandler(gatewayHandler2);
		// 3.api接口验证token
		GatewayHandler gatewayHandler3 = (GatewayHandler) SpringContextUtil.getBean("apiCheckTokenHandler");
		gatewayHandler2.setNextHandler(gatewayHandler3);
		return gatewayHandler1;

	}

}
