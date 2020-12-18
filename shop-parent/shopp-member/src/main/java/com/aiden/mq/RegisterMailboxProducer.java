
package com.aiden.mq;

import javax.jms.Destination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 *
 * @classDesc: 功能描述:(使用MQ调用消息服务,发送注册邮件)
 * @author: 蚂蚁课堂创始人-余胜军
 * @QQ: 644064779
 * @QQ粉丝群: 116295598
 * @createTime: 2017年10月22日 下午9:13:18
 * @version: v1.0
 * @copyright:每特学院(蚂蚁课堂)上海每特教育科技有限公司
 */
@Service("registerMailboxProducer")
public class RegisterMailboxProducer {

	@Autowired
	private JmsMessagingTemplate jmsMessagingTemplate;

	/**
	 *
	 * @methodDesc: 功能描述:(消息服务发送者)
	 * @param: @param
	 *             destination
	 * @param: @param
	 *             json
	 */
	public void sendMess(Destination destination, String json) {
		jmsMessagingTemplate.convertAndSend(destination, json);
	}
}
