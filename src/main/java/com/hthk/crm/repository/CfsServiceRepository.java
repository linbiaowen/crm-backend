package com.hthk.crm.repository;

import com.hthk.crm.domain.CfsService;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the CfsService entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CfsServiceRepository extends MongoRepository<CfsService, String> {

    @Query("{ service_id : ?0 }")
	CfsService findbyServiceId(String tempServiceId);
}
