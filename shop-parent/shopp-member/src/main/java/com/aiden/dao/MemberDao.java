
package com.aiden.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.aiden.common.base.myabtis.BaseDao;
import com.aiden.entity.MbUser;

@Mapper
public interface MemberDao extends BaseDao {

	/**
	 *
	 * @methodDesc: 功能描述:(使用条件查询用户信息)
	 * @param: @param
	 *             mbUser
	 * @param: @return
	 */
	@Select("select ID,USERNAME,PASSWORD,phone,EMAIL, created,updated from mb_user  WHERE PHONE=${mbUser.phone};")
	public MbUser getMbUser(@Param("mbUser") MbUser mbUser);


}
