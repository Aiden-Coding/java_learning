package com.itmayiedu.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.itmayiedu.adapt.PayAdaptService;
import com.itmayiedu.entity.PaymentInfo;
import com.itmayiedu.entity.PaymentType;
import com.itmayiedu.feign.PaymentTypeFeign;
import com.itmayiedu.service.PayService;
import com.itmayiedu.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PayServiceImpl implements PayService {
	@Autowired
	private PaymentTypeFeign paymentTypeFeign;
	@Autowired
	private YinLianPayService yinLianPayService;

	@Override
	public String pay(PaymentInfo paymentInfo) {
		// 判断支付类型
		Long typeId = paymentInfo.getTypeId();
		Map<String, Object> getPaymentTypeMap = paymentTypeFeign.getPaymentType(typeId);
		Map<String, Object> resultMap = (Map<String, Object>) ResultUtils.getResultMap(getPaymentTypeMap);
		String json = new JSONObject().toJSONString(resultMap);
		PaymentType paymentType = new JSONObject().parseObject(json, PaymentType.class);
		if (paymentType == null) {
			return null;
		}
		String typeName=paymentType.getTypeName();
		PayAdaptService payAdaptService=null;
		switch (typeName) {
		case "yinlianPay":
			payAdaptService=yinLianPayService;
			break;

		default:
			break;
		}

		return payAdaptService.pay(paymentInfo, paymentType);
	}

}
