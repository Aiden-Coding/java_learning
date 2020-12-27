/**
 * 功能说明:
 * 功能作者:
 * 创建日期:
 * 版权归属:每特教育|蚂蚁课堂所有 www.itmayiedu.com
 */
package com.itmayiedu.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itmayiedu.api.entity.UserEntity;
import com.itmayiedu.api.feign.MemberServiceFeigin;
import com.itmayiedu.api.service.IOrderService;

/**
 * 功能说明: <br>
 * 创建作者:每特教育-余胜军<br>
 * 创建时间:2018年9月6日 下午10:31:04<br>
 * 教育机构:每特教育|蚂蚁课堂<br>
 * 版权说明:上海每特教育科技有限公司版权所有<br>
 * 官方网站:www.itmayiedu.com|www.meitedu.com<br>
 * 联系方式:qq644064779<br>
 * 注意:本内容有每特教育学员共同研发,请尊重原创版权
 */
@RestController
public class OrderServiceImpl implements IOrderService {
	// 订单服务继承会员服务接口，用来实现feign客户端 减少重复接口代码
	@Autowired
	private MemberServiceFeigin memberServiceFeigin;

	@RequestMapping("/orderToMember")
	public String orderToMember(String name) {
		UserEntity user = memberServiceFeigin.getMember(name);
		return user == null ? "没有找到用户信息" : user.toString();
	}

}
