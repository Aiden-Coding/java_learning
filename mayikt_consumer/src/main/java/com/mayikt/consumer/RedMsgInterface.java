package com.mayikt.consumer;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface RedMsgInterface {

	// 从管道中获取消息
	@Input("my_msg")
	SubscribableChannel redMsg();
}
