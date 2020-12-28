package com.mayikt.member.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.mayikt.base.BaseApiService;
import com.mayikt.base.BaseResponse;
import com.mayikt.core.bean.MeiteBeanUtils;
import com.mayikt.core.utils.MD5Util;
import com.mayikt.member.MemberLoginService;
import com.mayikt.member.input.dto.UserLoginInpDTO;
import com.mayikt.member.mapper.UserMapper;
import com.mayikt.member.mapper.entity.UserDo;

@RestController
public class MemberLoginServiceImpl extends BaseApiService<JSONObject> implements MemberLoginService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public BaseResponse<JSONObject> login(UserLoginInpDTO userLoginInpDTO) {

		// 1.验证登陆请求参数
		String mobile = userLoginInpDTO.getMobile();
		if (StringUtils.isEmpty(mobile)) {
			return setResultError("手机号码不能为空!");
		}
		String password = userLoginInpDTO.getPassword();
		if (StringUtils.isEmpty(password)) {
			return setResultError("密码不能为空!");
		}
		// 2.将密码实现MD5加密
		String newPassword = MD5Util.MD5(password);
		userLoginInpDTO.setPassword(newPassword);
		// 3.将dto转换do查询数据库
		UserDo userDo = MeiteBeanUtils.dtoToDo(userLoginInpDTO, UserDo.class);
		userMapper.login(mobile, newPassword);
		// 4.如果userDo不null，生成用户令牌 存放子redis中,key 为token value=userid
		if (userDo == null) {
			return setResultError("手机号码或者密码错误!");
		}
		Long userId = userDo.getUserId();
		//

		return null;
	}
	// 你们公司移动app端怎么实现登陆？ 令牌 用户登陆成功之后 生成登陆令牌存入在app客户端
	// ，app客户端需要绑绑定会话信息的时候，在请求头传递token

	// 如果是PC端怎么实现登陆呢？ 存入在Cookies可能会存在伪造用户信息

}
