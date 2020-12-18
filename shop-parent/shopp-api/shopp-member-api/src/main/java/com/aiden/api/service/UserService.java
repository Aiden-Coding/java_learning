
package com.aiden.api.service;

import com.aiden.entity.UserEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

	/**
	 * 功能描述:登录成功后,生成对应的token，作为key,将用户userID作为value存放在redis中.返回token给客户端.
	 *
	 * @methodDesc: 功能描述:(登录)
	 * @param: @return
	 */
	@PostMapping("/login")
	public Map<String, Object> login(@RequestBody UserEntity userEntity);

	/**
	 *
	 * @methodDesc: 功能描述:(使用token查找用户信息)
	 * @param: @param
	 *             token
	 * @param: @return
	 */
	@PostMapping("/getUser")
	public Map<String, Object> getUser(@RequestParam("token") String token);
}
