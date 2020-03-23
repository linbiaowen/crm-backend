package com.hthk.crm.repository;

import com.hthk.crm.domain.OfferSalesChannel;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the OfferSalesChannel entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OfferSalesChannelRepository extends MongoRepository<OfferSalesChannel, String> {
}
