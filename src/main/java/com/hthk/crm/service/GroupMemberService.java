package com.hthk.crm.service;

import com.hthk.crm.domain.GroupMember;
import com.hthk.crm.repository.GroupMemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link GroupMember}.
 */
@Service
public class GroupMemberService {

    private final Logger log = LoggerFactory.getLogger(GroupMemberService.class);

    private final GroupMemberRepository groupMemberRepository;

    public GroupMemberService(GroupMemberRepository groupMemberRepository) {
        this.groupMemberRepository = groupMemberRepository;
    }

    /**
     * Save a groupMember.
     *
     * @param groupMember the entity to save.
     * @return the persisted entity.
     */
    public GroupMember save(GroupMember groupMember) {
        log.debug("Request to save GroupMember : {}", groupMember);
        return groupMemberRepository.save(groupMember);
    }

    /**
     * Get all the groupMembers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<GroupMember> findAll(Pageable pageable) {
        log.debug("Request to get all GroupMembers");
        return groupMemberRepository.findAll(pageable);
    }

    /**
     * Get one groupMember by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<GroupMember> findOne(String id) {
        log.debug("Request to get GroupMember : {}", id);
        return groupMemberRepository.findById(id);
    }

    /**
     * Delete the groupMember by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete GroupMember : {}", id);
        groupMemberRepository.deleteById(id);
    }
}
