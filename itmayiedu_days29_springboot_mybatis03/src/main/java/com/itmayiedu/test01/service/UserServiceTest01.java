package com.itmayiedu.test01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itmayiedu.test01.mapper.UserMapperTest01;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceTest01 {

	@Autowired
	private UserMapperTest01 userMapper;
	@Transactional(transactionManager = "test1TransactionManager")

	public Integer insertUser(String name, Integer age) {
		int insertResult = userMapper.insert(name, age);
		int i = 1 / 0;
		log.info("##insertResult:{}##", insertResult);
		return insertResult;
	}

}
