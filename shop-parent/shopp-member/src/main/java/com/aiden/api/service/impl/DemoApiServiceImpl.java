
package com.aiden.api.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.aiden.api.service.DemoApiService;
import com.aiden.common.api.BaseApiService;
import com.aiden.common.redis.BaseRedisService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class DemoApiServiceImpl extends BaseApiService implements DemoApiService {
	@Autowired
	private BaseRedisService baseRedisService;

	@Override
	public Map<String, Object> demo() {
		log.info("this is demo");
		return setResutSuccess();
	}

	@Override
	public Map<String, Object> setKey(String key, String value) {
		baseRedisService.setString(key, value);
		return setResutSuccess();

	}

	@Override
	public Map<String, Object> getKey(String key) {
		String value = baseRedisService.get(key);
		return setResutSuccessData(value);

	}

}
