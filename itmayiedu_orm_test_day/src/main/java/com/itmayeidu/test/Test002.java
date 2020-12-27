package com.itmayeidu.test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.itmayiedu.service.JDBCUtils;

public class Test002 {

	public static void main(String[] args) throws SQLException {
		List<Object> list = new ArrayList<>();
		list.add("Xiaoxin");
		list.add("21");
		ResultSet query = JDBCUtils.query("select * from User where userName=? and userAge=?", list);
		while (query.next()) {
			String userName = (String) query.getObject("userName");
			System.out.println(userName);
		}
	}
}
