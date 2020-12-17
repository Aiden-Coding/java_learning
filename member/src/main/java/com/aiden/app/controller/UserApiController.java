
package com.aiden.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aiden.app.entity.UserEntity;
import com.aiden.app.service.UserService;

@RestController
public class UserApiController {
	@Autowired
	private UserService userService;

	@RequestMapping("/getUser")
	public UserEntity getUser(Integer userId) {
		return userService.getUser(userId);
	}

}
