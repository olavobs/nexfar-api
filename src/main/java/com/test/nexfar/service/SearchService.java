package com.test.nexfar.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.nexfar.entity.Product;
import com.test.nexfar.entity.ProductResponse;
import com.test.nexfar.entity.ProductSellingRestrictions;
import com.test.nexfar.entity.Taxes;
import com.test.nexfar.exception.ProductNotFoundException;
import com.test.nexfar.repository.ProductRepository;
import com.test.nexfar.repository.ProductSellingRestrictionsRepository;
import com.test.nexfar.repository.TaxRepository;

@Service
public class SearchService {

	private final String ICMS = "ICMS";
	private final String IPI = "IPI";

	private ProductRepository productRepository;
	private ProductSellingRestrictionsRepository productSellingRestrictionsRepository;
	private TaxRepository taxRepository;

	@Autowired
	public SearchService(ProductRepository productRepository,
			ProductSellingRestrictionsRepository productSellingRestrictionsRepository, TaxRepository taxRepository) {
		this.productRepository = productRepository;
		this.productSellingRestrictionsRepository = productSellingRestrictionsRepository;
		this.taxRepository = taxRepository;
	}

	public List<ProductResponse> findProducts(Integer clientId, String name) throws ProductNotFoundException {

		List<Product> products = productRepository.findByName(name);

		if (products.isEmpty()) {
			throw new ProductNotFoundException("Product " + name + "was not found!");
		}

		List<Taxes> taxes = taxRepository.findAllByClientId(clientId);

		List<ProductSellingRestrictions> restrictions = productSellingRestrictionsRepository.findByIdCliente(clientId);

		Integer icms = getTax(taxes, ICMS);
		Integer ipi = getTax(taxes, IPI);

		List<ProductResponse> productResponseList = new ArrayList<ProductResponse>();
		List<Integer> restrictedIds = restrictions.stream().map(r -> r.getIdProduto()).collect(Collectors.toList());

		for (Product product : products) {
			Integer productId = product.getProductId();

			if (!restrictedIds.contains(productId)) {
				productResponseList.add(createProductResponse(icms, ipi, product));
			}
		}

		return productResponseList;
	}

	private ProductResponse createProductResponse(Integer icms, Integer ipi, Product product) {
		ProductResponse productResponse = new ProductResponse();

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
		return price + (price * (ipi / 100)) + (price * (icms / 100));
	}

}
