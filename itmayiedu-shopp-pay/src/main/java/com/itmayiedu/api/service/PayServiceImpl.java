package com.itmayiedu.api.service;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.itmayiedu.base.BaseApiService;
import com.itmayiedu.base.ResponseBase;
import com.itmayiedu.constants.Constants;
import com.itmayiedu.dao.PaymentInfoDao;
import com.itmayiedu.manager.PayManager;
import com.itmayiedu.manager.impl.AliBaBaManagerImpl;
import com.itmayiedu.utils.TokenUtils;
import com.itmnayiedu.entity.PaymentInfo;

@RestController
public class PayServiceImpl extends BaseApiService implements PayService {
	@Autowired
	private PaymentInfoDao paymentInfoDao;
	@Autowired
	private AliBaBaManagerImpl aliBaBaManagerImpl;

	@Override
	public ResponseBase getPayToken(@RequestBody PaymentInfo paymentInfo) {
		// 1.插入参数提交信息
		Integer savePaymentType = paymentInfoDao.savePaymentType(paymentInfo);
		if (savePaymentType <= 0) {
			return setResultError("系统错误!");
		}
		// 2.生成对应token
		String payToken = TokenUtils.getPayToken();
		// 3.存放在redis中
		Integer payId = paymentInfo.getId();
		baseRedisService.setString(payToken, payId + "", Constants.PAY_TOKEN_MEMBER_TIME);
		// 4.返回token給消費者
		JSONObject result = new JSONObject();
		result.put("payToken", payToken);
		return setResultSuccess(result);
	}

	@Override
	public ResponseBase payInfo(@RequestParam("payToken") String payToken) {
		if (StringUtils.isEmpty(payToken)) {
			return setResultError("token不能为空!");
		}
		String payId = (String) baseRedisService.getString(payToken);
		if (StringUtils.isEmpty(payId)) {
			return setResultError("支付已经超时!");
		}
		PaymentInfo paymentInfo = paymentInfoDao.getPaymentInfo(Long.parseLong(payId));
		if (paymentInfo == null) {
			return setResultError("未找到交易类型.");
		}
		// 判断类型 调用 具体业务接口
		Long typeId = paymentInfo.getTypeId();
		PayManager payManager = null;
		// 调用支付接口
		if (typeId == 1) {
			payManager = aliBaBaManagerImpl;
		}
		try {
			String payInfo = payManager.payInfo(paymentInfo);
			JSONObject payInfoJSON = new JSONObject();
			payInfoJSON.put("payInfoHtml", payInfo);
			return setResultSuccess(payInfoJSON);
		} catch (AlipayApiException e) {
			return setResultError("支付错误!");
		}

	}

}
