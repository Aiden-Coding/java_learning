/**
 * 功能说明:
 * 功能作者:
 * 创建日期:
 * 版权归属:每特教育|蚂蚁课堂所有 www.itmayiedu.com
 */
package com.itmayiedu.service;

import org.springframework.expression.spel.ast.BooleanLiteral;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

@Service
public class OrderService {

	public boolean addOrder() {
		System.out.println("db....正在操作订单表数据库...");
		return true;
	}

}
