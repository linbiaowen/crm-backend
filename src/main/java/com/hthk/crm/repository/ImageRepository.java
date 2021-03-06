package com.hthk.crm.repository;

import com.hthk.crm.domain.Image;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Image entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ImageRepository extends MongoRepository<Image, String> {

    @Query("{ image_id : ?0 }")
	Image findByImageId(Long imageId);
}
