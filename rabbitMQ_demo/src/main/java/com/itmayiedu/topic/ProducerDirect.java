package com.itmayiedu.topic;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.itmayiedu.utils.MQConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class ProducerDirect {
	private static final String EXCHANGE_NAME = "topic_exchange";

	public static void main(String[] args) throws IOException, TimeoutException {
		// 1.�����µ�����
		Connection connection = MQConnectionUtils.newConnection();
		// 2.����ͨ��
		Channel channel = connection.createChannel();
		// 3.�󶨵Ľ����� ����1���������� ����2 exchange����
		channel.exchangeDeclare(EXCHANGE_NAME, "topic");
		String routingKey = "log.info.error";
		String msg = "topic_exchange_msg" + routingKey;
		// 4.������Ϣ
		channel.basicPublish(EXCHANGE_NAME, routingKey, null, msg.getBytes());
		System.out.println("�����߷���msg��" + msg);
		// // 5.�ر�ͨ��������
		channel.close();
		connection.close();
		// ע�⣺�������û�а󶨽������Ͷ��У�����Ϣ�ᶪʧ

	}

}
