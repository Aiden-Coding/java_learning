
package com.aiden.common.base.entity;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

/**
 *
 * @classDesc: 功能描述:(封装一些相同字段和属性)



 * @createTime: 2017年10月24日 下午9:20:15
 * @version: v1.0

 */
@Getter
@Setter
public class BaseEntity {

	/**
	 * 主键
	 */
	private Long id;
	/**
	 * 创建时间
	 */
	private Timestamp created;
	/**
	 * 修改时间
	 */
	private Timestamp updated;
//    public static void main(String[] args) {
//		log.info("我在使用lomBok  自动生成 get 和set 方法 还有自动日志");
//	}
}
