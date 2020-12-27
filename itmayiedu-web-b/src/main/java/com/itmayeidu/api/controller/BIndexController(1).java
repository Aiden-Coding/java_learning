/**
 * 功能说明:
 * 功能作者:
 * 创建日期:
 * 版权归属:每特教育|蚂蚁课堂所有 www.itmayiedu.com
 */
package com.itmayeidu.api.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

/**
 * 功能说明: <br>
 * 创建作者:每特教育-余胜军<br>
 * 创建时间:2018年8月20日 下午8:58:02<br>
 * 教育机构:每特教育|蚂蚁课堂<br>
 * 版权说明:上海每特教育科技有限公司版权所有<br>
 * 官方网站:www.itmayiedu.com|www.meitedu.com<br>
 * 联系方式:qq644064779<br>
 * 注意:本内容有每特教育学员共同研发,请尊重原创版权
 */
@RestController
@SpringBootApplication
public class BIndexController {

	// 该接口提供给A项目进行ajax调用
	@RequestMapping("/getBInfo")
	public Map<String, Object> getBInfo(HttpServletResponse response) {
		// 告诉客户端（浏览器 ）允许跨域访问 *表示所有域名都是可以 在公司中正常的代码应该放入在过滤器中
		// response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("retCode", "200");
		result.put("retMsg", "登陆成功");
		return result;
	}

	// 使用jsonp 解决跨域问题
	// @RequestMapping("/getBInfo")
	// public void getBInfo(HttpServletResponse response, String jsonpCallback)
	// throws IOException {
	// // 告诉客户端（浏览器 ）允许跨域访问 *表示所有域名都是可以 在公司中正常的代码应该放入在过滤器中
	// // Map<String, Object> result = new HashMap<String, Object>();
	// // result.put("retCode", "200");
	// // result.put("retMsg", "登陆成功");
	// JSONObject result = new JSONObject();
	// result.put("retCode", "200");
	// result.put("retMsg", "登陆成功");
	// PrintWriter writer = response.getWriter();
	// writer.println(jsonpCallback + "(" + result.toJSONString() + ")");
	// writer.close();
	// }

	public static void main(String[] args) {
		SpringApplication.run(BIndexController.class, args);
	}

}
