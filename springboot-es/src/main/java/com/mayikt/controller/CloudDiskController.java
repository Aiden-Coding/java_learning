// package com.mayikt.controller;
//
// import java.util.Optional;
//
// import org.apache.commons.lang.StringUtils;
// import org.elasticsearch.index.query.BoolQueryBuilder;
// import org.elasticsearch.index.query.MatchQueryBuilder;
// import org.elasticsearch.index.query.QueryBuilders;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.Pageable;
// import org.springframework.data.web.PageableDefault;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
//
// import com.mayikt.entity.CloudDiskEntity;
// import com.mayikt.repository.CloudDiskDao;
//
//// SpringBoot 整合ES增删改查
// @RestController
// public class CloudDiskController {
//
// @Autowired
// private CloudDiskDao cloudDiskDao;
//
// // /findById/1
// // /findById/2
// @RequestMapping("/findById/{id}")
// public Optional<CloudDiskEntity> findById(@PathVariable String id) {
// return cloudDiskDao.findById(id);
// }
//
// // page 表示请求的页数 从0开始
// // value size 每一页展示多少条数据
// // @PageableDefault 默认值
// // 2018史上最全SpringBoot 拆分成 史上，最全，springboot
// @RequestMapping("/search")
// public Page<CloudDiskEntity> search(String keyWord, @PageableDefault(page =
// 0, value = 2) Pageable pageable) {
// // 查询所有的
// BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
// if (!StringUtils.isEmpty(keyWord)) {
// // 模糊查询 一定要ik中文
// MatchQueryBuilder matchQuery = QueryBuilders.matchQuery("name", keyWord);
// boolQuery.must(matchQuery);
// }
// Page<CloudDiskEntity> page = cloudDiskDao.search(boolQuery, pageable);
// return page;
//
// }
//
// }
