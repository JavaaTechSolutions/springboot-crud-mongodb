package com.jts.springboot.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.jts.springboot.entity.Products;

public interface PaginationRepository extends MongoRepository<Products, Long> {
	Page<Products> findAll(Pageable pageable);
	
//	Page<Products> findAll(Sort srt);
}
