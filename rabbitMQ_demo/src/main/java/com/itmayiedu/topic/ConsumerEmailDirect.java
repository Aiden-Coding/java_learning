package com.itmayiedu.topic;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.itmayiedu.utils.MQConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;

public class ConsumerEmailDirect {
	private static final String QUEUE_NAME = "consumer_topic_email";
	private static final String EXCHANGE_NAME = "topic_exchange";

	public static void main(String[] args) throws IOException, TimeoutException {
		System.out.println("�ʼ�����������");
		// 1.�����µ�����
		Connection connection = MQConnectionUtils.newConnection();
		// 2.����ͨ��
		Channel channel = connection.createChannel();
		// 3.�����߹�������
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "log.#");
		// 4.�����߰󶨽����� ����1 ���� ����2������ ����3 routingKey
		DefaultConsumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
				String msg = new String(body, "UTF-8");
				System.out.println("�����߻�ȡ��������Ϣ:" + msg);
			}
		};
		// 5.�����߼���������Ϣ
		channel.basicConsume(QUEUE_NAME, true, consumer);
	}

}
