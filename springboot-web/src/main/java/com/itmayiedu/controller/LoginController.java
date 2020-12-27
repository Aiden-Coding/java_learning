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

import com.itmayiedu.entity.UserEntity;
import com.itmayiedu.mapper.UserMapper;

/**
 * 功能说明: <br>
 * 创建作者:每特教育-余胜军<br>
 * 创建时间:2018年7月10日 下午4:44:54<br>
 * 教育机构:每特教育|蚂蚁课堂<br>
 * 版权说明:上海每特教育科技有限公司版权所有<br>
 * 官方网站:www.itmayiedu.com|www.meitedu.com<br>
 * 联系方式:qq644064779<br>
 * 注意:本内容有每特教育学员共同研发,请尊重原创版权
 */
@RestController
public class LoginController {
	@Autowired
	private UserMapper userMapper;

	@RequestMapping("/login")
	public String login(UserEntity userEntity) {
		System.out.println("账号密码信息:userEntity:" + userEntity.toString());
		UserEntity login = userMapper.login(userEntity);
		return login == null ? "登陆失败!" : "登陆成功!";
	}

}
