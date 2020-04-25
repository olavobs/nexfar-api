package com.test.nexfar.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "productSellingRestrictions")
public class ProductSellingRestrictions {

	@Field(name = "idCliente")
	private Integer idCliente;

	@Field(name = "idProduto")
	private Integer idProduto;

	public ProductSellingRestrictions() {
	}

	public ProductSellingRestrictions(Integer idCliente, Integer idProduto) {
		super();
		this.idCliente = idCliente;
		this.idProduto = idProduto;
	}

	public Integer getIdClient() {
		return idCliente;
	}

	public void setIdClient(Integer idClient) {
		this.idCliente = idClient;
	}

	public Integer getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}
}
