package com.itmayiedu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itmayiedu.entity.User;
import com.itmayiedu.mapper.UserMapper;

@Service
public class UserService {
	@Autowired
	private UserMapper userMapper;

	/**
	 * page 当前页数<br>
	 * size 当前页数展示数据<br>
	 * 作者: 每特教育-余胜军<br>
	 * 联系方式:QQ644064779|WWW.itmayiedu.com<br>
	 * 
	 * @param page
	 * @param size
	 * @return
	 */
	public PageInfo<User> findUserList(Integer page, Integer size) {
		// 开启分页查询,放在查询语句上一行
		PageHelper.startPage(page, size);
		List<User> listUser = userMapper.findUserList();
		// 封装分页结果集
		PageInfo<User> pageInfo = new PageInfo<User>(listUser);
		return pageInfo;
	}

}
