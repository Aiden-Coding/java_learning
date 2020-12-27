package com.itmayiedu.fanout;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.itmayiedu.utils.MQConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;

public class ConsumerSMSFanout {
	private static final String QUEUE_NAME = "ConsumerFanout_sms";
	private static final String EXCHANGE_NAME = "fanout_exchange";

	public static void main(String[] args) throws IOException, TimeoutException {
		System.out.println("��������������");
		// 1.�����µ�����
		Connection connection = MQConnectionUtils.newConnection();
		// 2.����ͨ��
		Channel channel = connection.createChannel();
		// 3.�����߹�������
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		// 4.�����߰󶨽����� ����1 ���� ����2������ ����3 routingKey
		channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "");
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
