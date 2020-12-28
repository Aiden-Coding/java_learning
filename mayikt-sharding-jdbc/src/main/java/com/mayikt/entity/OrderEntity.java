package com.mayikt.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "t_order")
public class OrderEntity {
	@Id
	private Long orderId;

	private Long userId;

}
