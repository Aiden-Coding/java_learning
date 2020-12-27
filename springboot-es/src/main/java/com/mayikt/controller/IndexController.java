package com.mayikt.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mayikt.entity.CloudDiskEntity;
import com.mayikt.repository.CloudDiskDao;

@Controller
public class IndexController {
	@Autowired
	private CloudDiskDao cloudDiskDao;

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping("/search")
	public String search(String keyword, @PageableDefault(page = 0, value = 5) Pageable pageable,
			HttpServletRequest req) {
		// 模糊查询标题名称
		BoolQueryBuilder builder = QueryBuilders.boolQuery();
		if (!StringUtils.isEmpty(keyword)) {
			MatchQueryBuilder nameMatchQuery = QueryBuilders.matchQuery("name", keyword);
			builder.must(nameMatchQuery);
		}
		Page<CloudDiskEntity> page = cloudDiskDao.search(builder, pageable);
		// 计算分页总数
		int totalPage = (int) ((page.getTotalElements() - 1) / pageable.getPageSize() + 1);
		req.setAttribute("page", page);
		// 当前页数
		req.setAttribute("pageNumber", pageable.getPageNumber());
		// 标题名称 关键字
		req.setAttribute("keyword", keyword);
		// 分页总数
		req.setAttribute("totalPage", totalPage);
		return "search";
	}

}
