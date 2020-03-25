package com.hthk.crm.repository;

import com.hthk.crm.domain.ProductMms;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the ProductMms entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProductMmsRepository extends MongoRepository<ProductMms, String> {
}
