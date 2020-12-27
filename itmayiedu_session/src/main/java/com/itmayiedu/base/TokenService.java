/**
 * 功能说明:
 * 功能作者:
 * 创建日期:
 * 版权归属:每特教育|蚂蚁课堂所有 www.itmayiedu.com
 */
package com.itmayiedu.base;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 功能说明: <br>
 * 创建作者:每特教育-余胜军<br>
 * 创建时间:2018年8月13日 下午7:22:12<br>
 * 教育机构:每特教育|蚂蚁课堂<br>
 * 版权说明:上海每特教育科技有限公司版权所有<br>
 * 官方网站:www.itmayiedu.com|www.meitedu.com<br>
 * 联系方式:qq644064779<br>
 * 注意:本内容有每特教育学员共同研发,请尊重原创版权
 */
@Service
public class TokenService {
	@Autowired
	private RedisService redisService;

	// 新增 返回token
	public String put(Object object) {
		String token = getToken();
		redisService.setString(token, object);
		return token;
	}

	// 获取信息
	public String get(String token) {
		String reuslt = redisService.getString(token);
		return reuslt;
	}

	public String getToken() {
		return UUID.randomUUID().toString();
	}

}
