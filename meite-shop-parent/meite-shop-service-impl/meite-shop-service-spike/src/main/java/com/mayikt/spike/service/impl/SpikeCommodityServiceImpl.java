package com.mayikt.spike.service.impl;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.google.common.util.concurrent.RateLimiter;
import com.mayikt.api.spike.service.SpikeCommodityService;
import com.mayikt.base.BaseApiService;
import com.mayikt.base.BaseResponse;
import com.mayikt.core.token.GenerateToken;
import com.mayikt.spike.producer.SpikeCommodityProducer;
import com.mayikt.spike.service.mapper.SeckillMapper;
import com.mayikt.spike.service.mapper.entity.SeckillEntity;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class SpikeCommodityServiceImpl extends BaseApiService<JSONObject> implements SpikeCommodityService {
	@Autowired
	private SeckillMapper seckillMapper;
	@Autowired
	private GenerateToken generateToken;
	@Autowired
	private SpikeCommodityProducer spikeCommodityProducer;

	@Override
	@Transactional
	@HystrixCommand(fallbackMethod = "spikeFallback")
	public BaseResponse<JSONObject> spike(String phone, Long seckillId) {
		log.info(">>>>>>秒杀服务接口:spike()线程名称:" + Thread.currentThread().getName());
		// 1.秒杀服务实现限流

		// boolean tryAcquire = rateLimiter.tryAcquire(0, TimeUnit.SECONDS);
		// if (!tryAcquire) {
		// return setResultError("服务忙，请稍后重试!");
		// }
		// 1.参数验证
		if (StringUtils.isEmpty(phone)) {
			return setResultError("手机号码不能为空!");
		}
		if (seckillId == null) {
			return setResultError("商品库存id不能为空!");
		}

		// 3.获取到秒杀token之后，异步放入mq中实现修改商品的库存
		sendSeckillMsg(seckillId, phone);
		return setResultSuccess("正在排队中.......");
	}

	private BaseResponse<JSONObject> spikeFallback(String phone, Long seckillId) {
		return setResultError("服务器忙,请稍后重试!");
	}

	/**
	 * 获取到秒杀token之后，异步放入mq中实现修改商品的库存
	 */
	@Async
	private void sendSeckillMsg(Long seckillId, String phone) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("seckillId", seckillId);
		jsonObject.put("phone", phone);
		spikeCommodityProducer.send(jsonObject);
	}

	// 结业项目中采用rabbitmq实现秒杀
	/**
	 * 面试官 都喜欢问 你们项目中在那些地方使用到多线程
	 * 
	 * @param seckillId
	 * @param tokenQuantity
	 * @return
	 */

	// 采用redis数据库类型为 list类型 key为 商品库存id list 多个秒杀token
	@Override
	public BaseResponse<JSONObject> addSpikeToken(Long seckillId, Long tokenQuantity) {
		// 1.验证参数
		if (seckillId == null) {
			return setResultError("商品库存id不能为空!");
		}
		if (tokenQuantity == null) {
			return setResultError("token数量不能为空!");
		}
		SeckillEntity seckillEntity = seckillMapper.findBySeckillId(seckillId);
		if (seckillEntity == null) {
			return setResultError("商品信息不存在!");
		}
		// 2.使用多线程异步生产令牌
		createSeckillToken(seckillId, tokenQuantity);
		return setResultSuccess("令牌正在生成中.....");
	}

	@Async
	private void createSeckillToken(Long seckillId, Long tokenQuantity) {
		generateToken.createListToken("seckill_", seckillId + "", tokenQuantity);
	}

}
