
package com.aiden.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aiden.app.dao.UserDao;
import com.aiden.app.entity.UserEntity;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	public List<UserEntity> getAllUser() {
		  return userDao.getAllUser();
	}
	public UserEntity getUser(Integer id){
		List<UserEntity> allUser = getAllUser();
		for (UserEntity userEntity : allUser) {
			if(userEntity.getId()==id){
			   return userEntity;
			}
		}
		return null;
	}

}
