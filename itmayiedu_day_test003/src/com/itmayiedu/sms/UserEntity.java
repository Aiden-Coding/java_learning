package com.itmayiedu.sms;

public class UserEntity implements Cloneable {

	private String userName;
	private java.util.Date date;
	Integer age;

	public UserEntity(String userName, java.util.Date date) {
		super();
		this.userName = userName;
		this.date = date;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public java.util.Date getDate() {
		return date;
	}

	public void setDate(java.util.Date date) {
		this.date = date;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		Object obj = super.clone();// 直接调用object对象克隆
		return obj;
	}
}
