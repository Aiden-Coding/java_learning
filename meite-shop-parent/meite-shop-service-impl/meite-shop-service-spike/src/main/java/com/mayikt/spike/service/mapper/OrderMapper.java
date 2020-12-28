package com.mayikt.spike.service.mapper;

import org.apache.ibatis.annotations.Insert;

import com.mayikt.spike.service.mapper.entity.OrderEntity;

public interface OrderMapper {

	@Insert("INSERT INTO `meite_order` VALUES (#{seckillId},#{userPhone}, '1', now());")
	int insertOrder(OrderEntity orderEntity);
}
