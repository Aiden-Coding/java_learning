package com.itmayiedu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itmayiedu.base.ResponseBase;
import com.itmayiedu.constants.Constants;
import com.itmayiedu.fegin.PayServiceFegin;
import com.itmnayiedu.entity.PaymentInfo;

@Controller
public class PayController {
	@Autowired
	private PayServiceFegin payServiceFegin;

	@RequestMapping("/pay")
	public void pay(String payToken, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html;charset=utf-8");
		ResponseBase payInfo = payServiceFegin.payInfo(payToken);
		PrintWriter out = resp.getWriter();
		if (!payInfo.getRtnCode().equals(Constants.HTTP_RES_CODE_200)) {
			out.println("ERROR");
			return;
		}
		LinkedHashMap mapPayInfo = (LinkedHashMap) payInfo.getData();
		String payInfoHtml = (String) mapPayInfo.get("payInfoHtml");
		out.println(payInfoHtml);
		out.close();
	}

}
