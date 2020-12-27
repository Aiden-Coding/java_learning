/**
 * 功能说明:
 * 功能作者:
 * 创建日期:
 * 版权归属:每特教育|蚂蚁课堂所有 www.itmayiedu.com
 */
package com.itmayiedu.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能说明: <br>
 * 创建作者:每特教育-余胜军<br>
 * 创建时间:2018年8月13日 上午10:47:26<br>
 * 教育机构:每特教育|蚂蚁课堂<br>
 * 版权说明:上海每特教育科技有限公司版权所有<br>
 * 官方网站:www.itmayiedu.com|www.meitedu.com<br>
 * 联系方式:qq644064779<br>
 * 注意:本内容有每特教育学员共同研发,请尊重原创版权
 */
@SpringBootApplication
@RestController
public class TestSessionController {
	@Value("${server.port}")
	private String serverPort;

	@RequestMapping("/")
	public String index() {
		return serverPort;
	}

	// 创建session 会话
	@RequestMapping("/createSession")
	public String createSession(HttpServletRequest request, String nameValue) {
		// 默认 创建一个session，
		HttpSession session = request.getSession();
		System.out.println(
				"存入Session  sessionid:信息" + session.getId() + ",nameValue:" + nameValue + ",serverPort:" + serverPort);
		session.setAttribute("name", nameValue);
		return "success-" + serverPort;
	}

	// 获取session 会话
	@RequestMapping("/getSession")
	public Object getSession(HttpServletRequest request) {
		// 设置为true 情况下的时候，客户端使用对应的sessionid 查询不到对应的sesison 会直接创建一个新的session
		// 设置为false 情况下的时候，客户端使用对应的sessionid 查询不到对应的sesison 不 会直接创建一个新的session
		HttpSession session = request.getSession(false);
		if (session == null) {
			return serverPort + "  该服务器上没有存放对应的session值";
		}
		System.out.println("获取Session sessionid:信息" + session.getId() + "serverPort:" + serverPort);
		Object value = session.getAttribute("name");
		return serverPort + "-" + value;
	}

}
