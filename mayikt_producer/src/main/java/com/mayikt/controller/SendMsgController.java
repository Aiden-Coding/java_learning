package com.mayikt.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mayikt.stream.SendMessageInterface;

// 发送消息接口
@RestController
public class SendMsgController {
	@Autowired
	private SendMessageInterface sendMessageInterface;

	@RequestMapping("/sendMsg")
	public String sendMsg() {
		String msg = "1111";
		// System.out.println("生产者发送内容msg:" + msg);
		Message build = MessageBuilder.withPayload(msg.getBytes()).build();
		sendMessageInterface.sendMsg().send(build);
		return "success";
	}

}
