package com.itmayiedu.controller;

import com.alibaba.fastjson.JSONObject;
import com.itmayiedu.entity.PaymentInfo;
import com.itmayiedu.feign.PaymentInfoFeign;
import com.itmayiedu.service.PayService;
import com.itmayiedu.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@Controller
@RequestMapping("/pay")
public class PayController {
	@Autowired
	private PaymentInfoFeign paymentInfoFeign;
	@Autowired
	private PayService payService;

	@RequestMapping("/payIndex")
	public void payIndex(String token, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		// 使用token 查找对应支付信息
		try {
			Map<String, Object> payInfoToken = paymentInfoFeign.getPayInfoToken(token);
			Map<String, Object> resultMap = (Map<String, Object>) ResultUtils.getResultMap(payInfoToken);
			String json = new JSONObject().toJSONString(resultMap);
			PaymentInfo paymentInfo = new JSONObject().parseObject(json, PaymentInfo.class);
			if (paymentInfo == null) {
				// 没有找到该接口
				out.println("没有找到该接口");
				return;
			}
			String html = payService.pay(paymentInfo);
			out.println(html);
		} catch (Exception e) {
			// TODO: handle exception
			out.println("系统错误");
		}finally {
			out.close();
		}


	}

}
