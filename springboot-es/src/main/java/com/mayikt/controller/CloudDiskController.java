package com.mayikt.controller;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;
import com.mayikt.entity.CloudDiskEntity;
import com.mayikt.repository.CloudDiskDao;

@RestController
public class CloudDiskController {
	@Autowired
	private CloudDiskDao cloudDiskDao;

	// 根据id查询文档信息
	@RequestMapping("/findById/{id}")
	public Optional<CloudDiskEntity> findById(@PathVariable String id) {
		return cloudDiskDao.findById(id);
	}

	// 模糊查询文档信息
	@RequestMapping("/findCloudDisk")
	public Page<CloudDiskEntity> findCloudDisk(String name, String describe,
			@PageableDefault(page = 0, value = 2) Pageable pageable) {
		// 模糊查询标题名称
		BoolQueryBuilder builder = QueryBuilders.boolQuery();
		if (!StringUtils.isEmpty(name)) {
			MatchQueryBuilder nameMatchQuery = QueryBuilders.matchQuery("name", name);
			builder.must(nameMatchQuery);
		}
		if (!StringUtils.isEmpty(describe)) {
			// 模糊查询描述
			MatchQueryBuilder matchQuery = QueryBuilders.matchQuery("describe", describe);
			builder.must(matchQuery);
		}

		return cloudDiskDao.search(builder, pageable);
	}

}
