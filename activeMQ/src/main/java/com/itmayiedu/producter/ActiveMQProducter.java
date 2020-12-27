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
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 功能说明: <br>
 * 创建作者:每特教育-余胜军<br>
 * 创建时间:2018年9月25日 下午2:20:21<br>
 * 教育机构:每特教育|蚂蚁课堂<br>
 * 版权说明:上海每特教育科技有限公司版权所有<br>
 * 官方网站:www.itmayiedu.com|www.meitedu.com<br>
 * 联系方式:qq644064779<br>
 * 注意:本内容有每特教育学员共同研发,请尊重原创版权
 */
public class ActiveMQProducter {
	private static String userName = "admin";
	private static String password = "admin";
	private static String brokerURL = "tcp://127.0.0.1:61616";

	public static void main(String[] args) throws JMSException {
		// 1.创建JMS 连接工厂
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(userName, password, brokerURL);
		// 2.创建 JMS 连接到Producer
		Connection connection = connectionFactory.createConnection();
		// 3.启动连接
		connection.start();
		// 4.创建Session会话
		Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
		// 5.创建消息队列
		Queue createQueue = session.createQueue("my-queue");
		// 6.创建生产者
		MessageProducer producer = session.createProducer(createQueue);
		// 7.设置队列设置不持久化
		producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		for (int i = 0; i < 5; i++) {
			sendMsg(session, producer, i);
			session.commit();
		}
		connection.close();
	}

	public static void sendMsg(Session session, MessageProducer producer, int i) {
		try {
			// 8.发送消息内容
			TextMessage message = session.createTextMessage("hello  ActiveMQ! i:" + i);
			producer.send(message);
		} catch (JMSException e) {

		}
	}

}
