package com.itmayiedu.test02.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itmayiedu.test01.mapper.UserMapperTest01;
import com.itmayiedu.test02.mapper.UserMapperTest02;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceTest02 {
	@Autowired
	private UserMapperTest01 userMapperTest01;
	@Autowired
	private UserMapperTest02 userMapperTest02;

	@Transactional(transactionManager = "test2TransactionManager")
	public Integer insertUser(String name, Integer age) {
		int insertResult = userMapperTest01.insert(name, age);
		int i = 1 / 0;
		log.info("##insertResult:{}##", insertResult);
		return insertResult;
	}

	@Transactional
	public Integer inserUserTest01AndTest02(String name, Integer age) {
		int insertResult1 = userMapperTest01.insert(name, age);
		int insertResult2 = userMapperTest02.insert(name, age);
		int result = insertResult1 + insertResult2;
		log.info("###inserUserTest01AndTest02####result:" + result);
		// int i = 1 / 0;
		return result;

	}

}
