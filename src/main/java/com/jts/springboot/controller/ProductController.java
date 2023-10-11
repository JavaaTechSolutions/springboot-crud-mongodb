package com.jts.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jts.springboot.entity.Products;
import com.jts.springboot.service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/findAll")
	public ResponseEntity<?> findAll() {
		
		List<Products> prods = productService.findAll();
		
//		Map<String, Object> res = new HashMap<>();
//		res.put("produts", prods.getContent());
//		res.put("current-page", prods.getNumber());
//		res.put("total-items", prods.getTotalElements());
//		res.put("total-pages", prods.getTotalPages());
		
		return ResponseEntity.ok().body(prods);
	}
	
	
//	@GetMapping("/findAllOrder")
//	public ResponseEntity<List<Order>> findAllOrder() {
//		return ResponseEntity.ok().body(productService.findAllOrder());
//	}
	
//	@GetMapping("/findAllProductDistinct")
//	public ResponseEntity<List<String>> findAllProductDistinct() {
//		return ResponseEntity.ok().body(productService.findAllProductDistinct());
//	}
	
	@GetMapping("/findById/{id}")
	public ResponseEntity<Products> findById(@PathVariable long id) {
		return ResponseEntity.ok().body(productService.findProductById(id));
	}
	
	@PostMapping("/create")
	public ResponseEntity<Products> createProduct(@RequestBody Products products) {
		return ResponseEntity.ok().body(productService.createProduct(products));
	}
	
	@PutMapping("/update")
	public ResponseEntity<Products> updateProduct(@RequestBody Products products) {
		return ResponseEntity.ok().body(productService.updateProduct(products));
	}
	
	@DeleteMapping("/delete/{id}")
	public HttpStatus deleteProduct(@PathVariable long id) {
		productService.deleteById(id);
		
		return HttpStatus.OK;
	}

}
