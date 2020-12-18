
package com.aiden.api.service.impl;

import com.aiden.api.service.MbUserService;
import com.aiden.common.base.api.BaseApiService;
import com.aiden.common.date.DateUtils;
import com.aiden.common.md5.MD5Util;
import com.aiden.constants.ConstantsTables;
import com.aiden.dao.MemberDao;
import com.aiden.entity.MbUser;
import com.aiden.mq.roducer.RegisterMailboxProducer;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Destination;
import java.util.Map;


@Slf4j
@RestController
@RequestMapping("/member/user")
public class MbUserServiceImpl extends BaseApiService implements MbUserService {
	@Autowired
	private MemberDao memberDao;
	@Autowired
	private RegisterMailboxProducer registerMailboxProducer;
	@Value("${messages.queue}")
	private String MESSAGES_QUEUE;

	@Override
	public Map<String, Object> register(@RequestBody MbUser mbUser) {
		try {
			// ① 使用手机号码查找该用户是否存在
			MbUser resultMbUser = memberDao.getMbUser(mbUser);
			if (resultMbUser != null) {
				return setResultError("该手机号码已经被注册");
			}
			// 密码加密并且加盐
			String userName = mbUser.getUsername();
			String password = mbUser.getPassword();
			String newPassWord = MD5Util.MD5(userName + password);
			mbUser.setPassword(newPassWord);
			// ② 如果不存在,则可以注册
			mbUser.setCreated(DateUtils.getTimestamp());
			mbUser.setUpdated(DateUtils.getTimestamp());
			memberDao.save(mbUser, ConstantsTables.TABLE_MB_USER);
			// ③ 注册成功后,调用消息服务接口,推送一条邮箱注册成功通知。
			String email = mbUser.getEmail();
			String message = message(email);
			log.info("###register()调用消息中间 发送注册成功邮箱通知结果 message:{}",message);
			sendMess(message);
		} catch (Exception e) {
			log.error("####register()####,ERROR:", e);
			return setResultError("系统错误!");
		}
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

	private void sendMess(String json) {
		Destination activeMQQueue = new ActiveMQQueue(MESSAGES_QUEUE);
		registerMailboxProducer.sendMess(activeMQQueue, json);
	}

}
