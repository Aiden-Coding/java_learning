package com.itmayiedu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;

import com.itmayiedu.dao.UserDao;
import com.itmayiedu.utils.TransactionUtils;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private TransactionUtils transactionUtils;

	// public void add() {
	// TransactionStatus transactionStatus = null;
	// try {
	// transactionStatus = transactionUtils.begin();
	// userDao.add("wangmazi", 27);
	// int i = 1 / 0;
	// System.out.println("我是add方法");
	// userDao.add("zhangsan", 16);
	// transactionUtils.commit(transactionStatus);
	// } catch (Exception e) {
	// e.printStackTrace();
	// } finally {
	// if (transactionStatus != null) {
	// transactionStatus.rollbackToSavepoint(transactionStatus);
	// }
	// }
	//
	// }

	// @Transactional
	// public void add() {
	// try {
	// userDao.add("wangmazi", 27);
	// int i = 1 / 0;
	// System.out.println("我是add方法");
	// userDao.add("zhangsan", 16);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }

	@Transactional
	public void add() {
		userDao.add("wangmazi", 27);
		int i = 1 / 0;
		System.out.println("我是add方法");
		userDao.add("zhangsan", 16);
	}

}
