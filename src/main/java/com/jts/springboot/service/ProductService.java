package com.jts.springboot.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.jts.springboot.entity.Order;
import com.jts.springboot.entity.Products;
import com.jts.springboot.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public Products createProduct(Products products) {
		return productRepository.save(products);
	}

	public Products updateProduct(Products products) {
		Optional<Products> optionalProduct = productRepository.findById(products.getId());

		if (optionalProduct.isPresent()) {
			Products updateProd = optionalProduct.get();
			updateProd.setName(products.getName());
			updateProd.setDescription(products.getDescription());

			productRepository.save(updateProd);

			return updateProd;
		}

		throw new RuntimeException("Product Not available " + products.getId());

	}
	
	public List<Products> findAll() {
		return productRepository.findAll();
	}

//	public List<Products> findAllProduct() {
////		List<String> desc = Arrays.asList("Test Demo", "Mobile Demo");
//		return productRepository.findAllProductWithOrder();
//	}
	
	public Page<Products> findAllWithPagination() {
		Sort srt = Sort.by("name");
		Pageable pageable = PageRequest.of(0, 10, srt.ascending().and(Sort.by("description").descending()));
		return productRepository.findAll(pageable);
	}
	
	public List<Products> findAllWithSorting() {
		Sort srt = Sort.by("name");
		return productRepository.findAll(srt);
	}
	
//	public List<String> findAllProductDistinct() {
////		List<String> desc = Arrays.asList("Test Demo", "Mobile Demo");
//		return productRepository.findAllProductById();
//	}

	public Products findProductById(long id) {
		Optional<Products> optionalProduct = productRepository.findById(id);

		if (optionalProduct.isPresent()) {
			return optionalProduct.get();
		}

		throw new RuntimeException("Product Not available " + id);
	}
	
	public void deleteById(long id) {
		Optional<Products> optionalProduct = productRepository.findById(id);

		if (optionalProduct.isPresent()) {
			productRepository.deleteById(id);
			return;
		}
		
		throw new RuntimeException("Product Not available " + id);
	}
	
//	public List<Order> findAllOrder() {
//		return productRepository.findOrder();
//	}
}
