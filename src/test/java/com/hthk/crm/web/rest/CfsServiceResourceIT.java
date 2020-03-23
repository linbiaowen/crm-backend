package com.hthk.crm.web.rest;

import com.hthk.crm.CrmwebApp;
import com.hthk.crm.config.TestSecurityConfiguration;
import com.hthk.crm.domain.CfsService;
import com.hthk.crm.repository.CfsServiceRepository;
import com.hthk.crm.service.CfsServiceService;

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
 * Integration tests for the {@link CfsServiceResource} REST controller.
 */
@SpringBootTest(classes = { CrmwebApp.class, TestSecurityConfiguration.class })

@AutoConfigureMockMvc
@WithMockUser
public class CfsServiceResourceIT {

    private static final String DEFAULT_SERVICE_ID = "AAAAAAAAAA";
    private static final String UPDATED_SERVICE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_SERVICE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SERVICE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TEMP_VOICE_SPEC_IDS = "AAAAAAAAAA";
    private static final String UPDATED_TEMP_VOICE_SPEC_IDS = "BBBBBBBBBB";

    private static final String DEFAULT_TEMP_DATA_SPEC_IDS = "AAAAAAAAAA";
    private static final String UPDATED_TEMP_DATA_SPEC_IDS = "BBBBBBBBBB";

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
    private CfsServiceRepository cfsServiceRepository;

    @Autowired
    private CfsServiceService cfsServiceService;

    @Autowired
    private MockMvc restCfsServiceMockMvc;

    private CfsService cfsService;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CfsService createEntity() {
        CfsService cfsService = new CfsService()
            .serviceId(DEFAULT_SERVICE_ID)
            .serviceName(DEFAULT_SERVICE_NAME)
            .tempVoiceSpecIds(DEFAULT_TEMP_VOICE_SPEC_IDS)
            .tempDataSpecIds(DEFAULT_TEMP_DATA_SPEC_IDS)
            .lockCount(DEFAULT_LOCK_COUNT)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdatedDate(DEFAULT_LAST_UPDATED_DATE)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY)
            .tenantId(DEFAULT_TENANT_ID);
        return cfsService;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CfsService createUpdatedEntity() {
        CfsService cfsService = new CfsService()
            .serviceId(UPDATED_SERVICE_ID)
            .serviceName(UPDATED_SERVICE_NAME)
            .tempVoiceSpecIds(UPDATED_TEMP_VOICE_SPEC_IDS)
            .tempDataSpecIds(UPDATED_TEMP_DATA_SPEC_IDS)
            .lockCount(UPDATED_LOCK_COUNT)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);
        return cfsService;
    }

    @BeforeEach
    public void initTest() {
        cfsServiceRepository.deleteAll();
        cfsService = createEntity();
    }

    @Test
    public void createCfsService() throws Exception {
        int databaseSizeBeforeCreate = cfsServiceRepository.findAll().size();

        // Create the CfsService
        restCfsServiceMockMvc.perform(post("/api/cfs-services").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cfsService)))
            .andExpect(status().isCreated());

        // Validate the CfsService in the database
        List<CfsService> cfsServiceList = cfsServiceRepository.findAll();
        assertThat(cfsServiceList).hasSize(databaseSizeBeforeCreate + 1);
        CfsService testCfsService = cfsServiceList.get(cfsServiceList.size() - 1);
        assertThat(testCfsService.getServiceId()).isEqualTo(DEFAULT_SERVICE_ID);
        assertThat(testCfsService.getServiceName()).isEqualTo(DEFAULT_SERVICE_NAME);
        assertThat(testCfsService.getTempVoiceSpecIds()).isEqualTo(DEFAULT_TEMP_VOICE_SPEC_IDS);
        assertThat(testCfsService.getTempDataSpecIds()).isEqualTo(DEFAULT_TEMP_DATA_SPEC_IDS);
        assertThat(testCfsService.getLockCount()).isEqualTo(DEFAULT_LOCK_COUNT);
        assertThat(testCfsService.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testCfsService.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testCfsService.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testCfsService.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
        assertThat(testCfsService.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
    }

    @Test
    public void createCfsServiceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cfsServiceRepository.findAll().size();

        // Create the CfsService with an existing ID
        cfsService.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restCfsServiceMockMvc.perform(post("/api/cfs-services").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cfsService)))
            .andExpect(status().isBadRequest());

        // Validate the CfsService in the database
        List<CfsService> cfsServiceList = cfsServiceRepository.findAll();
        assertThat(cfsServiceList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkServiceIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = cfsServiceRepository.findAll().size();
        // set the field null
        cfsService.setServiceId(null);

        // Create the CfsService, which fails.

        restCfsServiceMockMvc.perform(post("/api/cfs-services").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cfsService)))
            .andExpect(status().isBadRequest());

        List<CfsService> cfsServiceList = cfsServiceRepository.findAll();
        assertThat(cfsServiceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkServiceNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = cfsServiceRepository.findAll().size();
        // set the field null
        cfsService.setServiceName(null);

        // Create the CfsService, which fails.

        restCfsServiceMockMvc.perform(post("/api/cfs-services").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cfsService)))
            .andExpect(status().isBadRequest());

        List<CfsService> cfsServiceList = cfsServiceRepository.findAll();
        assertThat(cfsServiceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = cfsServiceRepository.findAll().size();
        // set the field null
        cfsService.setCreatedDate(null);

        // Create the CfsService, which fails.

        restCfsServiceMockMvc.perform(post("/api/cfs-services").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cfsService)))
            .andExpect(status().isBadRequest());

        List<CfsService> cfsServiceList = cfsServiceRepository.findAll();
        assertThat(cfsServiceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = cfsServiceRepository.findAll().size();
        // set the field null
        cfsService.setCreatedBy(null);

        // Create the CfsService, which fails.

        restCfsServiceMockMvc.perform(post("/api/cfs-services").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cfsService)))
            .andExpect(status().isBadRequest());

        List<CfsService> cfsServiceList = cfsServiceRepository.findAll();
        assertThat(cfsServiceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = cfsServiceRepository.findAll().size();
        // set the field null
        cfsService.setLastUpdatedDate(null);

        // Create the CfsService, which fails.

        restCfsServiceMockMvc.perform(post("/api/cfs-services").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cfsService)))
            .andExpect(status().isBadRequest());

        List<CfsService> cfsServiceList = cfsServiceRepository.findAll();
        assertThat(cfsServiceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = cfsServiceRepository.findAll().size();
        // set the field null
        cfsService.setLastUpdatedBy(null);

        // Create the CfsService, which fails.

        restCfsServiceMockMvc.perform(post("/api/cfs-services").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cfsService)))
            .andExpect(status().isBadRequest());

        List<CfsService> cfsServiceList = cfsServiceRepository.findAll();
        assertThat(cfsServiceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTenantIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = cfsServiceRepository.findAll().size();
        // set the field null
        cfsService.setTenantId(null);

        // Create the CfsService, which fails.

        restCfsServiceMockMvc.perform(post("/api/cfs-services").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cfsService)))
            .andExpect(status().isBadRequest());

        List<CfsService> cfsServiceList = cfsServiceRepository.findAll();
        assertThat(cfsServiceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllCfsServices() throws Exception {
        // Initialize the database
        cfsServiceRepository.save(cfsService);

        // Get all the cfsServiceList
        restCfsServiceMockMvc.perform(get("/api/cfs-services?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cfsService.getId())))
            .andExpect(jsonPath("$.[*].serviceId").value(hasItem(DEFAULT_SERVICE_ID)))
            .andExpect(jsonPath("$.[*].serviceName").value(hasItem(DEFAULT_SERVICE_NAME)))
            .andExpect(jsonPath("$.[*].tempVoiceSpecIds").value(hasItem(DEFAULT_TEMP_VOICE_SPEC_IDS)))
            .andExpect(jsonPath("$.[*].tempDataSpecIds").value(hasItem(DEFAULT_TEMP_DATA_SPEC_IDS)))
            .andExpect(jsonPath("$.[*].lockCount").value(hasItem(DEFAULT_LOCK_COUNT)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].lastUpdatedDate").value(hasItem(DEFAULT_LAST_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].tenantId").value(hasItem(DEFAULT_TENANT_ID)));
    }
    
    @Test
    public void getCfsService() throws Exception {
        // Initialize the database
        cfsServiceRepository.save(cfsService);

        // Get the cfsService
        restCfsServiceMockMvc.perform(get("/api/cfs-services/{id}", cfsService.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(cfsService.getId()))
            .andExpect(jsonPath("$.serviceId").value(DEFAULT_SERVICE_ID))
            .andExpect(jsonPath("$.serviceName").value(DEFAULT_SERVICE_NAME))
            .andExpect(jsonPath("$.tempVoiceSpecIds").value(DEFAULT_TEMP_VOICE_SPEC_IDS))
            .andExpect(jsonPath("$.tempDataSpecIds").value(DEFAULT_TEMP_DATA_SPEC_IDS))
            .andExpect(jsonPath("$.lockCount").value(DEFAULT_LOCK_COUNT))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.lastUpdatedDate").value(DEFAULT_LAST_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY))
            .andExpect(jsonPath("$.tenantId").value(DEFAULT_TENANT_ID));
    }

    @Test
    public void getNonExistingCfsService() throws Exception {
        // Get the cfsService
        restCfsServiceMockMvc.perform(get("/api/cfs-services/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateCfsService() throws Exception {
        // Initialize the database
        cfsServiceService.save(cfsService);

        int databaseSizeBeforeUpdate = cfsServiceRepository.findAll().size();

        // Update the cfsService
        CfsService updatedCfsService = cfsServiceRepository.findById(cfsService.getId()).get();
        updatedCfsService
            .serviceId(UPDATED_SERVICE_ID)
            .serviceName(UPDATED_SERVICE_NAME)
            .tempVoiceSpecIds(UPDATED_TEMP_VOICE_SPEC_IDS)
            .tempDataSpecIds(UPDATED_TEMP_DATA_SPEC_IDS)
            .lockCount(UPDATED_LOCK_COUNT)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);

        restCfsServiceMockMvc.perform(put("/api/cfs-services").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedCfsService)))
            .andExpect(status().isOk());

        // Validate the CfsService in the database
        List<CfsService> cfsServiceList = cfsServiceRepository.findAll();
        assertThat(cfsServiceList).hasSize(databaseSizeBeforeUpdate);
        CfsService testCfsService = cfsServiceList.get(cfsServiceList.size() - 1);
        assertThat(testCfsService.getServiceId()).isEqualTo(UPDATED_SERVICE_ID);
        assertThat(testCfsService.getServiceName()).isEqualTo(UPDATED_SERVICE_NAME);
        assertThat(testCfsService.getTempVoiceSpecIds()).isEqualTo(UPDATED_TEMP_VOICE_SPEC_IDS);
        assertThat(testCfsService.getTempDataSpecIds()).isEqualTo(UPDATED_TEMP_DATA_SPEC_IDS);
        assertThat(testCfsService.getLockCount()).isEqualTo(UPDATED_LOCK_COUNT);
        assertThat(testCfsService.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testCfsService.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testCfsService.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testCfsService.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
        assertThat(testCfsService.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    public void updateNonExistingCfsService() throws Exception {
        int databaseSizeBeforeUpdate = cfsServiceRepository.findAll().size();

        // Create the CfsService

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCfsServiceMockMvc.perform(put("/api/cfs-services").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cfsService)))
            .andExpect(status().isBadRequest());

        // Validate the CfsService in the database
        List<CfsService> cfsServiceList = cfsServiceRepository.findAll();
        assertThat(cfsServiceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteCfsService() throws Exception {
        // Initialize the database
        cfsServiceService.save(cfsService);

        int databaseSizeBeforeDelete = cfsServiceRepository.findAll().size();

        // Delete the cfsService
        restCfsServiceMockMvc.perform(delete("/api/cfs-services/{id}", cfsService.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CfsService> cfsServiceList = cfsServiceRepository.findAll();
        assertThat(cfsServiceList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
