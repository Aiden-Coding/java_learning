package com.itmayiedu.service.impl;

import com.itmayiedu.common.api.BaseApiService;
import com.itmayiedu.common.redis.BaseRedisService;
import com.itmayiedu.common.token.TokenUtils;
import com.itmayiedu.dao.PaymentInfoDao;
import com.itmayiedu.entity.PaymentInfo;
import com.itmayiedu.service.PaymentInfoService;
import com.itmayiedu.utils.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/pay")
public class PaymentInfoServiceImpl extends BaseApiService implements PaymentInfoService {

	@Autowired
	private PaymentInfoDao paymentInfoDao;
	@Autowired
	private TokenUtils tokenUtils;
	@Autowired
	private BaseRedisService baseRedisService;

	@RequestMapping("/addPayInfoToken")
	public Map<String, Object> addPayInfoToken(@RequestBody PaymentInfo paymentInfo) {
		// ①添加paymentInfo
		paymentInfo.setCreated(DateUtils.getTimestamp());
		paymentInfo.setUpdated(DateUtils.getTimestamp());
		paymentInfoDao.savePaymentType(paymentInfo);
		Long id = paymentInfo.getId();
		if (id == null) {
			return setResutError("系统错误!");
		}
		// ②生成对应token
		String payToken = tokenUtils.getPayToken();
		// ③存放在redis中 key token value paymentInfo.id
		baseRedisService.setString(payToken, id + "");
		return setResutSuccessData(payToken);
	}

	@RequestMapping("/getPayInfoToken")
	public Map<String, Object> getPayInfoToken(@RequestParam("token") String token) {
		// 判断token是否为空
		if (StringUtils.isEmpty(token)) {
			return setResutError("token不能为空!");
		}
		// 使用token去redis查找对应支付id
		String id = baseRedisService.get(token);
		if (StringUtils.isEmpty(id)) {
			return setResutError("该支付信息已过期!");
		}
		// 使用支付id查找数据库
		Long newId = Long.parseLong(id);
		PaymentInfo paymentInfo = paymentInfoDao.getPaymentInfo(newId);
		return setResutSuccessData(paymentInfo);
	}

	@RequestMapping("/getByOrderIdPayInfo")
	public Map<String, Object> getByOrderIdPayInfo(@Param("orderId") String orderId) {
		PaymentInfo byOrderIdPayInfo = paymentInfoDao.getByOrderIdPayInfo(orderId);
		if (byOrderIdPayInfo == null) {
			return setResutError("没有查找到信息");
		}
		return setResutSuccessData(byOrderIdPayInfo);
	}

	@RequestMapping("/updatePayInfo")
	public Map<String, Object> updatePayInfo(@RequestBody PaymentInfo paymentInfo) {
		paymentInfoDao.updatePayInfo(paymentInfo);
		return setResutSuccess();
	}
}
