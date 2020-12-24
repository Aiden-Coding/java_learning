package com.itmayiedu.weixin;

import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.itmayiedu.entity.TextMessage;
import com.itmayiedu.weixin.utils.CheckUtil;
import com.itmayiedu.weixin.utils.HttpClientUtil;
import com.itmayiedu.weixin.utils.XmlUtils;
import com.thoughtworks.xstream.XStream;

import javassist.bytecode.StackMap.Writer;
import lombok.extern.slf4j.Slf4j;

/**
 * 微信事件通知
 * 
 * @author 余胜军
 *
 */

@RestController
@Slf4j
public class DispatCherServlet {

	/**
	 * 微信验证
	 * 
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @param echostr
	 * @return
	 */
	@RequestMapping(value = "/dispatCherServlet", method = RequestMethod.GET)
	public String getDispatCherServlet(String signature, String timestamp, String nonce, String echostr) {
		boolean checkSignature = CheckUtil.checkSignature(signature, timestamp, nonce);
		if (!checkSignature) {
			return null;
		}
		return echostr;
	}

	/**
	 * 功能说明:微信事件通知
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/dispatCherServlet", method = RequestMethod.POST)
	public void postdispatCherServlet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		Map<String, String> mapResult = XmlUtils.parseXml(request);
		if (mapResult == null) {
			return;
		}
		String msgType = mapResult.get("MsgType");
		PrintWriter out = response.getWriter();

		switch (msgType) {
		case "text":
			String content = mapResult.get("Content");
			String toUserName = mapResult.get("ToUserName");
			String fromUserName = mapResult.get("FromUserName");
			String textMessage = null;
			if (content.equals("蚂蚁课堂")) {
				// 判断蚂蚁课堂
				textMessage = setTextMessage("蚂蚁课堂 中国高端IT培训教学网站 www.itmayiedu.com", toUserName, fromUserName);
			} else if (content.equals("你知道余胜军吗")) {
				// 判断蚂蚁课堂
				textMessage = setTextMessage("知道啊,余胜军,出生于1997年10月17日 在2016年7月15日创办了蚂蚁课堂教学网站，现任上海每特教育科技有限公司CEO",
						toUserName, fromUserName);
			} else if (content.equals("蚂蚁课堂第一期就业薪资")) {
				// 判断蚂蚁课堂
				textMessage = setTextMessage("平均薪资15k,哈哈哈 我也想去学习...", toUserName, fromUserName);
			} else {
				// 调用第三方智能接口
				String resultStr = HttpClientUtil
						.doGet("http://api.qingyunke.com/api.php?key=free&appid=0&msg=" + content);
				JSONObject jsonObject = new JSONObject().parseObject(resultStr);
				Integer integer = jsonObject.getInteger("result");
				if (integer == null || integer != 0) {
					textMessage = setTextMessage("亲,系统出错啦!", toUserName, fromUserName);
				} else {
					String result = jsonObject.getString("content");
					textMessage = setTextMessage(result, toUserName, fromUserName);
				}

			}
			log.info("postdispatCherServlet() info:{}", textMessage);
			out.print(textMessage);
			break;

		default:
			break;
		}
		out.close();
	}

	public String setTextMessage(String content, String toUserName, String fromUserName) {
		TextMessage textMessage = new TextMessage();
		textMessage.setCreateTime(new Date().getTime());
		textMessage.setFromUserName(toUserName);
		textMessage.setToUserName(fromUserName);
		textMessage.setContent(content);
		textMessage.setMsgType("text");
		String messageToXml = XmlUtils.messageToXml(textMessage);
		return messageToXml;
	}

}
