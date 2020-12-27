/**
 * 功能说明:
 * 功能作者:
 * 创建日期:
 * 版权归属:每特教育|蚂蚁课堂所有 www.itmayiedu.com
 */
package com.itmayiedu.api.member.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itmayeidu.api.member.UserService;

/**
 * 功能说明: <br>
 * 创建作者:每特教育-余胜军<br>
 * 创建时间:2018年9月18日 下午4:27:46<br>
 * 教育机构:每特教育|蚂蚁课堂<br>
 * 版权说明:上海每特教育科技有限公司版权所有<br>
 * 官方网站:www.itmayiedu.com|www.meitedu.com<br>
 * 联系方式:qq644064779<br>
 * 注意:本内容有每特教育学员共同研发,请尊重原创版权
 */
@Service(version = "1.0.1")
public class UserServiceImpl implements UserService {

	@Override
	public String getUser(Long userId) {
		System.out.println("订单服务调用会员服务接口,userId:" + userId);
		return "订单服务调用会员服务接口";
	}

}
