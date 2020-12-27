package com.itmayiedu.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.itmayiedu.entity.User;

public interface UserMapper {
	@Select("select * from user")
	public List<User> getSelectUser();

	@Update("UPDATE user set userName=#{userName},userAge=#{userAge} where id=#{id}")
	public void getUpdateUser(User user);

	@Insert("insert into user(userName,userAge) values(#{userName},#{userAge});")
	public void getInsertUser(User user);

	@Delete("DELETE FROM user where id=#{id}")
	public void getDeleteUser(@Param("")int id);

}