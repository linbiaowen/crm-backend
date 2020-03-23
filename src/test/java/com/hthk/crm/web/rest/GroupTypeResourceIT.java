package com.hthk.crm.web.rest;

import com.hthk.crm.CrmwebApp;
import com.hthk.crm.config.TestSecurityConfiguration;
import com.hthk.crm.domain.GroupType;
import com.hthk.crm.repository.GroupTypeRepository;
import com.hthk.crm.service.GroupTypeService;

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

/**
 * Integration tests for the {@link GroupTypeResource} REST controller.
 */
@SpringBootTest(classes = { CrmwebApp.class, TestSecurityConfiguration.class })

@AutoConfigureMockMvc
@WithMockUser
public class GroupTypeResourceIT {

    private static final Long DEFAULT_GROUP_TYPE_ID = 1L;
    private static final Long UPDATED_GROUP_TYPE_ID = 2L;

    private static final String DEFAULT_GROUP_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_GROUP_TYPE = "BBBBBBBBBB";

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
    private GroupTypeRepository groupTypeRepository;

    @Autowired
    private GroupTypeService groupTypeService;

    @Autowired
    private MockMvc restGroupTypeMockMvc;

    private GroupType groupType;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GroupType createEntity() {
        GroupType groupType = new GroupType()
            .groupTypeId(DEFAULT_GROUP_TYPE_ID)
            .groupType(DEFAULT_GROUP_TYPE)
            .startDate(DEFAULT_START_DATE)
            .endDate(DEFAULT_END_DATE)
            .lockCount(DEFAULT_LOCK_COUNT)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdatedDate(DEFAULT_LAST_UPDATED_DATE)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY)
            .tenantId(DEFAULT_TENANT_ID);
        return groupType;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GroupType createUpdatedEntity() {
        GroupType groupType = new GroupType()
            .groupTypeId(UPDATED_GROUP_TYPE_ID)
            .groupType(UPDATED_GROUP_TYPE)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .lockCount(UPDATED_LOCK_COUNT)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);
        return groupType;
    }

    @BeforeEach
    public void initTest() {
        groupTypeRepository.deleteAll();
        groupType = createEntity();
    }

    @Test
    public void createGroupType() throws Exception {
        int databaseSizeBeforeCreate = groupTypeRepository.findAll().size();

        // Create the GroupType
        restGroupTypeMockMvc.perform(post("/api/group-types").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(groupType)))
            .andExpect(status().isCreated());

        // Validate the GroupType in the database
        List<GroupType> groupTypeList = groupTypeRepository.findAll();
        assertThat(groupTypeList).hasSize(databaseSizeBeforeCreate + 1);
        GroupType testGroupType = groupTypeList.get(groupTypeList.size() - 1);
        assertThat(testGroupType.getGroupTypeId()).isEqualTo(DEFAULT_GROUP_TYPE_ID);
        assertThat(testGroupType.getGroupType()).isEqualTo(DEFAULT_GROUP_TYPE);
        assertThat(testGroupType.getStartDate()).isEqualTo(DEFAULT_START_DATE);
        assertThat(testGroupType.getEndDate()).isEqualTo(DEFAULT_END_DATE);
        assertThat(testGroupType.getLockCount()).isEqualTo(DEFAULT_LOCK_COUNT);
        assertThat(testGroupType.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testGroupType.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testGroupType.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testGroupType.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
        assertThat(testGroupType.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
    }

    @Test
    public void createGroupTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = groupTypeRepository.findAll().size();

        // Create the GroupType with an existing ID
        groupType.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restGroupTypeMockMvc.perform(post("/api/group-types").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(groupType)))
            .andExpect(status().isBadRequest());

        // Validate the GroupType in the database
        List<GroupType> groupTypeList = groupTypeRepository.findAll();
        assertThat(groupTypeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkGroupTypeIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = groupTypeRepository.findAll().size();
        // set the field null
        groupType.setGroupTypeId(null);

        // Create the GroupType, which fails.

        restGroupTypeMockMvc.perform(post("/api/group-types").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(groupType)))
            .andExpect(status().isBadRequest());

        List<GroupType> groupTypeList = groupTypeRepository.findAll();
        assertThat(groupTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkGroupTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = groupTypeRepository.findAll().size();
        // set the field null
        groupType.setGroupType(null);

        // Create the GroupType, which fails.

        restGroupTypeMockMvc.perform(post("/api/group-types").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(groupType)))
            .andExpect(status().isBadRequest());

        List<GroupType> groupTypeList = groupTypeRepository.findAll();
        assertThat(groupTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = groupTypeRepository.findAll().size();
        // set the field null
        groupType.setCreatedDate(null);

        // Create the GroupType, which fails.

        restGroupTypeMockMvc.perform(post("/api/group-types").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(groupType)))
            .andExpect(status().isBadRequest());

        List<GroupType> groupTypeList = groupTypeRepository.findAll();
        assertThat(groupTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = groupTypeRepository.findAll().size();
        // set the field null
        groupType.setCreatedBy(null);

        // Create the GroupType, which fails.

        restGroupTypeMockMvc.perform(post("/api/group-types").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(groupType)))
            .andExpect(status().isBadRequest());

        List<GroupType> groupTypeList = groupTypeRepository.findAll();
        assertThat(groupTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = groupTypeRepository.findAll().size();
        // set the field null
        groupType.setLastUpdatedDate(null);

        // Create the GroupType, which fails.

        restGroupTypeMockMvc.perform(post("/api/group-types").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(groupType)))
            .andExpect(status().isBadRequest());

        List<GroupType> groupTypeList = groupTypeRepository.findAll();
        assertThat(groupTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = groupTypeRepository.findAll().size();
        // set the field null
        groupType.setLastUpdatedBy(null);

        // Create the GroupType, which fails.

        restGroupTypeMockMvc.perform(post("/api/group-types").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(groupType)))
            .andExpect(status().isBadRequest());

        List<GroupType> groupTypeList = groupTypeRepository.findAll();
        assertThat(groupTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTenantIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = groupTypeRepository.findAll().size();
        // set the field null
        groupType.setTenantId(null);

        // Create the GroupType, which fails.

        restGroupTypeMockMvc.perform(post("/api/group-types").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(groupType)))
            .andExpect(status().isBadRequest());

        List<GroupType> groupTypeList = groupTypeRepository.findAll();
        assertThat(groupTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllGroupTypes() throws Exception {
        // Initialize the database
        groupTypeRepository.save(groupType);

        // Get all the groupTypeList
        restGroupTypeMockMvc.perform(get("/api/group-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(groupType.getId())))
            .andExpect(jsonPath("$.[*].groupTypeId").value(hasItem(DEFAULT_GROUP_TYPE_ID.intValue())))
            .andExpect(jsonPath("$.[*].groupType").value(hasItem(DEFAULT_GROUP_TYPE)))
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
    public void getGroupType() throws Exception {
        // Initialize the database
        groupTypeRepository.save(groupType);

        // Get the groupType
        restGroupTypeMockMvc.perform(get("/api/group-types/{id}", groupType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(groupType.getId()))
            .andExpect(jsonPath("$.groupTypeId").value(DEFAULT_GROUP_TYPE_ID.intValue()))
            .andExpect(jsonPath("$.groupType").value(DEFAULT_GROUP_TYPE))
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
    public void getNonExistingGroupType() throws Exception {
        // Get the groupType
        restGroupTypeMockMvc.perform(get("/api/group-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateGroupType() throws Exception {
        // Initialize the database
        groupTypeService.save(groupType);

        int databaseSizeBeforeUpdate = groupTypeRepository.findAll().size();

        // Update the groupType
        GroupType updatedGroupType = groupTypeRepository.findById(groupType.getId()).get();
        updatedGroupType
            .groupTypeId(UPDATED_GROUP_TYPE_ID)
            .groupType(UPDATED_GROUP_TYPE)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .lockCount(UPDATED_LOCK_COUNT)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);

        restGroupTypeMockMvc.perform(put("/api/group-types").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedGroupType)))
            .andExpect(status().isOk());

        // Validate the GroupType in the database
        List<GroupType> groupTypeList = groupTypeRepository.findAll();
        assertThat(groupTypeList).hasSize(databaseSizeBeforeUpdate);
        GroupType testGroupType = groupTypeList.get(groupTypeList.size() - 1);
        assertThat(testGroupType.getGroupTypeId()).isEqualTo(UPDATED_GROUP_TYPE_ID);
        assertThat(testGroupType.getGroupType()).isEqualTo(UPDATED_GROUP_TYPE);
        assertThat(testGroupType.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testGroupType.getEndDate()).isEqualTo(UPDATED_END_DATE);
        assertThat(testGroupType.getLockCount()).isEqualTo(UPDATED_LOCK_COUNT);
        assertThat(testGroupType.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testGroupType.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testGroupType.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testGroupType.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
        assertThat(testGroupType.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    public void updateNonExistingGroupType() throws Exception {
        int databaseSizeBeforeUpdate = groupTypeRepository.findAll().size();

        // Create the GroupType

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGroupTypeMockMvc.perform(put("/api/group-types").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(groupType)))
            .andExpect(status().isBadRequest());

        // Validate the GroupType in the database
        List<GroupType> groupTypeList = groupTypeRepository.findAll();
        assertThat(groupTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteGroupType() throws Exception {
        // Initialize the database
        groupTypeService.save(groupType);

        int databaseSizeBeforeDelete = groupTypeRepository.findAll().size();

        // Delete the groupType
        restGroupTypeMockMvc.perform(delete("/api/group-types/{id}", groupType.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<GroupType> groupTypeList = groupTypeRepository.findAll();
        assertThat(groupTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
