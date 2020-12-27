package com.itmayiedu.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

//�ʼ�����
@Component
@RabbitListener(queues = "fanout_sms_queue")
public class FanoutSmsConsumer {
	@RabbitHandler
	public void process(String msg) {
		System.out.println("���������߻�ȡ��������Ϣmsg:" + msg);
	}
}
