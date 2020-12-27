package com.itmayiedu.rabbitmq;

import java.util.Map;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.itmayiedu.rabbitmq.utils.HttpClientUtils;
import com.rabbitmq.client.Channel;

//���ŶԽ�
@Component
public class DeadConsumer {

	@RabbitListener(queues = "dead_queue")
	public void process(Message message, @Headers Map<String, Object> headers, Channel channel) throws Exception {
		String messageId = message.getMessageProperties().getMessageId();
		String msg = new String(message.getBody(), "UTF-8");
		System.out.println("�����ʼ������߻�ȡ��������Ϣmsg:" + msg + ",��Ϣid:" + messageId);
		channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);

	}

}
