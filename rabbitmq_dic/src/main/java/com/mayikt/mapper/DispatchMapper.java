package com.mayikt.mapper;

import org.apache.ibatis.annotations.Insert;

import com.mayikt.entity.DispatchEntity;

public interface DispatchMapper {

	/**
	 * 新增派单任务
	 */
	@Insert("INSERT into platoon values (null,#{orderId},#{dispatchRoute},#{takeoutUserId});")
	public int insertDistribute(DispatchEntity distributeEntity);

}
