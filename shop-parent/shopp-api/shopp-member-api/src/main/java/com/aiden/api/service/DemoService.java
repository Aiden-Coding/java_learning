
package com.aiden.api.service;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.aiden.entity.MbUser;

/**
 *
 * @classDesc: 功能描述:(会员demo)
 * @author: 余胜军
 * @createTime: 2017年10月20日 下午10:05:17
 * @version: v1.0
 * @copyright:上海每特教育科技有限公司
 * @QQ:644064779
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
