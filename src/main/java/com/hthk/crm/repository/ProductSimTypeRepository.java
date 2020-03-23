package com.hthk.crm.repository;

import com.hthk.crm.domain.ProductSimType;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the ProductSimType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProductSimTypeRepository extends MongoRepository<ProductSimType, String> {
}
