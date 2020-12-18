
package com.aiden.api.service;

import com.aiden.entity.UserEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;


/**
 *
 * @classDesc: 功能描述:(用户服务)

 * @createTime: 2017年10月24日 下午10:50:16
 * @version: v1.0

 */
@RequestMapping("/member")
public interface UserService {
	/**
	 *
	 * @methodDesc: 功能描述:(注册服务)
	 * @param: @param
	 *             UserEntity
	 * @param: @return
	 */
	@PostMapping("/regist")
	public Map<String, Object> regist(@RequestBody UserEntity UserEntity);
}
