package com.itmayiedu.api.serivce.impl;

import java.util.Date;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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

import feign.Param;
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
		user.setCreated(new Date());
		user.setUpdated(new Date());
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
		// 1.验证参数
		String username = user.getUsername();
		if (StringUtils.isEmpty(username)) {
			return setResultError("用戶名称不能为空!");
		}
		String password = user.getPassword();
		if (StringUtils.isEmpty(password)) {
			return setResultError("密码不能为空!");
		}
		// 2.数据库查找账号密码是否正确
		String newPassWrod = MD5Util.MD5(password);
		UserEntity userEntity = memberDao.login(username, newPassWrod);
		if (userEntity == null) {
			return setResultError("账号或者密码不能正确");
		}
		Integer userId = userEntity.getId();
		JSONObject jsonObject = setUserRedis(userId);
		return setResultSuccess(jsonObject);
	}

	private JSONObject setUserRedis(Integer userId) {
		// 3.如果账号密码正确，对应生成token
		String memberToken = TokenUtils.getMemberToken();
		// 4.存放在redis中，key为token value 为 userid
		log.info("####用户信息token存放在redis中... key为:{},value", memberToken, userId);
		baseRedisService.setString(memberToken, userId + "", Constants.TOKEN_MEMBER_TIME);
		// 5.直接返回token
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("memberToken", memberToken);
		return jsonObject;
	}

	@Override
	public ResponseBase findByTokenUser(@RequestParam("token") String token) {
		// 1.验证参数
		if (StringUtils.isEmpty(token)) {
			return setResultError("token不能为空!");
		}
		// 2.从redis中 使用token 查找对应 userid
		String strUserId = (String) baseRedisService.getString(token);
		if (StringUtils.isEmpty(strUserId)) {
			return setResultError("token无效或者已经过期!");
		}
		// 3.使用userid数据库查询用户信息返回给客户端
		Long userId = Long.parseLong(strUserId);
		UserEntity userEntity = memberDao.findByID(userId);
		if (userEntity == null) {
			return setResultError("为查找到该用户信息");
		}
		userEntity.setPassword(null);
		return setResultSuccess(userEntity);
	}

	@Override
	public ResponseBase findByOpenIdUser(@RequestParam("openId") String openId) {
		if (StringUtils.isEmpty(openId)) {
			return setResultError("openId不能为空!");
		}
		UserEntity userEntity = memberDao.findByOpenIdUser(openId);
		if (userEntity == null) {
			return setResultErrorCode(Constants.HTTP_RES_CODE_201, "用户未授权QQ登录.");
		}
		Integer userId = userEntity.getId();
		JSONObject jsonObject = setUserRedis(userId);
		return setResultSuccess(jsonObject);
	}

	@Override
	public ResponseBase qqLoginOpenId(@RequestBody UserEntity user) {
		// 1.验证参数
		String username = user.getUsername();
		if (StringUtils.isEmpty(username)) {
			return setResultError("用戶名称不能为空!");
		}
		String password = user.getPassword();
		if (StringUtils.isEmpty(password)) {
			return setResultError("密码不能为空!");
		}
		// 2.数据库查找账号密码是否正确
		String newPassWrod = MD5Util.MD5(password);
		UserEntity userEntity = memberDao.login(username, newPassWrod);
		if (userEntity == null) {
			return setResultError("账号或者密码不能正确");
		}
		// 3. 关联userid
		String openid = user.getOpenid();
		Integer userId = userEntity.getId();
		memberDao.updateUser(openid, userId);
		JSONObject jsonObject = setUserRedis(userId);
		return setResultSuccess(jsonObject);
	}
}
