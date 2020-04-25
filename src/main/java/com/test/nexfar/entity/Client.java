package com.test.nexfar.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "client")
public class Client {

	@Id
	@Field(name = "idClient")
	private Integer idClient;

	@Field(name = "CNPJ")
	private String cnpj;

	@Field(name = "Nome")
	private String nome;

	public Client(Integer idClient, String cnpj, String nome) {
		super();
		this.idClient = idClient;
		this.cnpj = cnpj;
		this.nome = nome;
	}

	public Client() {
	}

	public Integer getIdClient() {
		return idClient;
	}

	public void setIdClient(Integer idClient) {
		this.idClient = idClient;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
