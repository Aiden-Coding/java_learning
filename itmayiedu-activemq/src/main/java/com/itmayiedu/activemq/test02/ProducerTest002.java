
package com.itmayiedu.activemq.test02;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class ProducerTest002 {

	public static void main(String[] args) throws JMSException {
	
		// 获取mq连接工程
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,
				ActiveMQConnection.DEFAULT_PASSWORD, "tcp://127.0.0.1:61616");
		// 创建连接
		Connection createConnection = connectionFactory.createConnection();
		// 启动连接
		createConnection.start();
		// 创建会话工厂
		Session session = createConnection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
		// Destination destination = session.createTopic("itmayiedu-topic");
		MessageProducer producer = session.createProducer(null);
		// 不持久化
		producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		for (int i = 1; i <= 5; i++) {
			System.out.println("我是生产者: " + i);
			sendMsg(session, producer, "我是生产者: " + i);

		}
		System.out.println("生产者 发送消息完毕!!!");
	}

	public static void sendMsg(Session session, MessageProducer producer, String i) throws JMSException {
		TextMessage textMessage = session.createTextMessage("hello activemq " + i);
		Destination destination = session.createTopic("itmayiedu-topic");
		producer.send(destination, textMessage);
	}

}
