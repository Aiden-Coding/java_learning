package com.itmayiedu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.itmayiedu.entity.User;

public interface UserMapper {
	@Select("SELECT * FROM USERS ")
	List<User> findUserList();

}
