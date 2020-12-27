/**
 * 功能说明:
 * 功能作者:
 * 创建日期:
 * 版权归属:每特教育|蚂蚁课堂所有 www.itmayiedu.com
 */
package com.itmayiedu.api.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

/**
 * 功能说明: <br>
 * 创建作者:每特教育-余胜军<br>
 * 创建时间:2018年8月20日 下午3:11:53<br>
 * 教育机构:每特教育|蚂蚁课堂<br>
 * 版权说明:上海每特教育科技有限公司版权所有<br>
 * 官方网站:www.itmayiedu.com|www.meitedu.com<br>
 * 联系方式:qq644064779<br>
 * 注意:本内容有每特教育学员共同研发,请尊重原创版权
 */
@SpringBootApplication
@RestController
public class BIndexController {

	@RequestMapping("/ajaxB")
	public Map<String, Object> ajaxB(HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("errorCode", "200");
		result.put("errorMsg", "登陆成功");
		return result;
	}

	// @RequestMapping(value = "/ajaxB", method = RequestMethod.GET)
	// public void ajaxB(HttpServletResponse response, String jsonpCallback)
	// throws IOException {
	// JSONObject root = new JSONObject();
	// root.put("errorCode", "200");
	// root.put("errorMsg", "登陆成功");
	// response.setHeader("Content-type", "text/html;charset=UTF-8");
	// PrintWriter writer = response.getWriter();
	// writer.print(jsonpCallback + "(" + root.toString() + ")");
	// writer.close();
	// }

	public static void main(String[] args) {
		SpringApplication.run(BIndexController.class, args);
	}

}
