package com.mayikt.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mayikt.entity.BookEntity;
import com.mayikt.repository.BookDao;

@RestController
public class ElasticController {
	@Autowired
	private BookDao bookDao;

	// 新增book
	@RequestMapping("/addBook")
	public BookEntity addBook(@RequestBody BookEntity book) {
		return bookDao.save(book);
	}

	@RequestMapping("/getBook")
	public Optional<BookEntity> getBook(String id) {
		return bookDao.findById(id);
	}
}
