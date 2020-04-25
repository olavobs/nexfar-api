package com.test.nexfar.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "taxes")
public class Taxes {

	@Field(name = "clientId")
	private Integer clientId;

	@Field(name = "type")
	private String type;

	@Field(name = "percentage")
	private Integer percentage;

	public Taxes(Integer clientId, String type, Integer percentage) {
		super();
		this.clientId = clientId;
		this.type = type;
		this.percentage = percentage;
	}

	public Taxes() {
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getPercentage() {
		return percentage;
	}

	public void setPercentage(Integer percentage) {
		this.percentage = percentage;
	}

}
