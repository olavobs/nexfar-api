package com.test.nexfar.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.nexfar.entity.Product;
import com.test.nexfar.entity.SearchProduct;
import com.test.nexfar.entity.ProductSellingRestrictions;
import com.test.nexfar.entity.Taxes;
import com.test.nexfar.exception.ProductNotFoundException;
import com.test.nexfar.repository.ProductRepository;
import com.test.nexfar.repository.ProductSellingRestrictionsRepository;
import com.test.nexfar.repository.TaxRepository;

@Service
public class SearchServiceImpl implements ISearchService {

	private final String ICMS = "ICMS";
	private final String IPI = "IPI";

	private ProductRepository productRepository;
	private ProductSellingRestrictionsRepository productSellingRestrictionsRepository;
	private TaxRepository taxRepository;

	@Autowired
	public SearchServiceImpl(ProductRepository productRepository,
			ProductSellingRestrictionsRepository productSellingRestrictionsRepository, TaxRepository taxRepository) {
		this.productRepository = productRepository;
		this.productSellingRestrictionsRepository = productSellingRestrictionsRepository;
		this.taxRepository = taxRepository;
	}

	@Override
	public List<SearchProduct> findProducts(Integer clientId, String name) throws ProductNotFoundException {

		List<Product> products = productRepository.findByName(name);

		if (products.isEmpty()) {
			throw new ProductNotFoundException("Product " + name + "was not found!");
		}

		List<Taxes> taxes = taxRepository.findAllByClientId(clientId);

		List<ProductSellingRestrictions> restrictions = productSellingRestrictionsRepository.findByIdCliente(clientId);

		Integer icms = getTax(taxes, ICMS);
		Integer ipi = getTax(taxes, IPI);

		List<SearchProduct> productResponseList = new ArrayList<SearchProduct>();

		List<Integer> restrictedIds = restrictions.stream().map(r -> r.getIdProduto()).collect(Collectors.toList());

		for (Product product : products) {
			Integer productId = product.getProductId();

			if (!restrictedIds.contains(productId)) {
				productResponseList.add(createProductResponse(icms, ipi, product));
			}
		}

		return productResponseList;
	}

	private SearchProduct createProductResponse(Integer icms, Integer ipi, Product product) {
		SearchProduct productResponse = new SearchProduct();

		Double price = product.getPrice();

		productResponse.setName(product.getName());
		productResponse.setPrice(price);
		productResponse.setTaxes(calculateTax(price, ipi, icms));
		productResponse.setProductId(product.getProductId());

		return productResponse;
	}

	private Integer getTax(List<Taxes> taxes, String type) {
		return taxes.stream().filter(tax -> tax.getType().toUpperCase().equals(type)).findFirst()
				.map(t -> t.getPercentage()).orElse(0);
	}

	private Double calculateTax(Double price, double ipi, double icms) {
		Double value = price + (price * (ipi / 100)) + (price * (icms / 100));
		return (double) Math.round(value * 100) / 100;
	}

}
