/**
 * 功能说明:
 * 功能作者:
 * 创建日期:
 * 版权归属:每特教育|蚂蚁课堂所有 www.itmayiedu.com
 */
package com.itmayiedu.producter;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 功能说明: <br>
 * 创建作者:每特教育-余胜军<br>
 * 创建时间:2018年9月25日 下午5:16:26<br>
 * 教育机构:每特教育|蚂蚁课堂<br>
 * 版权说明:上海每特教育科技有限公司版权所有<br>
 * 官方网站:www.itmayiedu.com|www.meitedu.com<br>
 * 联系方式:qq644064779<br>
 * 注意:本内容有每特教育学员共同研发,请尊重原创版权
 */
public class Consumer {
	/**
	 * mq通讯地址
	 */
	private final static String URL = "tcp://localhost:61616";
	/**
	 * 队列名称
	 */
	private final static String QUEUENAME = "my_queue";

	public static void main(String[] args) throws JMSException {
		// 1.创建ActiveMQFactory
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(URL);
		// 2.创建连接
		Connection cnnection = factory.createConnection();
		// 3.启动连接
		cnnection.start();
		// 4.创建Session 不开启事务,自动签收模式
		Session session = cnnection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		// 5.创建一个目标
		Queue queue = session.createQueue(QUEUENAME);
		// 6.创建生产者
		MessageConsumer createConsumer = session.createConsumer(queue);
		createConsumer.setMessageListener(new MessageListener() {

			public void onMessage(Message message) {
				try {
					TextMessage textMessage = (TextMessage) message;
					System.out.println("消费者消费消息:" + textMessage.getText());
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		});
		// 先不要关闭连接
	}
}
