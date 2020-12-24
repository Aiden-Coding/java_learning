package com.itmayiedu.manager;

import com.alipay.api.AlipayApiException;
import com.itmnayiedu.entity.PaymentInfo;

public interface PayManager {

	
	 public String payInfo(	PaymentInfo paymentInfo)  throws AlipayApiException;
	
}
