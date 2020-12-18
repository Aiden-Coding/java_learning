
package com.aiden.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SMSMailboxService implements MessageAdapter {
	@Autowired
	private JavaMailSender mailSender; // 自动注入的Bean
	@Value("${spring.mail.username}")
	private String sender; // 读取配置文件中的参数

	@Override
	public void distribute(JSONObject jsonObject) {
		String mail = jsonObject.getString("mail");
		// 开始发送邮件
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(mail);
		message.setTo(sender); // 自己给自己发送邮件
		message.setSubject("恭喜您成为微信商城的信用...");
		message.setText("恭喜您今天成为了微信商城的用户,谢谢您的关注!");
		log.info("###发送短信邮箱 mail:{}", mail);
		mailSender.send(message);
	}

}
