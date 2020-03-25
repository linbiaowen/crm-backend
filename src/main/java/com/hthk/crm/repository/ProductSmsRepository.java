package com.hthk.crm.repository;

import com.hthk.crm.domain.ProductSms;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the ProductSms entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProductSmsRepository extends MongoRepository<ProductSms, String> {

    @Query("{ sms_id : ?0 }")
	ProductSms findBySmsId(String tempSmsIds);
}
