package com.hthk.crm.repository;

import com.hthk.crm.domain.GroupMember;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the GroupMember entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GroupMemberRepository extends MongoRepository<GroupMember, String> {
}
