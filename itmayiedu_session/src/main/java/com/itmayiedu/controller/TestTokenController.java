/**
 * 功能说明:
 * 功能作者:
 * 创建日期:
 * 版权归属:每特教育|蚂蚁课堂所有 www.itmayiedu.com
 */
package com.itmayiedu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itmayiedu.session.TokenService;

/**
 * 功能说明: <br>
 * 创建作者:每特教育-余胜军<br>
 * 创建时间:2018年8月14日 下午8:37:45<br>
 * 教育机构:每特教育|蚂蚁课堂<br>
 * 版权说明:上海每特教育科技有限公司版权所有<br>
 * 官方网站:www.itmayiedu.com|www.meitedu.com<br>
 * 联系方式:qq644064779<br>
 * 注意:本内容有每特教育学员共同研发,请尊重原创版权
 */
@RestController
public class TestTokenController {

	@Autowired
	private TokenService tokenService;

	// 1. 使用token方式替代Session功能 session 原理在服务器端创建session 返回对应的sessionid 给客户端
	@RequestMapping("/put")
	public String put(String object) {
		return tokenService.put(object);
	}

	@RequestMapping("/get")
	public String get(String token) {
		return tokenService.get(token);
	}
	// 生成好的token如何存放？ 本地 移动端 存放本地文件 浏览器 存放在cookie
	// http 请求如何传递呢？ 请求头 最好建议存放在请求

}
