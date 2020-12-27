/**
 * 功能说明:
 * 功能作者:
 * 创建日期:
 * 版权归属:每特教育|蚂蚁课堂所有 www.itmayiedu.com
 */
package com.itmayiedu.api.controller;

import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.deser.Deserializers.Base;
import com.itmayiedu.base.BaseApiService;
import com.itmayiedu.base.BaseRedisService;
import com.itmayiedu.base.ResponseBase;

/**
 * 功能说明: <br>
 * 创建作者:每特教育-余胜军<br>
 * 创建时间:2018年7月21日 下午10:56:37<br>
 * 教育机构:每特教育|蚂蚁课堂<br>
 * 版权说明:上海每特教育科技有限公司版权所有<br>
 * 官方网站:www.itmayiedu.com|www.meitedu.com<br>
 * 联系方式:qq644064779<br>
 * 注意:本内容有每特教育学员共同研发,请尊重原创版权
 */
@RestController
public class PayController extends BaseApiService {
	@Autowired
	private BaseRedisService baseRedisService;

	private static final Long TOKENTIME = (long) (30 * 60);

	// 先获取参数接口,返回令牌
	// 使用令牌传递参数 (不是前端调用是服务器调用)
	@RequestMapping("/getPayToken")
	public String getPayToken(Long userId, Long money) {
		// 生成令牌
		String payToken = UUID.randomUUID().toString();
		// 存放在redis中
		baseRedisService.setString(payToken, userId + "---" + money, TOKENTIME);
		return payToken;
	}

	@RequestMapping("/pay")
	public ResponseBase pay(String payToken) {
		if (StringUtils.isEmpty(payToken)) {
			return setResultError("token 不能为空!");
		}
		String result = (String) baseRedisService.getString(payToken);
		if (StringUtils.isEmpty(result)) {
			return setResultError("参数不能为空!");
		}
		// 直接处理操作数据库
		return setResultSuccessData(result);
	}

}
