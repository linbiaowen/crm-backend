package com.hthk.crm.repository;

import com.hthk.crm.domain.OrderProcessConfig;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the OrderProcessConfig entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrderProcessConfigRepository extends MongoRepository<OrderProcessConfig, String> {
}
