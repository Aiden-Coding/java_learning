package com.itmayiedu.test02.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itmayiedu.test02.mapper.UserMapperTest02;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceTest02 {

	@Autowired
	private UserMapperTest02 userMapper;

	@Transactional(transactionManager = "test2TransactionManager")
	public Integer insertUser(String name, Integer age) {
		int insertResult = userMapper.insert(name, age);
		int i = 1 / 0;
		log.info("##insertResult:{}##", insertResult);
		return insertResult;
	}

}
