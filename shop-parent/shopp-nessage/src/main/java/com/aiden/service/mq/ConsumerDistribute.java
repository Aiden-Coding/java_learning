
package com.aiden.service.mq;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.aiden.constants.Constants;
import com.aiden.service.MessageAdapter;
import com.aiden.service.SMSMailboxService;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * @classDesc: 功能描述:(mq分发)
 * @author: 蚂蚁课堂创始人-余胜军
 * @QQ: 644064779
 * @QQ粉丝群: 116295598
 * @createTime: 2017年10月22日 下午9:00:45
 * @version: v1.0
 * @copyright:每特学院(蚂蚁课堂)上海每特教育科技有限公司
 */
@Slf4j
@Component
public class ConsumerDistribute {
	@Autowired
	private SMSMailboxService smsMailboxService;

	/**
	 *
	 *
	 * @methodDesc: 功能描述:(接受生产者消息)
	 * @param: 报文json
	 */
	@SuppressWarnings("static-access")
	@JmsListener(destination = "messages_queue")
	public void distribute(String json) {
		if (StringUtils.isEmpty(json)) {
			return;
		}
		log.info("###接受到消息内容json:{}", json);
		JSONObject root = new JSONObject().parseObject(json);
		JSONObject header = root.getJSONObject("header");
		String interfaceType = header.getString("interfaceType");
		if (StringUtils.isEmpty(interfaceType)) {
			return;
		}
		MessageAdapter messageAdapter = null;
		switch (interfaceType) {
		// 发送邮箱
		case Constants.SMS_MAIL:
			messageAdapter = smsMailboxService;
			break;
		default:
			break;
		}
		// 执行消息服务
		JSONObject content = root.getJSONObject("content");

		messageAdapter.distribute(content);
	}

}
