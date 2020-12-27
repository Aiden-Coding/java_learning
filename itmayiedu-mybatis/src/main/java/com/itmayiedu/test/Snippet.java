package com.itmayiedu.test;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.itmayiedu.entity.User;
import com.itmayiedu.mappers.UserMapper;

public class Snippet {

	public static void main(String[] args) throws IOException {
		SqlSessionFactory sqlSessionFactory;
		Reader reader;
		String resource = "mybatis.xml";
		// 加载一个流
		reader = Resources.getResourceAsReader(resource);
		// 生成sqlSessionFactory工厂
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession session = sqlSessionFactory.openSession();
		UserMapper userMapper = session.getMapper(UserMapper.class);
		List<User> users = userMapper.getSelectUser();
		for (User user2 : users) {
			System.out.println(user2.toString());
		}
		session.close();

	}

}