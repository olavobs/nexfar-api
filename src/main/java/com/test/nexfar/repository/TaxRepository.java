package com.test.nexfar.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.test.nexfar.entity.Taxes;

@Repository
public interface TaxRepository extends MongoRepository<Taxes, Integer> {

	List<Taxes> findAllByClientId(Integer idCliente);

}
