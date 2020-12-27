package com.itmayeidu.test;

import com.itmayiedu.dao.UserDao;

public class Test001 {

	public static void main(String[] args) {
		UserDao userDao = SQLSession.getMapper(UserDao.class);
		int insertUser = userDao.insertUser(null, null);
	}

}
