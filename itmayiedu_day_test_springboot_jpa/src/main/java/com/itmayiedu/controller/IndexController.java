package com.itmayiedu.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itmayiedu.dao.UserDao;
import com.itmayiedu.entity.User;

@RestController
public class IndexController {
	@Autowired
	private UserDao userDao;

	@RequestMapping("/jpaFindUser")
	public Object jpaIndex(User user) {
		Optional<User> userOptional = userDao.findById(user.getId());
		User reusltUser = userOptional.get();
		return reusltUser == null ? "没有查询到数据" : reusltUser;
	}

}
