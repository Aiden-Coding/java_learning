
package com.itmayiedu.activemq.test01;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Consumer001 {

	public static void main(String[] args) throws JMSException {
		// 获取mq连接工程
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,
				ActiveMQConnection.DEFAULT_PASSWORD, "tcp://127.0.0.1:61616");
		// 创建连接
		Connection createConnection = connectionFactory.createConnection();
		// 启动连接
		createConnection.start();
		// 创建会话工厂
		Session session = createConnection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
		// 创建队列
		Destination destination = session.createQueue("itmayiedu_queue");
		MessageConsumer createConsumer = session.createConsumer(destination);
		while (true) {
			// 监听消息
			TextMessage textMessage = (TextMessage) createConsumer.receive();
			if (textMessage != null) {
				String text = textMessage.getText();
				System.out.println("消费者 获取到消息:  text:" + text);
				// 手动签收消息
				// textMessage.acknowledge();
				session.commit();
			} else {
				break;
			}

		}

		System.out.println("消费者消费消息完毕!!!");
	}
}
