package com.mayikt.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.mayikt.entity.UserEntity;

public interface UserDao extends ElasticsearchRepository<UserEntity, String> {

}
