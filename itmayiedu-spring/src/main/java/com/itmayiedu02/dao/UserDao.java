
package com.itmayiedu02.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

//userDao
@Repository("userDao02")
public class UserDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void add(String name, Integer age) {
		String sql = "INSERT INTO users(NAME, age) VALUES(?,?);";
		int update = jdbcTemplate.update(sql, name, age);
		System.out.println("数据库添加user成功..insert:" + update);
	}

	public void add() {
		System.out.println("userDao002 add....");
	}

}
