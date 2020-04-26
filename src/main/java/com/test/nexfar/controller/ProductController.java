package com.test.nexfar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.nexfar.entity.SearchProduct;
import com.test.nexfar.entity.SearchRequest;
import com.test.nexfar.exception.ProductNotFoundException;
import com.test.nexfar.service.SearchServiceImpl;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/api/product")
public class ProductController {

	private SearchServiceImpl searchService;

	@Autowired
	public ProductController(SearchServiceImpl searchService) {
		this.searchService = searchService;
	}

	@PostMapping("/search")
	@ApiOperation(value = "Calculate the tax for the products using clientId and the name of the product")
	public ResponseEntity<List<SearchProduct>> searchProduct(@RequestHeader("clientId") Integer clientId,
			@RequestBody SearchRequest request) throws ProductNotFoundException {
		List<SearchProduct> productResponse;

		try {
			productResponse = searchService.findProducts(clientId, request.getName());
		} catch (ProductNotFoundException e) {
			return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<SearchProduct>>(productResponse, HttpStatus.OK);

	}
}
