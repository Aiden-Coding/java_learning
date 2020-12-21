
package com.itmayiedu.activemq.producer;

import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component

public class Producer {
	@Autowired
	private JmsMessagingTemplate jmsMessagingTemplate;
	@Autowired
	private Queue queue;

	@Scheduled(fixedDelay = 5000)
	public void send() {
		String result="springboot整合消息中间..." + System.currentTimeMillis();
		System.out.println(result);
		jmsMessagingTemplate.convertAndSend(queue,result );
	}
}
