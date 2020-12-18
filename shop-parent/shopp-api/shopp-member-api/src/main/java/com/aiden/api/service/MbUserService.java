
package com.aiden.api.service;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aiden.entity.MbUser;

/**
 *
 * @classDesc: 功能描述:(会员服务接口)

 * @createTime: 2017年10月22日 下午3:46:02
 * @version: v1.0

 */
@RequestMapping("/member/user")
public interface MbUserService {

	/**
	 *
	 * @methodDesc: 功能描述:(注册接口)
	 * @param: @param
	 *             mbUser
	 * @param: @return
	 */
	@RequestMapping("/register")
	public Map<String, Object> register(@RequestBody MbUser mbUser);

}
