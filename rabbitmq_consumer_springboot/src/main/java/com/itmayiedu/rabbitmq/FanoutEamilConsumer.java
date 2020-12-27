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

//�ʼ�����
@Component
public class FanoutEamilConsumer {
	// @RabbitListener(queues = "fanout_email_queue")
	// public void process(String msg) throws Exception {
	// System.out.println("�ʼ������߻�ȡ��������Ϣmsg:" + msg);
	// JSONObject jsonObject = JSONObject.parseObject(msg);
	// // ��ȡemail����
	// String email = jsonObject.getString("email");
	// // �����ַ
	// String emailUrl = "http://127.0.0.1:8083/sendEmail?email=" + email;
	// JSONObject result = HttpClientUtils.httpGet(emailUrl);
	// if (result == null) {
	// // ��Ϊ����ԭ��,����޷�����,��������
	// throw new Exception("���ýӿ�ʧ��!");
	// }
	// System.out.println("ִ�н���....");
	//
	// }

	// @RabbitListener(queues = "fanout_email_queue")
	// public void process(Message message) throws Exception {
	// // ��ȡ��ϢId
	// String messageId = message.getMessageProperties().getMessageId();
	// String msg = new String(message.getBody(), "UTF-8");
	// System.out.println("�ʼ������߻�ȡ��������Ϣ" + "messageId:" + messageId + ",��Ϣ����:" +
	// msg);
	// JSONObject jsonObject = JSONObject.parseObject(msg);
	// // ��ȡemail����
	// String email = jsonObject.getString("email");
	// // �����ַ
	// String emailUrl = "http://127.0.0.1:8083/sendEmail?email=" + email;
	// JSONObject result = HttpClientUtils.httpGet(emailUrl);
	// if (result == null) {
	// // ��Ϊ����ԭ��,����޷�����,��������
	// throw new Exception("���ýӿ�ʧ��!");
	// }
	// System.out.println("ִ�н���....");
	// }

	@RabbitListener(queues = "fanout_email_queue")
	public void process(Message message, @Headers Map<String, Object> headers, Channel channel) throws Exception {
		// ��ȡ��ϢId
		String messageId = message.getMessageProperties().getMessageId();
		String msg = new String(message.getBody(), "UTF-8");
		System.out.println("�ʼ������߻�ȡ��������Ϣ" + "messageId:" + messageId + ",��Ϣ����:" + msg);
		JSONObject jsonObject = JSONObject.parseObject(msg);
		// ��ȡemail����
		String email = jsonObject.getString("email");
		// �����ַ
		String emailUrl = "http://127.0.0.1:8083/sendEmail?email=" + email;
		JSONObject result = HttpClientUtils.httpGet(emailUrl);
		if (result == null) {
			// ��Ϊ����ԭ��,����޷�����,��������
			throw new Exception("���ýӿ�ʧ��!");
		}
		// // �ֶ�ack
		Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
		// �ֶ�ǩ��
		channel.basicAck(deliveryTag, false);
		System.out.println("ִ�н���....");
	}
}
