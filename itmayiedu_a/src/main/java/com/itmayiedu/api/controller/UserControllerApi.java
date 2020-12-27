/**
 * 功能说明:
 * 功能作者:
 * 创建日期:
 * 版权归属:每特教育|蚂蚁课堂所有 www.itmayiedu.com
 */
package com.itmayiedu.api.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.itmayidu.des.DES;
import com.itmayiedu.base.BaseApiService;
import com.itmayiedu.base.ResponseBase;

/**
 * 功能说明: <br>
 * 创建作者:每特教育-余胜军<br>
 * 创建时间:2018年7月21日 下午2:47:05<br>
 * 教育机构:每特教育|蚂蚁课堂<br>
 * 版权说明:上海每特教育科技有限公司版权所有<br>
 * 官方网站:www.itmayiedu.com|www.meitedu.com<br>
 * 联系方式:qq644064779<br>
 * 注意:本内容有每特教育学员共同研发,请尊重原创版权
 */
@RequestMapping("/userApi")
@RestController
public class UserControllerApi extends BaseApiService {
	private static final String PASSWORD = "95880288";

	@RequestMapping("/register")
	public ResponseBase register(@RequestBody JSONObject data) {
		String userName = data.getString("userName");
		String passWrod = data.getString("passWrod");
		if (userName.equals("123456") && passWrod.equals("123456")) {
			return setResultSuccess("登陆成功!");
		}
		return setResultError("登陆失败!");
	}

	// 加密方式
	@RequestMapping("/encryptLogin")
	public ResponseBase encryptRegister(String userName, String passWord) throws Exception {
		String decUserName = new String(DES.decrypt(userName.getBytes(), PASSWORD));
		String decpassWord = new String(DES.decrypt(passWord.getBytes(), PASSWORD));
		if (decUserName.equals("123456") && decpassWord.equals("123456")) {
			return setResultSuccess("登陆成功!");
		}
		return setResultError("登陆失败!");
	}

	// 返回加密信息
	@RequestMapping("/encryptUserInfo")
	public ResponseBase encryptUserInfo(String userName, String passWord) throws Exception {
		JSONObject data = new JSONObject();
		data.put("encryptUserName", DES.encrypt(userName.getBytes(), PASSWORD));
		data.put("encryptPassWord", DES.encrypt(passWord.getBytes(), PASSWORD));
		return setResultSuccessData(data);
	}
}
