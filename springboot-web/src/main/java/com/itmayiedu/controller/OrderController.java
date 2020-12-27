/**
 * 功能说明:
 * 功能作者:
 * 创建日期:
 * 版权归属:每特教育|蚂蚁课堂所有 www.itmayiedu.com
 */
package com.itmayiedu.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itmayeidu.ext.ExtApiIdempotent;
import com.itmayeidu.utils.ConstantUtils;
import com.itmayeidu.utils.RedisToken;
import com.itmayiedu.entity.OrderEntity;
import com.itmayiedu.mapper.OrderMapper;

/**
 * 功能说明: <br>
 * 创建作者:每特教育-余胜军<br>
 * 创建时间:2018年7月12日 下午3:41:02<br>
 * 教育机构:每特教育|蚂蚁课堂<br>
 * 版权说明:上海每特教育科技有限公司版权所有<br>
 * 官方网站:www.itmayiedu.com|www.meitedu.com<br>
 * 联系方式:qq644064779<br>
 * 注意:本内容有每特教育学员共同研发,请尊重原创版权
 */
@RestController
public class OrderController {

	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private RedisToken redisToken;

	// @Autowired
	// private RedisTokenUtils redisTokenUtils;
	//
	// 从redis中获取Token
	@RequestMapping("/redisToken")
	public String RedisToken() {
		return redisToken.getToken();
	}

	// @RequestMapping(value = "/addOrderExtApiIdempotent", produces =
	// "application/json; charset=utf-8")
	// @ExtApiIdempotent(type = ConstantUtils.EXTAPIHEAD)
	// public String addOrderExtApiIdempotent(@RequestBody OrderEntity
	// orderEntity, HttpServletRequest request) {
	// // 如何使用Token 解决幂等性
	// // 步骤：
	// // 2.调用接口的时候，将该令牌放入的请求头中(获取请求头中的令牌)
	// String token = request.getHeader("token");
	// if (StringUtils.isEmpty(token)) {
	// return "参数错误";
	// }
	// // 3.接口获取对应的令牌,如果能够获取该(从redis获取令牌)令牌(将当前令牌删除掉) 就直接执行该访问的业务逻辑
	// boolean isToken = redisToken.findToken(token);
	// // 4.接口获取对应的令牌,如果获取不到该令牌 直接返回请勿重复提交
	// if (!isToken) {
	// return "请勿重复提交!";
	// }
	// int result = orderMapper.addOrder(orderEntity);
	// return result > 0 ? "添加成功" : "添加失败" + "";
	// }

	@RequestMapping(value = "/addOrderExtApiIdempotent", produces = "application/json; charset=utf-8")
	@ExtApiIdempotent(type = ConstantUtils.EXTAPIHEAD)
	public String addOrderExtApiIdempotent(@RequestBody OrderEntity orderEntity, HttpServletRequest request) {
		int result = orderMapper.addOrder(orderEntity);
		return result > 0 ? "添加成功" : "添加失败" + "";
	}

}
