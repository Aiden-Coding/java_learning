
package com.itmayiedu;

import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@SpringBootApplication
@Component
public class Consumer {
	private int count = 0;

	@JmsListener(destination = "${queue}")
	public void receive(TextMessage textMessage,Session session) {
		try {
			String text = textMessage.getText();

			System.out.println("消费端接受到生长者消息: " + text + " 第几次获取消息 count:" + (++count));
			int i = 1 / 0;
			//伪代码耗时15秒
//			Thread.sleep(15000l);
			String jmsMessageID = textMessage.getJMSMessageID();
			//网络延迟情况下，第二次消费过来，应该使用全局ID该消息是否被消费。
//			if(jmsMessageID==缓存里面){
//				textMessage.acknowledge();// 避免第三次重试。
//			}
		   // 消費成功
			//jmsMessageID==存放在緩存裡面
		

		} catch (Exception e) {
			// 在 catch 日志记录消息报文，可以采用补偿机制 （使用人工补偿），使用定时JOB健康检查脏数据。
//			session.recover();// 重试。
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(Consumer.class, args);
	}
}
