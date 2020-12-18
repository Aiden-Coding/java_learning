package com.itmayiedu.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 商品类型
 *
 * @author Administrator
 *
 */
@Getter
@Setter
public class ItemCatEntity {
	private Long id;

	private Long parentId;

	private String name;

	private String img;

	private Integer status;

	private Integer sortOrder;

	private Boolean isParent;

	private Date created;

	private Date updated;
}
