
package com.itmayiedu.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionController {

	@Value("${server.port}")
	private String PORT;

	public static void main(String[] args) {
		SpringApplication.run(SessionController.class, args);
	}

	@RequestMapping("/index")
	public String index() {
		return "index:" + PORT;
	}

	/**
	 * 
	 * @methodDesc: 功能描述:(往session存放值)
	 * @author: 余胜军
	 * @param: @param
	 *             httpSession
	 * @param: @param
	 *             sessionKey
	 * @param: @param
	 *             sessionValue
	 * @param: @return
	 * @createTime:2017年10月8日 下午3:55:26
	 * @returnType:@param httpSession
	 * @returnType:@param sessionKey
	 * @returnType:@param sessionValue
	 * @returnType:@return String
	 * @copyright:上海每特教育科技有限公司
	 * @QQ:644064779
	 */
	@RequestMapping("/setSession")
	public String setSession(HttpServletRequest request, String sessionKey, String sessionValue) {
		HttpSession session = request.getSession(true);
		session.setAttribute(sessionKey, sessionValue);
		return "success,port:" + PORT;
	}

	/**
	 * 
	 * @methodDesc: 功能描述:(从Session获取值)
	 * @author: 余胜军
	 * @param: @param
	 *             httpSession
	 * @param: @param
	 *             sessionKey
	 * @param: @return
	 * @createTime:2017年10月8日 下午3:55:47
	 * @returnType:@param httpSession
	 * @returnType:@param sessionKey
	 * @returnType:@return String
	 * @copyright:上海每特教育科技有限公司
	 * @QQ:644064779
	 */
	@RequestMapping("/getSession")
	public String getSession(HttpServletRequest request, String sessionKey) {
		HttpSession session =null;
		try {
		 session = request.getSession(false);
		} catch (Exception e) {
		  e.printStackTrace();
		}
		String value=null;
		if(session!=null){
			value = (String) session.getAttribute(sessionKey);
		}
		return "sessionValue:" + value + ",port:" + PORT;
	}

}
