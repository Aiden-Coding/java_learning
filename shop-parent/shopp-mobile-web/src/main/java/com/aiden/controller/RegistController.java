
package com.aiden.controller;

import com.aiden.base.controller.BaseController;
import com.aiden.constants.BaseApiConstants;
import com.aiden.entity.UserEntity;
import com.aiden.feign.UserFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


@Controller
public class RegistController extends BaseController {
	private static final String LOCAREGIST = "locaRegist";
	private static final String LGOIN = "login";
	@Autowired
	private UserFeign userFeign;

	@RequestMapping("/locaRegist")
	public String locaRegist() {
		return LOCAREGIST;
	}

	@RequestMapping("/regist")
	public String regist(UserEntity userEntity, HttpServletRequest request) {
		try {
			Map<String, Object> registMap = userFeign.regist(userEntity);
			Integer code = (Integer) registMap.get(BaseApiConstants.HTTP_CODE_NAME);
			if (!code.equals(BaseApiConstants.HTTP_200_CODE)) {
				String msg = (String) registMap.get("msg");
				return setError(request, msg, LOCAREGIST);
			}
			// 注册成功
			return LGOIN;

		} catch (Exception e) {
			return setError(request, "注册失败!", LOCAREGIST);
		}
	}

}
