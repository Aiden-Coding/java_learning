/**
 * 功能说明:
 * 功能作者:
 * 创建日期:
 * 版权归属:每特教育|蚂蚁课堂所有 www.itmayiedu.com
 */
package com.itmayiedu.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itmayiedu.api.entity.UserEntity;
import com.itmayiedu.api.service.IOrderService;
import com.itmayiedu.fegin.MemberApiFegin;

/**
 * 功能说明: <br>
 * 创建作者:每特教育-余胜军<br>
 * 创建时间:2018年9月6日 下午5:21:36<br>
 * 教育机构:每特教育|蚂蚁课堂<br>
 * 版权说明:上海每特教育科技有限公司版权所有<br>
 * 官方网站:www.itmayiedu.com|www.meitedu.com<br>
 * 联系方式:qq644064779<br>
 * 注意:本内容有每特教育学员共同研发,请尊重原创版权
 */
@RestController
public class IOrderServiceImpl implements IOrderService {
	@Autowired
	private MemberApiFegin memberApiFegin;

	@RequestMapping("/getOrederToMember")
	public String getOrederToMember(@RequestParam("name") String name) {
		UserEntity user = memberApiFegin.getUser(name);
		return user.toString();
	}

}
