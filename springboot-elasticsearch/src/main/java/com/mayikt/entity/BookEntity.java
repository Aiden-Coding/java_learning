package com.mayikt.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import lombok.Data;

@Document(indexName = "mymayikt", type = "book")
@Data
public class BookEntity {
	@Id
	String id;
	String name;
	String message;

}
