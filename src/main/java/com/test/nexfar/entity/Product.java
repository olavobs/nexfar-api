package com.test.nexfar.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "product")
public class Product {

	@Field(name = "productId")
	private Integer productId;

	@Field(name = "name")
	private String name;

	@Field(name = "price")
	private Double price;

	public Product(Integer productId, String name, Double price) {
		super();
		this.productId = productId;
		this.name = name;
		this.price = price;
	}

	public Product() {
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
}
