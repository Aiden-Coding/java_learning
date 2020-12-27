package com.itmayiedu.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

//邮件队列
@Component
@RabbitListener(queues = "fanout_eamil_queue")
public class FanoutEamilConsumer {
	@RabbitHandler
	public void process(String msg) throws Exception {
		System.out.println("邮件消费者获取生产者消息msg:" + msg);
	}
}
