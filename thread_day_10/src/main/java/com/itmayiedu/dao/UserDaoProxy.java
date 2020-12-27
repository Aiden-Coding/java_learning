package com.itmayiedu.dao;

public class UserDaoProxy implements IUserDao {
	private IUserDao target;

	public UserDaoProxy(IUserDao iuserDao) {
		this.target = iuserDao;
	}

	public void save() {
		System.out.println("开启事物...");
		target.save();
		System.out.println("关闭事物...");
	}

}
