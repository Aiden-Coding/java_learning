
package com.aiden.api.service;

import com.aiden.entity.MbUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 *
 * @classDesc: 功能描述:(会员demo)

 * @createTime: 2017年10月20日 下午10:05:17
 * @version: v1.0


 */
public interface DemoService {
	// 测试
	@GetMapping("/index")
	public Map<String, Object> index();

	@GetMapping("/setString")
	public Map<String, Object> setString(String key, String value);

	@GetMapping("/getKey")
	public Map<String, Object> getKey(String key);

	@PostMapping("/insertUser")
	public Map<String, Object> insertUser(@RequestBody MbUser MbUser);
	@PostMapping("/updateUser")
	public Map<String, Object> updateUser(@RequestBody MbUser MbUser);

}
