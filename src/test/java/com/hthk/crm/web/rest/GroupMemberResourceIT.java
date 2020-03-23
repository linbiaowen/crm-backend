package com.hthk.crm.web.rest;

import com.hthk.crm.CrmwebApp;
import com.hthk.crm.config.TestSecurityConfiguration;
import com.hthk.crm.domain.GroupMember;
import com.hthk.crm.repository.GroupMemberRepository;
import com.hthk.crm.service.GroupMemberService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.hthk.crm.domain.enumeration.GroupRole;
/**
 * Integration tests for the {@link GroupMemberResource} REST controller.
 */
@SpringBootTest(classes = { CrmwebApp.class, TestSecurityConfiguration.class })

@AutoConfigureMockMvc
@WithMockUser
public class GroupMemberResourceIT {

    private static final Long DEFAULT_GROUP_MEMBER_ID = 1L;
    private static final Long UPDATED_GROUP_MEMBER_ID = 2L;

    private static final Long DEFAULT_GROUP_ID = 1L;
    private static final Long UPDATED_GROUP_ID = 2L;

    private static final String DEFAULT_MSISDN = "AAAAAAAAAA";
    private static final String UPDATED_MSISDN = "BBBBBBBBBB";

    private static final GroupRole DEFAULT_GROUP_ROLE = GroupRole.ADMIN;
    private static final GroupRole UPDATED_GROUP_ROLE = GroupRole.MEMBER;

    private static final String DEFAULT_END_REASON_CODE = "AAAAAAAAAA";
    private static final String UPDATED_END_REASON_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_REMARKS = "AAAAAAAAAA";
    private static final String UPDATED_REMARKS = "BBBBBBBBBB";

    private static final Instant DEFAULT_START_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_START_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_END_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_END_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_LOCK_COUNT = 1;
    private static final Integer UPDATED_LOCK_COUNT = 2;

    private static final Instant DEFAULT_CREATED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_LAST_UPDATED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LAST_UPDATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_LAST_UPDATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_LAST_UPDATED_BY = "BBBBBBBBBB";

    private static final String DEFAULT_TENANT_ID = "AAAAAAAAAA";
    private static final String UPDATED_TENANT_ID = "BBBBBBBBBB";

    @Autowired
    private GroupMemberRepository groupMemberRepository;

    @Autowired
    private GroupMemberService groupMemberService;

    @Autowired
    private MockMvc restGroupMemberMockMvc;

    private GroupMember groupMember;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GroupMember createEntity() {
        GroupMember groupMember = new GroupMember()
            .groupMemberId(DEFAULT_GROUP_MEMBER_ID)
            .groupId(DEFAULT_GROUP_ID)
            .msisdn(DEFAULT_MSISDN)
            .groupRole(DEFAULT_GROUP_ROLE)
            .endReasonCode(DEFAULT_END_REASON_CODE)
            .remarks(DEFAULT_REMARKS)
            .startDate(DEFAULT_START_DATE)
            .endDate(DEFAULT_END_DATE)
            .lockCount(DEFAULT_LOCK_COUNT)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdatedDate(DEFAULT_LAST_UPDATED_DATE)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY)
            .tenantId(DEFAULT_TENANT_ID);
        return groupMember;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GroupMember createUpdatedEntity() {
        GroupMember groupMember = new GroupMember()
            .groupMemberId(UPDATED_GROUP_MEMBER_ID)
            .groupId(UPDATED_GROUP_ID)
            .msisdn(UPDATED_MSISDN)
            .groupRole(UPDATED_GROUP_ROLE)
            .endReasonCode(UPDATED_END_REASON_CODE)
            .remarks(UPDATED_REMARKS)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .lockCount(UPDATED_LOCK_COUNT)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);
        return groupMember;
    }

    @BeforeEach
    public void initTest() {
        groupMemberRepository.deleteAll();
        groupMember = createEntity();
    }

    @Test
    public void createGroupMember() throws Exception {
        int databaseSizeBeforeCreate = groupMemberRepository.findAll().size();

        // Create the GroupMember
        restGroupMemberMockMvc.perform(post("/api/group-members").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(groupMember)))
            .andExpect(status().isCreated());

        // Validate the GroupMember in the database
        List<GroupMember> groupMemberList = groupMemberRepository.findAll();
        assertThat(groupMemberList).hasSize(databaseSizeBeforeCreate + 1);
        GroupMember testGroupMember = groupMemberList.get(groupMemberList.size() - 1);
        assertThat(testGroupMember.getGroupMemberId()).isEqualTo(DEFAULT_GROUP_MEMBER_ID);
        assertThat(testGroupMember.getGroupId()).isEqualTo(DEFAULT_GROUP_ID);
        assertThat(testGroupMember.getMsisdn()).isEqualTo(DEFAULT_MSISDN);
        assertThat(testGroupMember.getGroupRole()).isEqualTo(DEFAULT_GROUP_ROLE);
        assertThat(testGroupMember.getEndReasonCode()).isEqualTo(DEFAULT_END_REASON_CODE);
        assertThat(testGroupMember.getRemarks()).isEqualTo(DEFAULT_REMARKS);
        assertThat(testGroupMember.getStartDate()).isEqualTo(DEFAULT_START_DATE);
        assertThat(testGroupMember.getEndDate()).isEqualTo(DEFAULT_END_DATE);
        assertThat(testGroupMember.getLockCount()).isEqualTo(DEFAULT_LOCK_COUNT);
        assertThat(testGroupMember.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testGroupMember.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testGroupMember.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testGroupMember.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
        assertThat(testGroupMember.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
    }

    @Test
    public void createGroupMemberWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = groupMemberRepository.findAll().size();

        // Create the GroupMember with an existing ID
        groupMember.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restGroupMemberMockMvc.perform(post("/api/group-members").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(groupMember)))
            .andExpect(status().isBadRequest());

        // Validate the GroupMember in the database
        List<GroupMember> groupMemberList = groupMemberRepository.findAll();
        assertThat(groupMemberList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkGroupMemberIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = groupMemberRepository.findAll().size();
        // set the field null
        groupMember.setGroupMemberId(null);

        // Create the GroupMember, which fails.

        restGroupMemberMockMvc.perform(post("/api/group-members").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(groupMember)))
            .andExpect(status().isBadRequest());

        List<GroupMember> groupMemberList = groupMemberRepository.findAll();
        assertThat(groupMemberList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkGroupIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = groupMemberRepository.findAll().size();
        // set the field null
        groupMember.setGroupId(null);

        // Create the GroupMember, which fails.

        restGroupMemberMockMvc.perform(post("/api/group-members").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(groupMember)))
            .andExpect(status().isBadRequest());

        List<GroupMember> groupMemberList = groupMemberRepository.findAll();
        assertThat(groupMemberList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkMsisdnIsRequired() throws Exception {
        int databaseSizeBeforeTest = groupMemberRepository.findAll().size();
        // set the field null
        groupMember.setMsisdn(null);

        // Create the GroupMember, which fails.

        restGroupMemberMockMvc.perform(post("/api/group-members").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(groupMember)))
            .andExpect(status().isBadRequest());

        List<GroupMember> groupMemberList = groupMemberRepository.findAll();
        assertThat(groupMemberList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkGroupRoleIsRequired() throws Exception {
        int databaseSizeBeforeTest = groupMemberRepository.findAll().size();
        // set the field null
        groupMember.setGroupRole(null);

        // Create the GroupMember, which fails.

        restGroupMemberMockMvc.perform(post("/api/group-members").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(groupMember)))
            .andExpect(status().isBadRequest());

        List<GroupMember> groupMemberList = groupMemberRepository.findAll();
        assertThat(groupMemberList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = groupMemberRepository.findAll().size();
        // set the field null
        groupMember.setCreatedDate(null);

        // Create the GroupMember, which fails.

        restGroupMemberMockMvc.perform(post("/api/group-members").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(groupMember)))
            .andExpect(status().isBadRequest());

        List<GroupMember> groupMemberList = groupMemberRepository.findAll();
        assertThat(groupMemberList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = groupMemberRepository.findAll().size();
        // set the field null
        groupMember.setCreatedBy(null);

        // Create the GroupMember, which fails.

        restGroupMemberMockMvc.perform(post("/api/group-members").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(groupMember)))
            .andExpect(status().isBadRequest());

        List<GroupMember> groupMemberList = groupMemberRepository.findAll();
        assertThat(groupMemberList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = groupMemberRepository.findAll().size();
        // set the field null
        groupMember.setLastUpdatedDate(null);

        // Create the GroupMember, which fails.

        restGroupMemberMockMvc.perform(post("/api/group-members").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(groupMember)))
            .andExpect(status().isBadRequest());

        List<GroupMember> groupMemberList = groupMemberRepository.findAll();
        assertThat(groupMemberList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = groupMemberRepository.findAll().size();
        // set the field null
        groupMember.setLastUpdatedBy(null);

        // Create the GroupMember, which fails.

        restGroupMemberMockMvc.perform(post("/api/group-members").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(groupMember)))
            .andExpect(status().isBadRequest());

        List<GroupMember> groupMemberList = groupMemberRepository.findAll();
        assertThat(groupMemberList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTenantIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = groupMemberRepository.findAll().size();
        // set the field null
        groupMember.setTenantId(null);

        // Create the GroupMember, which fails.

        restGroupMemberMockMvc.perform(post("/api/group-members").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(groupMember)))
            .andExpect(status().isBadRequest());

        List<GroupMember> groupMemberList = groupMemberRepository.findAll();
        assertThat(groupMemberList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllGroupMembers() throws Exception {
        // Initialize the database
        groupMemberRepository.save(groupMember);

        // Get all the groupMemberList
        restGroupMemberMockMvc.perform(get("/api/group-members?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(groupMember.getId())))
            .andExpect(jsonPath("$.[*].groupMemberId").value(hasItem(DEFAULT_GROUP_MEMBER_ID.intValue())))
            .andExpect(jsonPath("$.[*].groupId").value(hasItem(DEFAULT_GROUP_ID.intValue())))
            .andExpect(jsonPath("$.[*].msisdn").value(hasItem(DEFAULT_MSISDN)))
            .andExpect(jsonPath("$.[*].groupRole").value(hasItem(DEFAULT_GROUP_ROLE.toString())))
            .andExpect(jsonPath("$.[*].endReasonCode").value(hasItem(DEFAULT_END_REASON_CODE)))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS)))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].lockCount").value(hasItem(DEFAULT_LOCK_COUNT)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].lastUpdatedDate").value(hasItem(DEFAULT_LAST_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].tenantId").value(hasItem(DEFAULT_TENANT_ID)));
    }
    
    @Test
    public void getGroupMember() throws Exception {
        // Initialize the database
        groupMemberRepository.save(groupMember);

        // Get the groupMember
        restGroupMemberMockMvc.perform(get("/api/group-members/{id}", groupMember.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(groupMember.getId()))
            .andExpect(jsonPath("$.groupMemberId").value(DEFAULT_GROUP_MEMBER_ID.intValue()))
            .andExpect(jsonPath("$.groupId").value(DEFAULT_GROUP_ID.intValue()))
            .andExpect(jsonPath("$.msisdn").value(DEFAULT_MSISDN))
            .andExpect(jsonPath("$.groupRole").value(DEFAULT_GROUP_ROLE.toString()))
            .andExpect(jsonPath("$.endReasonCode").value(DEFAULT_END_REASON_CODE))
            .andExpect(jsonPath("$.remarks").value(DEFAULT_REMARKS))
            .andExpect(jsonPath("$.startDate").value(DEFAULT_START_DATE.toString()))
            .andExpect(jsonPath("$.endDate").value(DEFAULT_END_DATE.toString()))
            .andExpect(jsonPath("$.lockCount").value(DEFAULT_LOCK_COUNT))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.lastUpdatedDate").value(DEFAULT_LAST_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY))
            .andExpect(jsonPath("$.tenantId").value(DEFAULT_TENANT_ID));
    }

    @Test
    public void getNonExistingGroupMember() throws Exception {
        // Get the groupMember
        restGroupMemberMockMvc.perform(get("/api/group-members/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateGroupMember() throws Exception {
        // Initialize the database
        groupMemberService.save(groupMember);

        int databaseSizeBeforeUpdate = groupMemberRepository.findAll().size();

        // Update the groupMember
        GroupMember updatedGroupMember = groupMemberRepository.findById(groupMember.getId()).get();
        updatedGroupMember
            .groupMemberId(UPDATED_GROUP_MEMBER_ID)
            .groupId(UPDATED_GROUP_ID)
            .msisdn(UPDATED_MSISDN)
            .groupRole(UPDATED_GROUP_ROLE)
            .endReasonCode(UPDATED_END_REASON_CODE)
            .remarks(UPDATED_REMARKS)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .lockCount(UPDATED_LOCK_COUNT)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);

        restGroupMemberMockMvc.perform(put("/api/group-members").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedGroupMember)))
            .andExpect(status().isOk());

        // Validate the GroupMember in the database
        List<GroupMember> groupMemberList = groupMemberRepository.findAll();
        assertThat(groupMemberList).hasSize(databaseSizeBeforeUpdate);
        GroupMember testGroupMember = groupMemberList.get(groupMemberList.size() - 1);
        assertThat(testGroupMember.getGroupMemberId()).isEqualTo(UPDATED_GROUP_MEMBER_ID);
        assertThat(testGroupMember.getGroupId()).isEqualTo(UPDATED_GROUP_ID);
        assertThat(testGroupMember.getMsisdn()).isEqualTo(UPDATED_MSISDN);
        assertThat(testGroupMember.getGroupRole()).isEqualTo(UPDATED_GROUP_ROLE);
        assertThat(testGroupMember.getEndReasonCode()).isEqualTo(UPDATED_END_REASON_CODE);
        assertThat(testGroupMember.getRemarks()).isEqualTo(UPDATED_REMARKS);
        assertThat(testGroupMember.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testGroupMember.getEndDate()).isEqualTo(UPDATED_END_DATE);
        assertThat(testGroupMember.getLockCount()).isEqualTo(UPDATED_LOCK_COUNT);
        assertThat(testGroupMember.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testGroupMember.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testGroupMember.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testGroupMember.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
        assertThat(testGroupMember.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    public void updateNonExistingGroupMember() throws Exception {
        int databaseSizeBeforeUpdate = groupMemberRepository.findAll().size();

        // Create the GroupMember

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGroupMemberMockMvc.perform(put("/api/group-members").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(groupMember)))
            .andExpect(status().isBadRequest());

        // Validate the GroupMember in the database
        List<GroupMember> groupMemberList = groupMemberRepository.findAll();
        assertThat(groupMemberList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteGroupMember() throws Exception {
        // Initialize the database
        groupMemberService.save(groupMember);

        int databaseSizeBeforeDelete = groupMemberRepository.findAll().size();

        // Delete the groupMember
        restGroupMemberMockMvc.perform(delete("/api/group-members/{id}", groupMember.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<GroupMember> groupMemberList = groupMemberRepository.findAll();
        assertThat(groupMemberList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
