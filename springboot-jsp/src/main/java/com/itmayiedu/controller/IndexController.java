
package com.itmayiedu.controller;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itmayiedu.entity.User;
import com.itmayiedu.mapper.UserMapper;
import com.itmayiedu.test01.dao.UserMapperTest01;
import com.itmayiedu.test01.service.UserServiceTest01;
import com.itmayiedu.test02.dao.UserMapperTest02;

@Controller
public class IndexController {
	private static Logger log = Logger.getLogger(IndexController.class);
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserMapperTest01 userMapperTest01;
	@Autowired
	private UserMapperTest02 userMapperTest02;
	@Autowired
	private UserServiceTest01 userServiceTest01;
	@Value("${itmayiedu}")
	private String itmayiedu;

	@RequestMapping("/index")
	public String index() {
		return "index";
	}

	@ResponseBody
	@RequestMapping("/findByName")
	public User findByName(String name) {
		log.info("####findByName()####name:" + name);
		return userMapper.findByName(name);
	}

	@ResponseBody
	@RequestMapping("/insert")
	public String insert(String name, Integer age) {
		userMapper.insert(name, age);
		return "success";
	}

	@ResponseBody
	@RequestMapping("/insertTest001")
	public String insertTest001(String name, Integer age) {
		userServiceTest01.insertTest001(name, age);
		return "success";
	}

	@ResponseBody
	@RequestMapping("/findByNameTest01")
	public User findByNameTest01(String name) {
		log.info("####findByName()####name:" + name);
		return userMapperTest01.findByName(name);
	}

	@ResponseBody
	@RequestMapping("/insertTest002")
	public String insertTest002(String name, Integer age) {
		userMapperTest02.insert(name, age);
		return "success";
	}

	@ResponseBody
	@RequestMapping("/sendMsg")
	public String sendMsg() {
		System.out.println("###sendMsg###1");
		userServiceTest01.sendSms();
		System.out.println("###sendMsg###2");
		return "success";
	}
	@ResponseBody
	@RequestMapping("/getValue")
	public String getValue() {
		return itmayiedu;
	}
}
