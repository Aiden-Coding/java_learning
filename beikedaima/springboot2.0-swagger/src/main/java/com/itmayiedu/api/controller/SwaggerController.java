/**
 * 功能说明:
 * 功能作者:
 * 创建日期:
 * 版权归属:每特教育|蚂蚁课堂所有 www.itmayiedu.com
 */
package com.itmayiedu.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 功能说明: <br>
 * 创建作者:每特教育-余胜军<br>
 * 创建时间:2018年9月15日 下午6:12:23<br>
 * 教育机构:每特教育|蚂蚁课堂<br>
 * 版权说明:上海每特教育科技有限公司版权所有<br>
 * 官方网站:www.itmayiedu.com|www.meitedu.com<br>
 * 联系方式:qq644064779<br>
 * 注意:本内容有每特教育学员共同研发,请尊重原创版权
 */
@Api("客服接口")
@RestController
public class SwaggerController {

	@ApiOperation(value = "swagger接口", nickname = "swagger别名")
	@GetMapping("/swaggerDemo")
	public String swaggerDemo() {
		return "swaggerDemo";
	}

	@ApiOperation(value = "获取会员信息接口", nickname = "根据userName获取用户相关信息")
	@ApiImplicitParam(name = "userName", value = "用户名称", required = true, dataType = "String")
	@PostMapping("/getMember")
	public String getMember(String userName) {
		System.out.println("userName:" + userName);
		return "getMember" + ",userName:" + userName;
	}

}
