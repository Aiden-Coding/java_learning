
package com.aiden.app.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.aiden.app.entity.UserEntity;

@Component
public class UserDao {

	public List<UserEntity> getAllUser() {
		List<UserEntity> listUser = new ArrayList<UserEntity>();
		for (int i = 0; i < 20; i++) {
			listUser.add(new UserEntity(i, "name:" + i));
		}
		return listUser;
	}

}
