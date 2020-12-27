/**
 * 功能说明:
 * 功能作者:
 * 创建日期:
 * 版权归属:每特教育|蚂蚁课堂所有 www.itmayiedu.com
 */
package com.itmayiedu.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.itmayiedu.entity.Users;
import com.itmayiedu.mapper.UserMapper;

/**
 * 功能说明: <br>
 * 创建作者:每特教育-余胜军<br>
 * 创建时间:2018年8月4日 下午4:14:35<br>
 * 教育机构:每特教育|蚂蚁课堂<br>
 * 版权说明:上海每特教育科技有限公司版权所有<br>
 * 官方网站:www.itmayiedu.com|www.meitedu.com<br>
 * 联系方式:qq644064779<br>
 * 注意:本内容有每特教育学员共同研发,请尊重原创版权
 */
@Service
public class UserService {
	@Autowired
	private EhCacheUtils ehCacheUtils;
	private static final String CACHENAME_USERCACHE = "userCache";
	@Autowired
	private RedisService redisService;
	@Autowired
	private UserMapper userMapper;

	public Users getUser(Long id) {
		String key = this.getClass().getName() + "-" + Thread.currentThread().getStackTrace()[1].getMethodName()
				+ "-id:" + id;
		// 1.先查找一级缓存(本地缓存),如果本地缓存有数据直接返回
		Users ehUser = (Users) ehCacheUtils.get(CACHENAME_USERCACHE, key);
		if (ehUser != null) {
			System.out.println("使用key:" + key + ",查询一级缓存 ehCache 获取到ehUser:" + JSONObject.toJSONString(ehUser));
			return ehUser;
		}
		// 2. 如果本地缓存没有该数据，直接查询二级缓存(redis)
		String redisUserJson = redisService.getString(key);
		if (!StringUtils.isEmpty(redisUserJson)) {
			// 将json 转换为对象(如果二级缓存redis中有数据直接返回二级缓存)
			JSONObject jsonObject = new JSONObject();
			Users user = jsonObject.parseObject(redisUserJson, Users.class);
			// 更新一级缓存
			ehCacheUtils.put(CACHENAME_USERCACHE, key, user);
			System.out.println("使用key:" + key + ",查询二级缓存 redis 获取到ehUser:" + JSONObject.toJSONString(user));
			return user;
		}
		// 3. 如果二级缓存redis中也没有数据,查询数据库
		Users user = userMapper.getUser(id);
		if (user == null) {
			return null;
		}
		// 更新一级缓存和二级缓存
		String userJson = JSONObject.toJSONString(user);
		redisService.setString(key, userJson);
		ehCacheUtils.put(CACHENAME_USERCACHE, key, user);
		System.out.println("使用key:" + key + "，一级缓存和二级都没有数据,直接查询db" + userJson);
		return user;
	}

}
