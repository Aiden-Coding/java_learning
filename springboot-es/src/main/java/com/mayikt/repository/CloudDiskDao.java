package com.mayikt.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.mayikt.entity.CloudDiskEntity;

public interface CloudDiskDao extends ElasticsearchRepository<CloudDiskEntity, String> {

}
