package com.itmayiedu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itmayiedu.entity.User;
import com.itmayiedu.mapper.UserMapper;

/**
 * springboot 整合pageHelper<br>
 * 作者: 每特教育-余胜军<br>
 * 联系方式:QQ644064779|WWW.itmayiedu.com<br>
 */
@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;

	/**
	 * page 当前页数<br>
	 * pageSize 当前展示多少个<br>
	 * 作者: 每特教育-余胜军<br>
	 * 联系方式:QQ644064779|WWW.itmayiedu.com<br>
	 * 
	 * @param page
	 * @param pageSize
	 * @return
	 */
	// 思路
	public PageInfo<User> findUserList(int page, int pageSize) {
		// mysql 查询 limit oraclet 伪列 sqlserver top
		// pageHelper 帮我们生成分页语句
		PageHelper.startPage(page, pageSize); // 底层实现原理采用改写语句
		List<User> listUser = userMapper.findUserList();
		//	PageInfo<User> pageInfoUserList= userMapper.findUserList();
		// 返回给客户端展示
		PageInfo<User> pageInfoUserList = new PageInfo<User>(listUser);
		// 实现原理
		return pageInfoUserList;
	}

}
