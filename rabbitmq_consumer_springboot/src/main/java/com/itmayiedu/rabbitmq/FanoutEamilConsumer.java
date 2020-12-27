package com.itmayiedu.rabbitmq;

import java.util.Map;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.itmayiedu.rabbitmq.utils.HttpClientUtils;
import com.rabbitmq.client.Channel;

//邮件队列
@Component
public class FanoutEamilConsumer {
	// @RabbitListener(queues = "fanout_email_queue")
	// public void process(String msg) throws Exception {
	// System.out.println("邮件消费者获取生产者消息msg:" + msg);
	// JSONObject jsonObject = JSONObject.parseObject(msg);
	// // 获取email参数
	// String email = jsonObject.getString("email");
	// // 请求地址
	// String emailUrl = "http://127.0.0.1:8083/sendEmail?email=" + email;
	// JSONObject result = HttpClientUtils.httpGet(emailUrl);
	// if (result == null) {
	// // 因为网络原因,造成无法访问,继续重试
	// throw new Exception("调用接口失败!");
	// }
	// System.out.println("执行结束....");
	//
	// }

	// @RabbitListener(queues = "fanout_email_queue")
	// public void process(Message message) throws Exception {
	// // 获取消息Id
	// String messageId = message.getMessageProperties().getMessageId();
	// String msg = new String(message.getBody(), "UTF-8");
	// System.out.println("邮件消费者获取生产者消息" + "messageId:" + messageId + ",消息内容:" +
	// msg);
	// JSONObject jsonObject = JSONObject.parseObject(msg);
	// // 获取email参数
	// String email = jsonObject.getString("email");
	// // 请求地址
	// String emailUrl = "http://127.0.0.1:8083/sendEmail?email=" + email;
	// JSONObject result = HttpClientUtils.httpGet(emailUrl);
	// if (result == null) {
	// // 因为网络原因,造成无法访问,继续重试
	// throw new Exception("调用接口失败!");
	// }
	// System.out.println("执行结束....");
	// }

	@RabbitListener(queues = "fanout_email_queue")
	public void process(Message message, @Headers Map<String, Object> headers, Channel channel) throws Exception {
		// 获取消息Id
		String messageId = message.getMessageProperties().getMessageId();
		String msg = new String(message.getBody(), "UTF-8");
		System.out.println("邮件消费者获取生产者消息" + "messageId:" + messageId + ",消息内容:" + msg);
		JSONObject jsonObject = JSONObject.parseObject(msg);
		// 获取email参数
		String email = jsonObject.getString("email");
		// 请求地址
		String emailUrl = "http://127.0.0.1:8083/sendEmail?email=" + email;
		JSONObject result = HttpClientUtils.httpGet(emailUrl);
		if (result == null) {
			// 因为网络原因,造成无法访问,继续重试
			throw new Exception("调用接口失败!");
		}
		// // 手动ack
		Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
		// 手动签收
		channel.basicAck(deliveryTag, false);
		System.out.println("执行结束....");
	}
}
