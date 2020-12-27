/**
 * 功能说明:
 * 功能作者:
 * 创建日期:
 * 版权归属:每特教育|蚂蚁课堂所有 www.itmayiedu.com
 */
package com.itmayiedu.demo02;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 功能说明: <br>
 * 创建作者:每特教育-余胜军<br>
 * 创建时间:2018年9月25日 下午10:31:03<br>
 * 教育机构:每特教育|蚂蚁课堂<br>
 * 版权说明:上海每特教育科技有限公司版权所有<br>
 * 官方网站:www.itmayiedu.com|www.meitedu.com<br>
 * 联系方式:qq644064779<br>
 * 注意:本内容有每特教育学员共同研发,请尊重原创版权
 */
public class ProducerTest02 {
	// mq通讯地址
	private static String url = "tcp://127.0.0.1:61616";
	// 队列名称
	private static String topicName = "my_topic";

	public static void main(String[] args) throws JMSException {
		// 1.创建连接工厂 吗，密码采用默认密码admin 和admin
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(url);
		// 2.创建连接
		Connection connection = factory.createConnection();
		// 3.创建会话 参数1 设置是否需要以事务方式提交 参数2 消息方式 采用自动签收
		connection.start();// 启动连接
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		// 4.创建目标(主题)
		Topic topic = session.createTopic(topicName);
		// 5.创建生产者
		MessageProducer producer = session.createProducer(topic);
		for (int i = 1; i <= 10; i++) {
			// 6.创建 消息
			TextMessage textMessage = session.createTextMessage("消息内容i:" + i);
			// 7.发送消息
			producer.send(textMessage);

		}
		// 8.关闭连接
		connection.close();
		System.out.println("消息发送完毕!");
	}
}
