package com.mayikt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mayikt.entity.UserEntity;
import com.mayikt.mapper.UserMapper;

@Service
public class UserService {
	@Autowired
	private UserMapper userMapper;

	public List<UserEntity> findUser() {
		return userMapper.findUser();
	}

	public List<UserEntity> insertUser(String userName) {
		return userMapper.insertUser(userName);
	}

}
