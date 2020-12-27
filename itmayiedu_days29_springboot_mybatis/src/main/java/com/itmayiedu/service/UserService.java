package com.itmayiedu.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itmayiedu.mapper.UserMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

	@Autowired
	private UserMapper userMapper;

	@Transactional
	public Integer insertUser(String name, Integer age) {
		int insertResult = userMapper.insert(name, age);
		int i = 1 / 0;
		log.info("##insertResult:{}##", insertResult);
		return insertResult;
	}

}
