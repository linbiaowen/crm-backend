package com.hthk.crm.web.rest;

import com.hthk.crm.domain.GroupMember;
import com.hthk.crm.service.GroupMemberService;
import com.hthk.crm.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.hthk.crm.domain.GroupMember}.
 */
@RestController
@RequestMapping("/api")
public class GroupMemberResource {

    private final Logger log = LoggerFactory.getLogger(GroupMemberResource.class);

    private static final String ENTITY_NAME = "groupMember";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GroupMemberService groupMemberService;

    public GroupMemberResource(GroupMemberService groupMemberService) {
        this.groupMemberService = groupMemberService;
    }

    /**
     * {@code POST  /group-members} : Create a new groupMember.
     *
     * @param groupMember the groupMember to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new groupMember, or with status {@code 400 (Bad Request)} if the groupMember has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/group-members")
    public ResponseEntity<GroupMember> createGroupMember(@Valid @RequestBody GroupMember groupMember) throws URISyntaxException {
        log.debug("REST request to save GroupMember : {}", groupMember);
        if (groupMember.getId() != null) {
            throw new BadRequestAlertException("A new groupMember cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GroupMember result = groupMemberService.save(groupMember);
        return ResponseEntity.created(new URI("/api/group-members/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /group-members} : Updates an existing groupMember.
     *
     * @param groupMember the groupMember to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated groupMember,
     * or with status {@code 400 (Bad Request)} if the groupMember is not valid,
     * or with status {@code 500 (Internal Server Error)} if the groupMember couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/group-members")
    public ResponseEntity<GroupMember> updateGroupMember(@Valid @RequestBody GroupMember groupMember) throws URISyntaxException {
        log.debug("REST request to update GroupMember : {}", groupMember);
        if (groupMember.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GroupMember result = groupMemberService.save(groupMember);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, groupMember.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /group-members} : get all the groupMembers.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of groupMembers in body.
     */
    @GetMapping("/group-members")
    public ResponseEntity<List<GroupMember>> getAllGroupMembers(Pageable pageable) {
        log.debug("REST request to get a page of GroupMembers");
        Page<GroupMember> page = groupMemberService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /group-members/:id} : get the "id" groupMember.
     *
     * @param id the id of the groupMember to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the groupMember, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/group-members/{id}")
    public ResponseEntity<GroupMember> getGroupMember(@PathVariable String id) {
        log.debug("REST request to get GroupMember : {}", id);
        Optional<GroupMember> groupMember = groupMemberService.findOne(id);
        return ResponseUtil.wrapOrNotFound(groupMember);
    }

    /**
     * {@code DELETE  /group-members/:id} : delete the "id" groupMember.
     *
     * @param id the id of the groupMember to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/group-members/{id}")
    public ResponseEntity<Void> deleteGroupMember(@PathVariable String id) {
        log.debug("REST request to delete GroupMember : {}", id);
        groupMemberService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
