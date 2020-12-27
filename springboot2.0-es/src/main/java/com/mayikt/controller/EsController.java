package com.mayikt.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mayikt.entity.UserEntity;
import com.mayikt.repository.UserDao;

@RestController
public class EsController {
	@Autowired
	private UserDao userDao;

	// 添加文档
	@RequestMapping("/addUser")
	public UserEntity addUser(@RequestBody UserEntity userEntity) {
		return userDao.save(userEntity);
	}

	// 查询文档
	@RequestMapping("/findById")
	public Optional<UserEntity> findById(String id) {
		return userDao.findById(id);
	}

	@RequestMapping("/selectUser")
	public List<UserEntity> selectUser(String q) {
		QueryStringQueryBuilder builder = new QueryStringQueryBuilder(q);
		Iterable<UserEntity> searchResult = userDao.search(builder);
		Iterator<UserEntity> iterator = searchResult.iterator();
		List<UserEntity> list = new ArrayList<UserEntity>();
		while (iterator.hasNext()) {
			list.add(iterator.next());
		}
		return list;
	}

	public SearchQuery getSearchQuery(String car) {
		QueryBuilders.matchQuery("car", car);
		return null;

	}

}
