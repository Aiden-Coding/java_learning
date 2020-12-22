package com.itmayiedu.service;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.itmayiedu.base.BaseController;
import com.itmayiedu.base.BaseRedisService;
import com.itmayiedu.base.ResponseBase;
import com.itmayiedu.dao.UserDao;
import com.itmayiedu.entity.UserEntity;
import com.itmayiedu.mq.RegisterMailboxProducer;
import com.itmayiedu.utils.MD5Util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class MemberServiceImpl extends BaseController implements MemberService {
	@Autowired
	private BaseRedisService baseRedisService;
	@Autowired
	private UserDao userDao;
	@Autowired
	private RegisterMailboxProducer registerMailboxProducer;
	@Value("${messages.queue}")
	private String MESSAGESQUEUE;

	@Override
	public ResponseBase findUser(Long userId) {
		UserEntity userEntity = userDao.findByID(userId);

		if (userEntity == null) {
			return setResultError("为查找到该用户信息");
		}
		return setResultSuccess(userEntity);
	}

	@Override
	public ResponseBase register(@RequestBody UserEntity user) {
		String passWord = user.getPassword();
		String newPassWord = MD5Util.MD5(passWord);
		user.setPassword(newPassWord);
		Integer insertUser = userDao.insertUser(user);
		if (insertUser <= 0) {
			return setResultError("注册失败!");
		}
		// 采用MQ异步发送邮件
		String email = user.getEmail();
		String messAageJson = message(email);
		log.info("######email:{},messAageJson:{}",email,messAageJson);
		sendMsg(messAageJson);
		return setResultSuccess();
	}

	private String message(String mail) {
		JSONObject root = new JSONObject();
		JSONObject header = new JSONObject();
		header.put("interfaceType", "sms_mail");
		JSONObject content = new JSONObject();
		content.put("mail", mail);
		root.put("header", header);
		root.put("content", content);
		return root.toJSONString();
	}

	private void sendMsg(String json) {
		ActiveMQQueue activeMQQueue = new ActiveMQQueue(MESSAGESQUEUE);
		registerMailboxProducer.sendMsg(activeMQQueue, json);
	}

}
