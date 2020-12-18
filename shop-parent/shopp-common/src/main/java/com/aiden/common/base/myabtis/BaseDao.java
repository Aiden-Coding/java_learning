
package com.aiden.common.base.myabtis;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @classDesc: 功能描述:(BaseDao)
 * @author: 蚂蚁课堂创始人-余胜军
 * @QQ: 644064779
 * @QQ粉丝群: 116295598
 * @createTime: 2017年10月21日 下午9:10:25
 * @version: v1.0
 * @copyright:每特学院(蚂蚁课堂)上海每特教育科技有限公司
 */
public interface BaseDao {

	/**
	 *
	 *
	 * @methodDesc: 功能描述:(增加持久化对象)
	 * @param: @param
	 *             t
	 */
	@InsertProvider(type = BaseProvider.class, method = "save")
	public void save(@Param("oj") Object oj, @Param("table") String table);

	/**
	 *
	 *
	 * @methodDesc: 功能描述:(修改持久化对象)
	 * @param: @param
	 *             t
	 * @return
	 */
	@InsertProvider(type = BaseProvider.class, method = "update")
	public void update(@Param("oj") Object oj, @Param("table") String table, @Param("idKey") Long idKey);

}
