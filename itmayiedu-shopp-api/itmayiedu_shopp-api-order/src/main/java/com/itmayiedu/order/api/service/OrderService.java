package com.itmayiedu.order.api.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itmayiedu.base.ResponseBase;

@RequestMapping("/order")
public interface OrderService {

	/**
	 * 使用订单id修改订单状态
	 * 
	 * @param orderId
	 * @return
	 */
	@RequestMapping("/updateOrderId")
	public ResponseBase updateOrderId(@RequestParam("isPay") Long isPay, @RequestParam("payId") String aliPayId,
			@RequestParam("orderNumber") String orderNumber);

}
