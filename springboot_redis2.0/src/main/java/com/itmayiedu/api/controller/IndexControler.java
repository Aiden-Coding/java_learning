/**
 * 功能说明:
 * 功能作者:
 * 创建日期:
 * 版权归属:每特教育|蚂蚁课堂所有 www.itmayiedu.com
 */
package com.itmayiedu.api.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itmayiedu.entity.Users;
import com.itmayiedu.service.RedisService;
import com.itmayiedu.service.UserService;

/**
 * 功能说明: <br>
 * 创建作者:每特教育-余胜军<br>
 * 创建时间:2018年8月1日 下午4:20:33<br>
 * 教育机构:每特教育|蚂蚁课堂<br>
 * 版权说明:上海每特教育科技有限公司版权所有<br>
 * 官方网站:www.itmayiedu.com|www.meitedu.com<br>
 * 联系方式:qq644064779<br>
 * 注意:本内容有每特教育学员共同研发,请尊重原创版权
 */
@RestController
public class IndexControler {

	@Autowired
	private RedisService redisService;
	@Autowired
	private UserService userService;

	@RequestMapping("/setString")
	public String setString(String key, String value) {
		// redisService.set(key, value, 60l);
		redisService.setString(key, value);
		return "success";
	}

	@RequestMapping("/getString")
	public String getString(String key) {
		return redisService.getString(key);
	}

	@RequestMapping("/setSet")
	public String setSet() {
		Set<String> set = new HashSet<String>();
		set.add("yushengjun");
		set.add("lisi");
		redisService.setSet("setTest", set);
		return "success";
	}

	@RequestMapping("/getUser")
	public Users getUser(Long id) {
		Users user = userService.getUser(id);
		return user;
	}
}
