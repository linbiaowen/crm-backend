package com.hthk.crm.web.rest;

import com.hthk.crm.CrmwebApp;
import com.hthk.crm.config.TestSecurityConfiguration;
import com.hthk.crm.domain.SubsItemDelivery;
import com.hthk.crm.repository.SubsItemDeliveryRepository;
import com.hthk.crm.service.SubsItemDeliveryService;

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

import com.hthk.crm.domain.enumeration.DeliverOptions;
/**
 * Integration tests for the {@link SubsItemDeliveryResource} REST controller.
 */
@SpringBootTest(classes = { CrmwebApp.class, TestSecurityConfiguration.class })

@AutoConfigureMockMvc
@WithMockUser
public class SubsItemDeliveryResourceIT {

    private static final Long DEFAULT_DELIVERY_ID = 1L;
    private static final Long UPDATED_DELIVERY_ID = 2L;

    private static final String DEFAULT_DELIVERY_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_DELIVERY_STATUS = "BBBBBBBBBB";

    private static final DeliverOptions DEFAULT_DELIVERY_OPTION = DeliverOptions.EFLOCKER;
    private static final DeliverOptions UPDATED_DELIVERY_OPTION = DeliverOptions.POST;

    private static final String DEFAULT_TEMP_EF_LOCKER_CODE = "AAAAAAAAAA";
    private static final String UPDATED_TEMP_EF_LOCKER_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_TEMP_ADDRESS_ID = "AAAAAAAAAA";
    private static final String UPDATED_TEMP_ADDRESS_ID = "BBBBBBBBBB";

    private static final String DEFAULT_DELIVERY_REF_CODE = "AAAAAAAAAA";
    private static final String UPDATED_DELIVERY_REF_CODE = "BBBBBBBBBB";

    private static final Instant DEFAULT_FILE_GENERATION_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_FILE_GENERATION_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_FILE_RECEIVED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_FILE_RECEIVED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_DELIVERY_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DELIVERY_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_REMARKS = "AAAAAAAAAA";
    private static final String UPDATED_REMARKS = "BBBBBBBBBB";

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
    private SubsItemDeliveryRepository subsItemDeliveryRepository;

    @Autowired
    private SubsItemDeliveryService subsItemDeliveryService;

    @Autowired
    private MockMvc restSubsItemDeliveryMockMvc;

    private SubsItemDelivery subsItemDelivery;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SubsItemDelivery createEntity() {
        SubsItemDelivery subsItemDelivery = new SubsItemDelivery()
            .deliveryId(DEFAULT_DELIVERY_ID)
            .deliveryStatus(DEFAULT_DELIVERY_STATUS)
            .deliveryOption(DEFAULT_DELIVERY_OPTION)
            .tempEfLockerCode(DEFAULT_TEMP_EF_LOCKER_CODE)
            .tempAddressId(DEFAULT_TEMP_ADDRESS_ID)
            .deliveryRefCode(DEFAULT_DELIVERY_REF_CODE)
            .fileGenerationDate(DEFAULT_FILE_GENERATION_DATE)
            .fileReceivedDate(DEFAULT_FILE_RECEIVED_DATE)
            .deliveryDate(DEFAULT_DELIVERY_DATE)
            .remarks(DEFAULT_REMARKS)
            .lockCount(DEFAULT_LOCK_COUNT)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdatedDate(DEFAULT_LAST_UPDATED_DATE)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY)
            .tenantId(DEFAULT_TENANT_ID);
        return subsItemDelivery;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SubsItemDelivery createUpdatedEntity() {
        SubsItemDelivery subsItemDelivery = new SubsItemDelivery()
            .deliveryId(UPDATED_DELIVERY_ID)
            .deliveryStatus(UPDATED_DELIVERY_STATUS)
            .deliveryOption(UPDATED_DELIVERY_OPTION)
            .tempEfLockerCode(UPDATED_TEMP_EF_LOCKER_CODE)
            .tempAddressId(UPDATED_TEMP_ADDRESS_ID)
            .deliveryRefCode(UPDATED_DELIVERY_REF_CODE)
            .fileGenerationDate(UPDATED_FILE_GENERATION_DATE)
            .fileReceivedDate(UPDATED_FILE_RECEIVED_DATE)
            .deliveryDate(UPDATED_DELIVERY_DATE)
            .remarks(UPDATED_REMARKS)
            .lockCount(UPDATED_LOCK_COUNT)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);
        return subsItemDelivery;
    }

    @BeforeEach
    public void initTest() {
        subsItemDeliveryRepository.deleteAll();
        subsItemDelivery = createEntity();
    }

    @Test
    public void createSubsItemDelivery() throws Exception {
        int databaseSizeBeforeCreate = subsItemDeliveryRepository.findAll().size();

        // Create the SubsItemDelivery
        restSubsItemDeliveryMockMvc.perform(post("/api/subs-item-deliveries").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subsItemDelivery)))
            .andExpect(status().isCreated());

        // Validate the SubsItemDelivery in the database
        List<SubsItemDelivery> subsItemDeliveryList = subsItemDeliveryRepository.findAll();
        assertThat(subsItemDeliveryList).hasSize(databaseSizeBeforeCreate + 1);
        SubsItemDelivery testSubsItemDelivery = subsItemDeliveryList.get(subsItemDeliveryList.size() - 1);
        assertThat(testSubsItemDelivery.getDeliveryId()).isEqualTo(DEFAULT_DELIVERY_ID);
        assertThat(testSubsItemDelivery.getDeliveryStatus()).isEqualTo(DEFAULT_DELIVERY_STATUS);
        assertThat(testSubsItemDelivery.getDeliveryOption()).isEqualTo(DEFAULT_DELIVERY_OPTION);
        assertThat(testSubsItemDelivery.getTempEfLockerCode()).isEqualTo(DEFAULT_TEMP_EF_LOCKER_CODE);
        assertThat(testSubsItemDelivery.getTempAddressId()).isEqualTo(DEFAULT_TEMP_ADDRESS_ID);
        assertThat(testSubsItemDelivery.getDeliveryRefCode()).isEqualTo(DEFAULT_DELIVERY_REF_CODE);
        assertThat(testSubsItemDelivery.getFileGenerationDate()).isEqualTo(DEFAULT_FILE_GENERATION_DATE);
        assertThat(testSubsItemDelivery.getFileReceivedDate()).isEqualTo(DEFAULT_FILE_RECEIVED_DATE);
        assertThat(testSubsItemDelivery.getDeliveryDate()).isEqualTo(DEFAULT_DELIVERY_DATE);
        assertThat(testSubsItemDelivery.getRemarks()).isEqualTo(DEFAULT_REMARKS);
        assertThat(testSubsItemDelivery.getLockCount()).isEqualTo(DEFAULT_LOCK_COUNT);
        assertThat(testSubsItemDelivery.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testSubsItemDelivery.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testSubsItemDelivery.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testSubsItemDelivery.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
        assertThat(testSubsItemDelivery.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
    }

    @Test
    public void createSubsItemDeliveryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = subsItemDeliveryRepository.findAll().size();

        // Create the SubsItemDelivery with an existing ID
        subsItemDelivery.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restSubsItemDeliveryMockMvc.perform(post("/api/subs-item-deliveries").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subsItemDelivery)))
            .andExpect(status().isBadRequest());

        // Validate the SubsItemDelivery in the database
        List<SubsItemDelivery> subsItemDeliveryList = subsItemDeliveryRepository.findAll();
        assertThat(subsItemDeliveryList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkDeliveryIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = subsItemDeliveryRepository.findAll().size();
        // set the field null
        subsItemDelivery.setDeliveryId(null);

        // Create the SubsItemDelivery, which fails.

        restSubsItemDeliveryMockMvc.perform(post("/api/subs-item-deliveries").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subsItemDelivery)))
            .andExpect(status().isBadRequest());

        List<SubsItemDelivery> subsItemDeliveryList = subsItemDeliveryRepository.findAll();
        assertThat(subsItemDeliveryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkDeliveryStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = subsItemDeliveryRepository.findAll().size();
        // set the field null
        subsItemDelivery.setDeliveryStatus(null);

        // Create the SubsItemDelivery, which fails.

        restSubsItemDeliveryMockMvc.perform(post("/api/subs-item-deliveries").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subsItemDelivery)))
            .andExpect(status().isBadRequest());

        List<SubsItemDelivery> subsItemDeliveryList = subsItemDeliveryRepository.findAll();
        assertThat(subsItemDeliveryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkDeliveryOptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = subsItemDeliveryRepository.findAll().size();
        // set the field null
        subsItemDelivery.setDeliveryOption(null);

        // Create the SubsItemDelivery, which fails.

        restSubsItemDeliveryMockMvc.perform(post("/api/subs-item-deliveries").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subsItemDelivery)))
            .andExpect(status().isBadRequest());

        List<SubsItemDelivery> subsItemDeliveryList = subsItemDeliveryRepository.findAll();
        assertThat(subsItemDeliveryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = subsItemDeliveryRepository.findAll().size();
        // set the field null
        subsItemDelivery.setCreatedDate(null);

        // Create the SubsItemDelivery, which fails.

        restSubsItemDeliveryMockMvc.perform(post("/api/subs-item-deliveries").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subsItemDelivery)))
            .andExpect(status().isBadRequest());

        List<SubsItemDelivery> subsItemDeliveryList = subsItemDeliveryRepository.findAll();
        assertThat(subsItemDeliveryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = subsItemDeliveryRepository.findAll().size();
        // set the field null
        subsItemDelivery.setCreatedBy(null);

        // Create the SubsItemDelivery, which fails.

        restSubsItemDeliveryMockMvc.perform(post("/api/subs-item-deliveries").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subsItemDelivery)))
            .andExpect(status().isBadRequest());

        List<SubsItemDelivery> subsItemDeliveryList = subsItemDeliveryRepository.findAll();
        assertThat(subsItemDeliveryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = subsItemDeliveryRepository.findAll().size();
        // set the field null
        subsItemDelivery.setLastUpdatedDate(null);

        // Create the SubsItemDelivery, which fails.

        restSubsItemDeliveryMockMvc.perform(post("/api/subs-item-deliveries").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subsItemDelivery)))
            .andExpect(status().isBadRequest());

        List<SubsItemDelivery> subsItemDeliveryList = subsItemDeliveryRepository.findAll();
        assertThat(subsItemDeliveryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = subsItemDeliveryRepository.findAll().size();
        // set the field null
        subsItemDelivery.setLastUpdatedBy(null);

        // Create the SubsItemDelivery, which fails.

        restSubsItemDeliveryMockMvc.perform(post("/api/subs-item-deliveries").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subsItemDelivery)))
            .andExpect(status().isBadRequest());

        List<SubsItemDelivery> subsItemDeliveryList = subsItemDeliveryRepository.findAll();
        assertThat(subsItemDeliveryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTenantIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = subsItemDeliveryRepository.findAll().size();
        // set the field null
        subsItemDelivery.setTenantId(null);

        // Create the SubsItemDelivery, which fails.

        restSubsItemDeliveryMockMvc.perform(post("/api/subs-item-deliveries").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subsItemDelivery)))
            .andExpect(status().isBadRequest());

        List<SubsItemDelivery> subsItemDeliveryList = subsItemDeliveryRepository.findAll();
        assertThat(subsItemDeliveryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllSubsItemDeliveries() throws Exception {
        // Initialize the database
        subsItemDeliveryRepository.save(subsItemDelivery);

        // Get all the subsItemDeliveryList
        restSubsItemDeliveryMockMvc.perform(get("/api/subs-item-deliveries?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(subsItemDelivery.getId())))
            .andExpect(jsonPath("$.[*].deliveryId").value(hasItem(DEFAULT_DELIVERY_ID.intValue())))
            .andExpect(jsonPath("$.[*].deliveryStatus").value(hasItem(DEFAULT_DELIVERY_STATUS)))
            .andExpect(jsonPath("$.[*].deliveryOption").value(hasItem(DEFAULT_DELIVERY_OPTION.toString())))
            .andExpect(jsonPath("$.[*].tempEfLockerCode").value(hasItem(DEFAULT_TEMP_EF_LOCKER_CODE)))
            .andExpect(jsonPath("$.[*].tempAddressId").value(hasItem(DEFAULT_TEMP_ADDRESS_ID)))
            .andExpect(jsonPath("$.[*].deliveryRefCode").value(hasItem(DEFAULT_DELIVERY_REF_CODE)))
            .andExpect(jsonPath("$.[*].fileGenerationDate").value(hasItem(DEFAULT_FILE_GENERATION_DATE.toString())))
            .andExpect(jsonPath("$.[*].fileReceivedDate").value(hasItem(DEFAULT_FILE_RECEIVED_DATE.toString())))
            .andExpect(jsonPath("$.[*].deliveryDate").value(hasItem(DEFAULT_DELIVERY_DATE.toString())))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS)))
            .andExpect(jsonPath("$.[*].lockCount").value(hasItem(DEFAULT_LOCK_COUNT)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].lastUpdatedDate").value(hasItem(DEFAULT_LAST_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].tenantId").value(hasItem(DEFAULT_TENANT_ID)));
    }
    
    @Test
    public void getSubsItemDelivery() throws Exception {
        // Initialize the database
        subsItemDeliveryRepository.save(subsItemDelivery);

        // Get the subsItemDelivery
        restSubsItemDeliveryMockMvc.perform(get("/api/subs-item-deliveries/{id}", subsItemDelivery.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(subsItemDelivery.getId()))
            .andExpect(jsonPath("$.deliveryId").value(DEFAULT_DELIVERY_ID.intValue()))
            .andExpect(jsonPath("$.deliveryStatus").value(DEFAULT_DELIVERY_STATUS))
            .andExpect(jsonPath("$.deliveryOption").value(DEFAULT_DELIVERY_OPTION.toString()))
            .andExpect(jsonPath("$.tempEfLockerCode").value(DEFAULT_TEMP_EF_LOCKER_CODE))
            .andExpect(jsonPath("$.tempAddressId").value(DEFAULT_TEMP_ADDRESS_ID))
            .andExpect(jsonPath("$.deliveryRefCode").value(DEFAULT_DELIVERY_REF_CODE))
            .andExpect(jsonPath("$.fileGenerationDate").value(DEFAULT_FILE_GENERATION_DATE.toString()))
            .andExpect(jsonPath("$.fileReceivedDate").value(DEFAULT_FILE_RECEIVED_DATE.toString()))
            .andExpect(jsonPath("$.deliveryDate").value(DEFAULT_DELIVERY_DATE.toString()))
            .andExpect(jsonPath("$.remarks").value(DEFAULT_REMARKS))
            .andExpect(jsonPath("$.lockCount").value(DEFAULT_LOCK_COUNT))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.lastUpdatedDate").value(DEFAULT_LAST_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY))
            .andExpect(jsonPath("$.tenantId").value(DEFAULT_TENANT_ID));
    }

    @Test
    public void getNonExistingSubsItemDelivery() throws Exception {
        // Get the subsItemDelivery
        restSubsItemDeliveryMockMvc.perform(get("/api/subs-item-deliveries/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateSubsItemDelivery() throws Exception {
        // Initialize the database
        subsItemDeliveryService.save(subsItemDelivery);

        int databaseSizeBeforeUpdate = subsItemDeliveryRepository.findAll().size();

        // Update the subsItemDelivery
        SubsItemDelivery updatedSubsItemDelivery = subsItemDeliveryRepository.findById(subsItemDelivery.getId()).get();
        updatedSubsItemDelivery
            .deliveryId(UPDATED_DELIVERY_ID)
            .deliveryStatus(UPDATED_DELIVERY_STATUS)
            .deliveryOption(UPDATED_DELIVERY_OPTION)
            .tempEfLockerCode(UPDATED_TEMP_EF_LOCKER_CODE)
            .tempAddressId(UPDATED_TEMP_ADDRESS_ID)
            .deliveryRefCode(UPDATED_DELIVERY_REF_CODE)
            .fileGenerationDate(UPDATED_FILE_GENERATION_DATE)
            .fileReceivedDate(UPDATED_FILE_RECEIVED_DATE)
            .deliveryDate(UPDATED_DELIVERY_DATE)
            .remarks(UPDATED_REMARKS)
            .lockCount(UPDATED_LOCK_COUNT)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);

        restSubsItemDeliveryMockMvc.perform(put("/api/subs-item-deliveries").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedSubsItemDelivery)))
            .andExpect(status().isOk());

        // Validate the SubsItemDelivery in the database
        List<SubsItemDelivery> subsItemDeliveryList = subsItemDeliveryRepository.findAll();
        assertThat(subsItemDeliveryList).hasSize(databaseSizeBeforeUpdate);
        SubsItemDelivery testSubsItemDelivery = subsItemDeliveryList.get(subsItemDeliveryList.size() - 1);
        assertThat(testSubsItemDelivery.getDeliveryId()).isEqualTo(UPDATED_DELIVERY_ID);
        assertThat(testSubsItemDelivery.getDeliveryStatus()).isEqualTo(UPDATED_DELIVERY_STATUS);
        assertThat(testSubsItemDelivery.getDeliveryOption()).isEqualTo(UPDATED_DELIVERY_OPTION);
        assertThat(testSubsItemDelivery.getTempEfLockerCode()).isEqualTo(UPDATED_TEMP_EF_LOCKER_CODE);
        assertThat(testSubsItemDelivery.getTempAddressId()).isEqualTo(UPDATED_TEMP_ADDRESS_ID);
        assertThat(testSubsItemDelivery.getDeliveryRefCode()).isEqualTo(UPDATED_DELIVERY_REF_CODE);
        assertThat(testSubsItemDelivery.getFileGenerationDate()).isEqualTo(UPDATED_FILE_GENERATION_DATE);
        assertThat(testSubsItemDelivery.getFileReceivedDate()).isEqualTo(UPDATED_FILE_RECEIVED_DATE);
        assertThat(testSubsItemDelivery.getDeliveryDate()).isEqualTo(UPDATED_DELIVERY_DATE);
        assertThat(testSubsItemDelivery.getRemarks()).isEqualTo(UPDATED_REMARKS);
        assertThat(testSubsItemDelivery.getLockCount()).isEqualTo(UPDATED_LOCK_COUNT);
        assertThat(testSubsItemDelivery.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testSubsItemDelivery.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testSubsItemDelivery.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testSubsItemDelivery.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
        assertThat(testSubsItemDelivery.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    public void updateNonExistingSubsItemDelivery() throws Exception {
        int databaseSizeBeforeUpdate = subsItemDeliveryRepository.findAll().size();

        // Create the SubsItemDelivery

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSubsItemDeliveryMockMvc.perform(put("/api/subs-item-deliveries").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subsItemDelivery)))
            .andExpect(status().isBadRequest());

        // Validate the SubsItemDelivery in the database
        List<SubsItemDelivery> subsItemDeliveryList = subsItemDeliveryRepository.findAll();
        assertThat(subsItemDeliveryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteSubsItemDelivery() throws Exception {
        // Initialize the database
        subsItemDeliveryService.save(subsItemDelivery);

        int databaseSizeBeforeDelete = subsItemDeliveryRepository.findAll().size();

        // Delete the subsItemDelivery
        restSubsItemDeliveryMockMvc.perform(delete("/api/subs-item-deliveries/{id}", subsItemDelivery.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SubsItemDelivery> subsItemDeliveryList = subsItemDeliveryRepository.findAll();
        assertThat(subsItemDeliveryList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
