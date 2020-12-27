/**
 * 功能说明:
 * 功能作者:
 * 创建日期:
 * 版权归属:每特教育|蚂蚁课堂所有 www.itmayiedu.com
 */
package com.itmayiedu.consumer;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

/**
 * 功能说明: <br>
 * 创建作者:每特教育-余胜军<br>
 * 创建时间:2018年9月28日 下午9:36:40<br>
 * 教育机构:每特教育|蚂蚁课堂<br>
 * 版权说明:上海每特教育科技有限公司版权所有<br>
 * 官方网站:www.itmayiedu.com|www.meitedu.com<br>
 * 联系方式:qq644064779<br>
 * 注意:本内容有每特教育学员共同研发,请尊重原创版权
 */
@Component
public class P2PConsumer {
	@Autowired
	private JavaMailSender javaMailSender;

	// 幂等性
	@JmsListener(destination = "${my_queue}")
	public void receive(String msg) throws Exception {
		if (StringUtils.isEmpty(msg)) {
			return;
		}
		// 1.解析json
		JSONObject parseObject = JSONObject.parseObject(msg);
		String userName = parseObject.getString("userName");
		String email = parseObject.getString("email");
		sendSimpleMail(email, userName);
		System.out.println("采用点对点模式，消费者成功获取到生产者的消息,msg:" + msg);

	}

	public void sendSimpleMail(String eamil, String userName) throws Exception {
		SimpleMailMessage message = new SimpleMailMessage();
		// 邮件来自 自己发自己
		message.setFrom(eamil);
		// 发送给谁
		message.setTo(eamil);
		// 邮件标题
		message.setSubject("蚂蚁课堂|每特教育 新学员提醒");
		// 邮件内容
		message.setText("祝贺您,成为了我们" + userName + ",学员!");
		// 发送邮件
		javaMailSender.send(message);
		System.out.println("邮件发送完成," + JSONObject.toJSONString(message));
	}

}
