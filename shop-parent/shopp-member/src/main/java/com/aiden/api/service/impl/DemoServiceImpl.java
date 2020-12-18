
package com.aiden.api.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aiden.api.service.DemoService;
import com.aiden.common.base.api.BaseApiService;
import com.aiden.common.base.redis.BaseRedisService;
import com.aiden.constants.ConstantsTables;
import com.aiden.dao.MemberDao;
import com.aiden.entity.MbUser;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class DemoServiceImpl extends BaseApiService implements DemoService {

	@Autowired
	private BaseRedisService baseRedisService;
	@Autowired
	private MemberDao memberDao;

	@Override
	public Map<String, Object> index() {
		String result = "644064";
		log.info("request .... index result:{}", result);
		return setResultData(result);
	}

	@Override
	public Map<String, Object> setString(String key, String value) {
		baseRedisService.setString(key, value, 30l);
		return setResultSuccess();
	}

	@Override
	public Map<String, Object> getKey(String key) {
		String value = (String) baseRedisService.getString(key);
		return setResultData(value);
	}

	@Override
	public Map<String, Object> insertUser(@RequestBody MbUser mbUser) {
		try {
			memberDao.save(mbUser, ConstantsTables.TABLE_MB_USER);
		} catch (Exception e) {
			log.error("###insertUser(),ERROR:", e);
			return setResultError(e.getMessage());
		}
		return setResultSuccess();
	}

	@Override
	public Map<String, Object> updateUser(@RequestBody MbUser mbUser) {
		mbUser.setUpdated(new Timestamp(new Date().getTime()));
		try {
			memberDao.update(mbUser, ConstantsTables.TABLE_MB_USER, mbUser.getId());
		} catch (Exception e) {
			return setResultError(e.getMessage());
		}
		return setResultSuccess();
	}

}
