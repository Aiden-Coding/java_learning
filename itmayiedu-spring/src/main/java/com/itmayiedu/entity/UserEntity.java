
package com.itmayiedu.entity;

public class UserEntity {

	private String userName;
	private Integer age = null;

	public UserEntity() {
		System.out.println("无惨构造函数.....");
	}

	public UserEntity(String userName, Integer age) {
		System.out.println("我是有参构造函数 userName:"+userName+",age:"+age);
		this.userName = userName;
		this.age = age;
	}

	public String getUserName() {

		return userName;
	}

	public void setUserName(String userName) {

		this.userName = userName;
	}

	public Integer getAge() {

		return age;
	}

	public void setAge(Integer age) {

		this.age = age;
	}

	@Override
	public String toString() {
		return "UserEntity [userName=" + userName + ", age=" + age + "]";
	}

}
