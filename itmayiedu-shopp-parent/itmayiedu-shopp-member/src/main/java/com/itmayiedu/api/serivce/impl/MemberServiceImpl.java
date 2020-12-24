package com.itmayiedu.api.serivce.impl;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.itmayiedu.api.service.MemberService;
import com.itmayiedu.base.BaseApiService;
import com.itmayiedu.base.BaseRedisService;
import com.itmayiedu.base.ResponseBase;
import com.itmayiedu.constants.Constants;
import com.itmayiedu.dao.MemberDao;
import com.itmayiedu.entity.UserEntity;
import com.itmayiedu.mq.RegisterMailboxProducer;
import com.itmayiedu.utils.MD5Util;
import com.itmayiedu.utils.TokenUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class MemberServiceImpl extends BaseApiService implements MemberService {
	@Autowired
	private MemberDao memberDao;
	@Autowired
	private RegisterMailboxProducer registerMailboxProducer;
	@Value("${messages.queue}")
	private String MESSAGESQUEUE;
	@Autowired
	private BaseRedisService baseRedisService;

	@Override
	public ResponseBase findUserById(Long userId) {
		UserEntity user = memberDao.findByID(userId);
		if (user == null) {
			return setResultError("未查找到用户信息.");
		}
		return setResultSuccess(user);
	}

	@Override
	public ResponseBase regUser(@RequestBody UserEntity user) {
		// 参数验证
		String password = user.getPassword();
		if (StringUtils.isEmpty(password)) {
			return setResultError("密码不能为空.");
		}
		String newPassword = MD5Util.MD5(password);
		user.setPassword(newPassword);
		Integer result = memberDao.insertUser(user);
		if (result <= 0) {
			return setResultError("注册用户信息失败.");
		}
		// 采用异步方式发送消息
		String email = user.getEmail();
		String json = emailJson(email);
		log.info("####会员服务推送消息到消息服务平台####json:{}", json);
		sendMsg(json);
		return setResultSuccess("用户注册成功.");
	}

	private String emailJson(String email) {
		JSONObject rootJson = new JSONObject();
		JSONObject header = new JSONObject();
		header.put("interfaceType", Constants.MSG_EMAIL);
		JSONObject content = new JSONObject();
		content.put("email", email);
		rootJson.put("header", header);
		rootJson.put("content", content);
		return rootJson.toJSONString();
	}

	private void sendMsg(String json) {
		ActiveMQQueue activeMQQueue = new ActiveMQQueue(MESSAGESQUEUE);
		registerMailboxProducer.sendMsg(activeMQQueue, json);

	}

	@Override
	public ResponseBase login(@RequestBody UserEntity user) {
		String username = user.getUsername();
		if (StringUtils.isEmpty(username)) {
			return setResultError("用户名称不能为空!");
		}
		String password = user.getPassword();
		if (StringUtils.isEmpty(password)) {
			return setResultError("密码不能为空!");
		}
		String newPassWord = MD5Util.MD5(password);
		UserEntity userEntity = memberDao.login(username, newPassWord);
		if (userEntity == null) {
			return setResultError("账号或密码错误!");
		}
		// 生成token
		String token = TokenUtils.getToken();
		baseRedisService.setString(token, userEntity.getId()+"",null);
		JSONObject JSONObject = new JSONObject();
		JSONObject.put("token", token);
		return setResultSuccess(JSONObject);
	}

	@Override
	public ResponseBase finTokenByUser(String token) {
		if (StringUtils.isEmpty(token)) {
			return setResultError("token不能为空.");
		}
		String userId = baseRedisService.getString(token);
		if(StringUtils.isEmpty(userId)){
			return setResultError("未查询到用户信息");
		}
		Long userIdl=Long.parseLong(userId);
		UserEntity userEntity = memberDao.findByID(userIdl);
		if (userEntity == null) {
			return setResultError("未查询到用户信息");
		}
		userEntity.setPassword(null);
		return setResultSuccess(userEntity);
	}
}
