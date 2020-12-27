/**
 * 功能说明:
 * 功能作者:
 * 创建日期:
 * 版权归属:每特教育|蚂蚁课堂所有 www.itmayiedu.com
 */
package com.itmayiedu.producter;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 功能说明: <br>
 * 创建作者:每特教育-余胜军<br>
 * 创建时间:2018年9月25日 下午2:35:50<br>
 * 教育机构:每特教育|蚂蚁课堂<br>
 * 版权说明:上海每特教育科技有限公司版权所有<br>
 * 官方网站:www.itmayiedu.com|www.meitedu.com<br>
 * 联系方式:qq644064779<br>
 * 注意:本内容有每特教育学员共同研发,请尊重原创版权
 */
public class ActiveMQReceiver {
	private static String userName = "admin";
	private static String password = "admin";
	private static String brokerURL = "tcp://127.0.0.1:61616";

	public static void main(String[] args) throws JMSException {
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(userName, password, brokerURL);
		// 1.创建连接
		Connection connection = connectionFactory.createConnection();
		connection.start();
		// 2.创建Session
		Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
		Queue createQueue = session.createQueue("my-queue");
		MessageConsumer consumer = session.createConsumer(createQueue);
		while (true) {
			TextMessage textMessage = (TextMessage) consumer.receive();
			if (textMessage != null) {
				System.out.println("收到消息：" + textMessage.getText());
				session.commit();
			} else {
				break;
			}

		}
	}

}
