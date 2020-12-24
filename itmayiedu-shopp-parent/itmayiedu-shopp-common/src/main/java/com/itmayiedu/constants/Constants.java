package com.itmayiedu.constants;

public interface Constants {
	// 响应请求成功
	String HTTP_RES_CODE_200_VALUE = "success";
	// 系统错误
	String HTTP_RES_CODE_500_VALUE = "fial";
	// 响应请求成功code
	Integer HTTP_RES_CODE_200 = 200;
	// 系统错误
	Integer HTTP_RES_CODE_500 = 500;

	// 发送邮件
	String MSG_EMAIL = "email";
	// 会员token
	String TOKEN_MEMBER = "TOKEN_MEMBER";
	// 用户有效期 90天
	Long TOKEN_MEMBER_TIME = (long) (60 * 60 * 24 * 90);
	// 客户端cookie信息
	String MEMBER_TOKEN_KEY = "token";
	// 客户端cookie信息
	int MEMBER_TOKEN_COOKIE = (60 * 60 * 24 * 90);

	// qq用户未授权登录
	Integer HTTP_RES_CODE_201 = 201;

}
