
package com.itmayiedu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public String regit(String name, String pwd) {
		// 1.获取userId
		String insertSQLUserId = "INSERT INTO uuid VALUES (NULL);  ";
		jdbcTemplate.update(insertSQLUserId);
		Long userId = jdbcTemplate.queryForObject("select last_insert_id()", Long.class);
		// 2.拼接表名称
		String tableName = "user" + userId % 3;
		// 3.插入数据
		String inserSQLUser = "insert into " + tableName + " values('" + userId + "','" + name + "','" + pwd + "');";
	    System.out.println("sql:"+inserSQLUser);
		jdbcTemplate.update(inserSQLUser);
		return "success";
	}

	public String get(Long userId) {
		String tableName = "user" + userId % 3;
		String selectUserSQL = "select name from " + tableName + " where id ='" + userId + "';";
		System.out.println("sql:"+selectUserSQL);
		String name = jdbcTemplate.queryForObject(selectUserSQL, String.class);
		return name;
	}

}
