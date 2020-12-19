
package com.itmayiedu.service;

import com.itmayiedu.dao.UserDao;

public class UserService {
	private UserDao userDao;

	public void add() {
		System.out.println(" userService add...");
		userDao.add();
	}

	public UserDao getUserDao() {

		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		System.out.println("setUseDao");
		this.userDao = userDao;
	}

}
