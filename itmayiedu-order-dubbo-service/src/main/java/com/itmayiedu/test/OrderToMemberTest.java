/**
 * 功能说明:
 * 功能作者:
 * 创建日期:
 * 版权归属:每特教育|蚂蚁课堂所有 www.itmayiedu.com
 */
package com.itmayiedu.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.itmayiedu.api.member.service.MemberService;

/**
 * 功能说明: <br>
 * 创建作者:每特教育-余胜军<br>
 * 创建时间:2018年9月18日 下午10:04:04<br>
 * 教育机构:每特教育|蚂蚁课堂<br>
 * 版权说明:上海每特教育科技有限公司版权所有<br>
 * 官方网站:www.itmayiedu.com|www.meitedu.com<br>
 * 联系方式:qq644064779<br>
 * 注意:本内容有每特教育学员共同研发,请尊重原创版权
 */
public class OrderToMemberTest {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("consumer.xml");
		app.start();
		MemberService memberService = app.getBean(MemberService.class);
		String resultUser = memberService.getUser(1l);
		System.out.println("订单服务调用会员服务返回结果:" + resultUser);
	}
}
