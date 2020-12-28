package com.mayikt.member.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mayikt.base.BaseApiService;
import com.mayikt.base.BaseResponse;
import com.mayikt.constants.Constants;
import com.mayikt.member.MemberService;
import com.mayikt.member.entity.UserEntity;
import com.mayikt.member.feign.WeiXinAppServiceFeign;
import com.mayikt.member.mapper.UserMapper;
import com.mayikt.weixin.entity.AppEntity;
import com.mayikt.weixin.service.WeiXinAppService;
import com.netflix.discovery.converters.Auto;

import io.swagger.annotations.Api;

/**
 * 
 * 
 * @description:会员服务接口实现
 * @author: 97后互联网架构师-余胜军
 * @contact: QQ644064779、微信yushengjun644 www.mayikt.com
 * @date: 2019年1月3日 下午3:03:17
 * @version V1.0
 * @Copyright 该项目“基于SpringCloud2.x构建微服务电商项目”由每特教育|蚂蚁课堂版权所有，未经过允许的情况下，
 *            私自分享视频和源码属于违法行为。
 */
@RestController
public class MemberServiceImpl extends BaseApiService<UserEntity> implements MemberService {
	@Autowired
	private UserMapper userMapper;

	@Override
	public BaseResponse<UserEntity> existMobile(String mobile) {
		// 1.验证参数
		if (StringUtils.isEmpty(mobile)) {
			return setResultError("手机号码不能为空!");
		}
		// 2.根据手机号码查询用户信息 单独定义code 表示是用户信息不存在把
		UserEntity userEntity = userMapper.existMobile(mobile);
		if (userEntity == null) {
			return setResultError(Constants.HTTP_RES_CODE_EXISTMOBILE_203, "用户信息不存在!");
		}
		// 对特殊铭感字段需要做脱敏
		userEntity.setPassword(null);
		return setResultSuccess(userEntity);
	}

}
