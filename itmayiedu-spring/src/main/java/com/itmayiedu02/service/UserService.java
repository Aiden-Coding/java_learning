
package com.itmayiedu02.service;

import java.beans.Transient;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
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

	public void add() {
		System.out.println(" userService02 add...");
		TransactionStatus begin = null;
		try {
			begin = transactionUtils.begin();
			userDao.add("yushengjun", 20);
			// int i = 1 / 0;
			transactionUtils.commit(begin);
		} catch (Exception e) {
			e.printStackTrace();
			transactionUtils.rollback(begin);
		}

	}

	public void add02() {
		System.out.println(" userService02 add...");
		try {
			userDao.add("yushengjun", 20);
			int i = 1 / 0;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

//	@Transactional(propagation=Propagation.REQUIRED)
	public void add03() {
//		logService.addLog();
//		System.out.println(" userService02 add003...");
//		userDao.add("yushengjun", 20);
//		int i = 1 / 0;

	}

	public UserDao getUserDao() {

		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		System.out.println("setUseDao");
		this.userDao = userDao;
	}

}
