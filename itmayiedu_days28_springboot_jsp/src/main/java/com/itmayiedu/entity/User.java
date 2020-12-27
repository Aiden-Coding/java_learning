package com.itmayiedu.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j // 等同于
@Getter
@Setter
public class User {

	private String name;
	private Integer age;

	// lombok 底层使用字节码技术 ASM 修改字节码文件,生成get和set方法
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "userName:" + name + "age:" + age;
	}

	// 需要生成get和set方法 最终编译的时候还是会生产get和set方法
	// lombok 一定要在开发工具安装 在编译的时候修改字节码文件（底层使用字节码技术），线上环境使用编译好的文件。
	public static void main(String[] args) {
		User user = new User();
		user.setName("yushengjun");
		user.setAge(20);
		log.info(user.toString());
		// lombok 减少重复代码
	}

}
