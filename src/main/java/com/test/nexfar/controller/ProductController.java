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

import com.test.nexfar.entity.ProductResponse;
import com.test.nexfar.entity.Request;
import com.test.nexfar.exception.ProductNotFoundException;
import com.test.nexfar.service.SearchService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/api/product")
public class ProductController {

	private SearchService searchService;

	@Autowired
	public ProductController(SearchService searchService) {
		this.searchService = searchService;
	}

	@PostMapping("/search")
	@ApiOperation(value = "Calculate the tax for the products using clientId and the name of the product")
	public ResponseEntity<List<ProductResponse>> searchProduct(@RequestHeader("clientId") Integer clientId,
			@RequestBody Request request) throws ProductNotFoundException {
		List<ProductResponse> productResponse;

		try {
			productResponse = searchService.findProducts(clientId, request.getName());
		} catch (ProductNotFoundException e) {
			return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<ProductResponse>>(productResponse, HttpStatus.OK);

	}
}
