package com.itmayiedu.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class UserEntity {
	// @Getter
	// @Setter
	private String userName;
	// @Getter
	// @Setter
	private Integer age;

	@Override
	public String toString() {
		return "UserEntity [userName=" + userName + ", age=" + age + "]";
	}

	public static void main(String[] args) {
		UserEntity userEntity = new UserEntity();
		userEntity.setUserName("zhangsan");
		userEntity.setAge(20);
		System.out.println(userEntity.toString());
		log.info("####我是日志##########");
	}

}
