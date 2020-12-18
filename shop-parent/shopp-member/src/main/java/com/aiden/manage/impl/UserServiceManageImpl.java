
package com.aiden.manage.impl;

import com.aiden.common.api.BaseApiService;
import com.aiden.common.date.DateUtils;
import com.aiden.common.md5.MD5Util;
import com.aiden.common.redis.BaseRedisService;
import com.aiden.common.token.TokenUtils;
import com.aiden.constants.Constants;
import com.aiden.constants.DBTableName;
import com.aiden.constants.MQInterfaceType;
import com.aiden.dao.UserDao;
import com.aiden.entity.UserEntity;
import com.aiden.manage.UserServiceManage;
import com.aiden.mq.roducer.RegisterMailboxProducer;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.jms.Destination;
import java.util.Map;

@Service
@Slf4j
public class UserServiceManageImpl extends BaseApiService implements UserServiceManage {
	@Autowired
	private UserDao userDao;
	@Autowired
	private RegisterMailboxProducer registerMailboxProducer;
	@Value("${messages.queue}")
	private String MESSAGES_QUEUE;
	@Autowired
	private TokenUtils tokenUtils;
	@Autowired
	private BaseRedisService baseRedisService;

	@Override
	public void regist(UserEntity userEntity) {
		userEntity.setCreated(DateUtils.getTimestamp());
		userEntity.setUpdated(DateUtils.getTimestamp());
		String phone = userEntity.getPhone();
		String password = userEntity.getPassword();
		userEntity.setPassword(md5PassSalt(phone, password));
		userDao.save(userEntity, DBTableName.TABLE_MB_USER);
		// 队列
		Destination activeMQQueue = new ActiveMQQueue(MESSAGES_QUEUE);
		// 组装报文格式
		String mailMessage = mailMessage(userEntity.getEmail(), userEntity.getUserName());
		log.info("###regist() 注册发送邮件报文mailMessage:{}", mailMessage);
		// mq
		registerMailboxProducer.send(activeMQQueue, mailMessage);
	}

	@Override
	public String md5PassSalt(String phone, String password) {
		String newPass = MD5Util.MD5(phone + password);
		return newPass;

	}

	private String mailMessage(String email, String userName) {
		JSONObject root = new JSONObject();
		JSONObject header = new JSONObject();
		header.put("interfaceType", MQInterfaceType.SMS_MAIL);
		JSONObject content = new JSONObject();
		content.put("mail", email);
		content.put("userName", userName);
		root.put("header", header);
		root.put("content", content);
		return root.toJSONString();
	}

	@Override
	public Map<String, Object> login(UserEntity userEntity) {
		// 往数据进行查找数据
		String phone = userEntity.getPhone();
		String password = userEntity.getPassword();
		String newPassWord = md5PassSalt(phone, password);
		UserEntity userPhoneAndPwd = userDao.getUserPhoneAndPwd(phone, newPassWord);
		if (userPhoneAndPwd == null) {
			return setResutError("账号或密码错误");
		}
		// 生成对应的token
		String token = tokenUtils.getToken();
		Long userId = userPhoneAndPwd.getId();
		// key为自定义令牌,用户的userId作作为value 存放在redis中
		baseRedisService.set(token, userId + "", Constants.USER_TOKEN_TERMVALIDITY);
		return setResutSuccessData(token);

	}

	@Override
	public Map<String, Object> getUser(String token) {
		// 从redis中查找到userid
		String userId = baseRedisService.get(token);
		if (StringUtils.isEmpty(userId)) {
			return setResutError("用户已经过期!");
		}
		Long newUserIdl = Long.parseLong(userId);
		UserEntity userInfo = userDao.getUserInfo(newUserIdl);
		userInfo.setPassword(null);
		return setResutSuccessData(userInfo);
	}

}
