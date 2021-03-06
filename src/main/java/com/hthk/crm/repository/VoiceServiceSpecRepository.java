package com.hthk.crm.repository;

import com.hthk.crm.domain.VoiceServiceSpec;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the VoiceServiceSpec entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VoiceServiceSpecRepository extends MongoRepository<VoiceServiceSpec, String> {

    @Query("{ voice_spec_id : ?0 }")
	VoiceServiceSpec findByVoiceSpecId(String tempVoiceSpecIds);
}
