package com.aiden.entity;

import com.aiden.common.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MbUser extends BaseEntity {

	private String username;

	private String password;

	private String phone;

	private String email;

}
