package com.test.nexfar.service;

import java.util.List;

import com.test.nexfar.entity.SearchProduct;
import com.test.nexfar.exception.ProductNotFoundException;

public interface ISearchService {

	public List<SearchProduct> findProducts(Integer clientId, String name) throws ProductNotFoundException;

}
