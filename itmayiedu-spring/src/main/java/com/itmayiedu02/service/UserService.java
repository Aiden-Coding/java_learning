
package com.itmayiedu02.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;

import com.itmayiedu02.TransactionUtils;
import com.itmayiedu02.dao.UserDao;

import net.sf.cglib.proxy.Factory;

//默认注入容器 id userService
@Service
public class UserService {

	// @Autowired与Resource区别是什么？
	// Autowired 默认是以类型进行查找 Resource默认是名称进行查找

	@Resource(name = "userDao02")
	private UserDao userDao;
	@Autowired
	private TransactionUtils transactionUtils;
	@Autowired
	private LogService logService;

	// @Autowired(required=false)@Qualifier("userDao02")
	// private UserDao userDao;
	@Transactional()
	public void add() {
		System.out.println(" userService02 add...");
		logService.addLog();
		int i =1/0;
		userDao.add("yushengjun", 19);

	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		System.out.println("setUseDao");
		this.userDao = userDao;
	}

}
