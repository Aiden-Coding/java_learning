package com.itmayiedu.api.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itmayiedu.base.BaseApiService;
import com.itmayiedu.base.ResponseBase;
import com.itmayiedu.dao.OrderDao;
import com.itmayiedu.order.api.service.OrderService;

@RestController
public class OrderServiceImpl extends BaseApiService implements OrderService {
	@Autowired
	private OrderDao orderDao;

	@Override
	public ResponseBase updateOrderId(@RequestParam("isPay") Long isPay, @RequestParam("payId") String aliPayId,
			@RequestParam("orderNumber") String orderNumber) {
		int updateOrder = orderDao.updateOrder(isPay, aliPayId, orderNumber);
		if (updateOrder <= 0) {
			return setResultError("系统错误!");
		}
		return setResultSuccess();
	}

}
