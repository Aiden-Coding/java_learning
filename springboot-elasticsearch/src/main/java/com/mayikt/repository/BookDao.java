package com.mayikt.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mayikt.entity.BookEntity;

public interface BookDao extends CrudRepository<BookEntity, String> {
	public List<BookEntity> getByMessage(String key);
}
