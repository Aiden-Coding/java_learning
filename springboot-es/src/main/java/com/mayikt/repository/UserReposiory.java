package com.mayikt.repository;

import org.springframework.data.repository.CrudRepository;

import com.mayikt.entity.UserEntity;

public interface UserReposiory extends CrudRepository<UserEntity, String> {

}
