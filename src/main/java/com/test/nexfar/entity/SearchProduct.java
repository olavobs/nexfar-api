package com.test.nexfar.entity;

public class SearchProduct {
	Integer productId;
	String name;
	Double price;
	Double taxes;

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

	public Double getTaxes() {
		return taxes;
	}

	public void setTaxes(Double taxes) {
		this.taxes = taxes;
	}

	public SearchProduct(Integer productId, String name, Double price, Double taxes) {
		super();
		this.productId = productId;
		this.name = name;
		this.price = price;
		this.taxes = taxes;
	}

	public SearchProduct() {
	}
}
