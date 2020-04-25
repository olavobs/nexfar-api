package com.test.nexfar.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.test.nexfar.entity.ProductSellingRestrictions;

@Repository
public interface ProductSellingRestrictionsRepository extends MongoRepository<ProductSellingRestrictions, Integer> {

	List<ProductSellingRestrictions> findByIdCliente(Integer idClient);
}
