package com.itmayiedu;

enum UserEnum {
	HTTP_200(200, "请求成功"),HTTP_500(500,"请求失败");
	private Integer code;
	private String name;

	UserEnum(Integer code, String name) {
		this.code = code;
		this.name = name;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

public class TestEnum {

	public static void main(String[] args) {
		System.out.println(UserEnum.HTTP_500.getCode());
	}

}
