package com.itmayiedu.api.service;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itmayiedu.base.ResponseBase;
import com.itmnayiedu.entity.PaymentInfo;

@RequestMapping("/pay")
public interface PayService {
	// 其他服务调用支付接口，先生成token.
	@RequestMapping("/getPayToken")
	public ResponseBase getPayToken(@RequestBody PaymentInfo paymentInfo);

	@RequestMapping("/payInfo")
	public ResponseBase payInfo(@RequestParam("payToken") String payToken);
}
