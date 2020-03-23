package com.hthk.crm.web.rest;

import com.hthk.crm.CrmwebApp;
import com.hthk.crm.config.TestSecurityConfiguration;
import com.hthk.crm.domain.ProductMms;
import com.hthk.crm.repository.ProductMmsRepository;
import com.hthk.crm.service.ProductMmsService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.hthk.crm.domain.enumeration.MmsType;
/**
 * Integration tests for the {@link ProductMmsResource} REST controller.
 */
@SpringBootTest(classes = { CrmwebApp.class, TestSecurityConfiguration.class })

@AutoConfigureMockMvc
@WithMockUser
public class ProductMmsResourceIT {

    private static final String DEFAULT_MMS_ID = "AAAAAAAAAA";
    private static final String UPDATED_MMS_ID = "BBBBBBBBBB";

    private static final Long DEFAULT_PRODUCT_SPEC_ID = 1L;
    private static final Long UPDATED_PRODUCT_SPEC_ID = 2L;

    private static final String DEFAULT_UNIT = "AAAAAAAAAA";
    private static final String UPDATED_UNIT = "BBBBBBBBBB";

    private static final Integer DEFAULT_VOLUME = 1;
    private static final Integer UPDATED_VOLUME = 2;

    private static final MmsType DEFAULT_MMS_TYPE = MmsType.LOCAL;
    private static final MmsType UPDATED_MMS_TYPE = MmsType.INTERNATIONAL;

    private static final Boolean DEFAULT_ROAMING_ALLOWED = false;
    private static final Boolean UPDATED_ROAMING_ALLOWED = true;

    private static final BigDecimal DEFAULT_MIN_TRANSFER_QUOTA = new BigDecimal(1);
    private static final BigDecimal UPDATED_MIN_TRANSFER_QUOTA = new BigDecimal(2);

    private static final BigDecimal DEFAULT_MAX_TRANSFER_QUOTA = new BigDecimal(1);
    private static final BigDecimal UPDATED_MAX_TRANSFER_QUOTA = new BigDecimal(2);

    private static final BigDecimal DEFAULT_MIN_RETENTION_QUOTA = new BigDecimal(1);
    private static final BigDecimal UPDATED_MIN_RETENTION_QUOTA = new BigDecimal(2);

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
    private ProductMmsRepository productMmsRepository;

    @Autowired
    private ProductMmsService productMmsService;

    @Autowired
    private MockMvc restProductMmsMockMvc;

    private ProductMms productMms;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProductMms createEntity() {
        ProductMms productMms = new ProductMms()
            .mmsId(DEFAULT_MMS_ID)
            .productSpecId(DEFAULT_PRODUCT_SPEC_ID)
            .unit(DEFAULT_UNIT)
            .volume(DEFAULT_VOLUME)
            .mmsType(DEFAULT_MMS_TYPE)
            .roamingAllowed(DEFAULT_ROAMING_ALLOWED)
            .minTransferQuota(DEFAULT_MIN_TRANSFER_QUOTA)
            .maxTransferQuota(DEFAULT_MAX_TRANSFER_QUOTA)
            .minRetentionQuota(DEFAULT_MIN_RETENTION_QUOTA)
            .lockCount(DEFAULT_LOCK_COUNT)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdatedDate(DEFAULT_LAST_UPDATED_DATE)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY)
            .tenantId(DEFAULT_TENANT_ID);
        return productMms;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProductMms createUpdatedEntity() {
        ProductMms productMms = new ProductMms()
            .mmsId(UPDATED_MMS_ID)
            .productSpecId(UPDATED_PRODUCT_SPEC_ID)
            .unit(UPDATED_UNIT)
            .volume(UPDATED_VOLUME)
            .mmsType(UPDATED_MMS_TYPE)
            .roamingAllowed(UPDATED_ROAMING_ALLOWED)
            .minTransferQuota(UPDATED_MIN_TRANSFER_QUOTA)
            .maxTransferQuota(UPDATED_MAX_TRANSFER_QUOTA)
            .minRetentionQuota(UPDATED_MIN_RETENTION_QUOTA)
            .lockCount(UPDATED_LOCK_COUNT)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);
        return productMms;
    }

    @BeforeEach
    public void initTest() {
        productMmsRepository.deleteAll();
        productMms = createEntity();
    }

    @Test
    public void createProductMms() throws Exception {
        int databaseSizeBeforeCreate = productMmsRepository.findAll().size();

        // Create the ProductMms
        restProductMmsMockMvc.perform(post("/api/product-mms").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productMms)))
            .andExpect(status().isCreated());

        // Validate the ProductMms in the database
        List<ProductMms> productMmsList = productMmsRepository.findAll();
        assertThat(productMmsList).hasSize(databaseSizeBeforeCreate + 1);
        ProductMms testProductMms = productMmsList.get(productMmsList.size() - 1);
        assertThat(testProductMms.getMmsId()).isEqualTo(DEFAULT_MMS_ID);
        assertThat(testProductMms.getProductSpecId()).isEqualTo(DEFAULT_PRODUCT_SPEC_ID);
        assertThat(testProductMms.getUnit()).isEqualTo(DEFAULT_UNIT);
        assertThat(testProductMms.getVolume()).isEqualTo(DEFAULT_VOLUME);
        assertThat(testProductMms.getMmsType()).isEqualTo(DEFAULT_MMS_TYPE);
        assertThat(testProductMms.isRoamingAllowed()).isEqualTo(DEFAULT_ROAMING_ALLOWED);
        assertThat(testProductMms.getMinTransferQuota()).isEqualTo(DEFAULT_MIN_TRANSFER_QUOTA);
        assertThat(testProductMms.getMaxTransferQuota()).isEqualTo(DEFAULT_MAX_TRANSFER_QUOTA);
        assertThat(testProductMms.getMinRetentionQuota()).isEqualTo(DEFAULT_MIN_RETENTION_QUOTA);
        assertThat(testProductMms.getLockCount()).isEqualTo(DEFAULT_LOCK_COUNT);
        assertThat(testProductMms.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testProductMms.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testProductMms.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testProductMms.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
        assertThat(testProductMms.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
    }

    @Test
    public void createProductMmsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = productMmsRepository.findAll().size();

        // Create the ProductMms with an existing ID
        productMms.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restProductMmsMockMvc.perform(post("/api/product-mms").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productMms)))
            .andExpect(status().isBadRequest());

        // Validate the ProductMms in the database
        List<ProductMms> productMmsList = productMmsRepository.findAll();
        assertThat(productMmsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkProductSpecIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = productMmsRepository.findAll().size();
        // set the field null
        productMms.setProductSpecId(null);

        // Create the ProductMms, which fails.

        restProductMmsMockMvc.perform(post("/api/product-mms").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productMms)))
            .andExpect(status().isBadRequest());

        List<ProductMms> productMmsList = productMmsRepository.findAll();
        assertThat(productMmsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkUnitIsRequired() throws Exception {
        int databaseSizeBeforeTest = productMmsRepository.findAll().size();
        // set the field null
        productMms.setUnit(null);

        // Create the ProductMms, which fails.

        restProductMmsMockMvc.perform(post("/api/product-mms").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productMms)))
            .andExpect(status().isBadRequest());

        List<ProductMms> productMmsList = productMmsRepository.findAll();
        assertThat(productMmsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkVolumeIsRequired() throws Exception {
        int databaseSizeBeforeTest = productMmsRepository.findAll().size();
        // set the field null
        productMms.setVolume(null);

        // Create the ProductMms, which fails.

        restProductMmsMockMvc.perform(post("/api/product-mms").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productMms)))
            .andExpect(status().isBadRequest());

        List<ProductMms> productMmsList = productMmsRepository.findAll();
        assertThat(productMmsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = productMmsRepository.findAll().size();
        // set the field null
        productMms.setCreatedDate(null);

        // Create the ProductMms, which fails.

        restProductMmsMockMvc.perform(post("/api/product-mms").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productMms)))
            .andExpect(status().isBadRequest());

        List<ProductMms> productMmsList = productMmsRepository.findAll();
        assertThat(productMmsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = productMmsRepository.findAll().size();
        // set the field null
        productMms.setCreatedBy(null);

        // Create the ProductMms, which fails.

        restProductMmsMockMvc.perform(post("/api/product-mms").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productMms)))
            .andExpect(status().isBadRequest());

        List<ProductMms> productMmsList = productMmsRepository.findAll();
        assertThat(productMmsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = productMmsRepository.findAll().size();
        // set the field null
        productMms.setLastUpdatedDate(null);

        // Create the ProductMms, which fails.

        restProductMmsMockMvc.perform(post("/api/product-mms").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productMms)))
            .andExpect(status().isBadRequest());

        List<ProductMms> productMmsList = productMmsRepository.findAll();
        assertThat(productMmsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = productMmsRepository.findAll().size();
        // set the field null
        productMms.setLastUpdatedBy(null);

        // Create the ProductMms, which fails.

        restProductMmsMockMvc.perform(post("/api/product-mms").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productMms)))
            .andExpect(status().isBadRequest());

        List<ProductMms> productMmsList = productMmsRepository.findAll();
        assertThat(productMmsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTenantIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = productMmsRepository.findAll().size();
        // set the field null
        productMms.setTenantId(null);

        // Create the ProductMms, which fails.

        restProductMmsMockMvc.perform(post("/api/product-mms").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productMms)))
            .andExpect(status().isBadRequest());

        List<ProductMms> productMmsList = productMmsRepository.findAll();
        assertThat(productMmsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllProductMms() throws Exception {
        // Initialize the database
        productMmsRepository.save(productMms);

        // Get all the productMmsList
        restProductMmsMockMvc.perform(get("/api/product-mms?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(productMms.getId())))
            .andExpect(jsonPath("$.[*].mmsId").value(hasItem(DEFAULT_MMS_ID)))
            .andExpect(jsonPath("$.[*].productSpecId").value(hasItem(DEFAULT_PRODUCT_SPEC_ID.intValue())))
            .andExpect(jsonPath("$.[*].unit").value(hasItem(DEFAULT_UNIT)))
            .andExpect(jsonPath("$.[*].volume").value(hasItem(DEFAULT_VOLUME)))
            .andExpect(jsonPath("$.[*].mmsType").value(hasItem(DEFAULT_MMS_TYPE.toString())))
            .andExpect(jsonPath("$.[*].roamingAllowed").value(hasItem(DEFAULT_ROAMING_ALLOWED.booleanValue())))
            .andExpect(jsonPath("$.[*].minTransferQuota").value(hasItem(DEFAULT_MIN_TRANSFER_QUOTA.intValue())))
            .andExpect(jsonPath("$.[*].maxTransferQuota").value(hasItem(DEFAULT_MAX_TRANSFER_QUOTA.intValue())))
            .andExpect(jsonPath("$.[*].minRetentionQuota").value(hasItem(DEFAULT_MIN_RETENTION_QUOTA.intValue())))
            .andExpect(jsonPath("$.[*].lockCount").value(hasItem(DEFAULT_LOCK_COUNT)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].lastUpdatedDate").value(hasItem(DEFAULT_LAST_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].tenantId").value(hasItem(DEFAULT_TENANT_ID)));
    }
    
    @Test
    public void getProductMms() throws Exception {
        // Initialize the database
        productMmsRepository.save(productMms);

        // Get the productMms
        restProductMmsMockMvc.perform(get("/api/product-mms/{id}", productMms.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(productMms.getId()))
            .andExpect(jsonPath("$.mmsId").value(DEFAULT_MMS_ID))
            .andExpect(jsonPath("$.productSpecId").value(DEFAULT_PRODUCT_SPEC_ID.intValue()))
            .andExpect(jsonPath("$.unit").value(DEFAULT_UNIT))
            .andExpect(jsonPath("$.volume").value(DEFAULT_VOLUME))
            .andExpect(jsonPath("$.mmsType").value(DEFAULT_MMS_TYPE.toString()))
            .andExpect(jsonPath("$.roamingAllowed").value(DEFAULT_ROAMING_ALLOWED.booleanValue()))
            .andExpect(jsonPath("$.minTransferQuota").value(DEFAULT_MIN_TRANSFER_QUOTA.intValue()))
            .andExpect(jsonPath("$.maxTransferQuota").value(DEFAULT_MAX_TRANSFER_QUOTA.intValue()))
            .andExpect(jsonPath("$.minRetentionQuota").value(DEFAULT_MIN_RETENTION_QUOTA.intValue()))
            .andExpect(jsonPath("$.lockCount").value(DEFAULT_LOCK_COUNT))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.lastUpdatedDate").value(DEFAULT_LAST_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY))
            .andExpect(jsonPath("$.tenantId").value(DEFAULT_TENANT_ID));
    }

    @Test
    public void getNonExistingProductMms() throws Exception {
        // Get the productMms
        restProductMmsMockMvc.perform(get("/api/product-mms/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateProductMms() throws Exception {
        // Initialize the database
        productMmsService.save(productMms);

        int databaseSizeBeforeUpdate = productMmsRepository.findAll().size();

        // Update the productMms
        ProductMms updatedProductMms = productMmsRepository.findById(productMms.getId()).get();
        updatedProductMms
            .mmsId(UPDATED_MMS_ID)
            .productSpecId(UPDATED_PRODUCT_SPEC_ID)
            .unit(UPDATED_UNIT)
            .volume(UPDATED_VOLUME)
            .mmsType(UPDATED_MMS_TYPE)
            .roamingAllowed(UPDATED_ROAMING_ALLOWED)
            .minTransferQuota(UPDATED_MIN_TRANSFER_QUOTA)
            .maxTransferQuota(UPDATED_MAX_TRANSFER_QUOTA)
            .minRetentionQuota(UPDATED_MIN_RETENTION_QUOTA)
            .lockCount(UPDATED_LOCK_COUNT)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);

        restProductMmsMockMvc.perform(put("/api/product-mms").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedProductMms)))
            .andExpect(status().isOk());

        // Validate the ProductMms in the database
        List<ProductMms> productMmsList = productMmsRepository.findAll();
        assertThat(productMmsList).hasSize(databaseSizeBeforeUpdate);
        ProductMms testProductMms = productMmsList.get(productMmsList.size() - 1);
        assertThat(testProductMms.getMmsId()).isEqualTo(UPDATED_MMS_ID);
        assertThat(testProductMms.getProductSpecId()).isEqualTo(UPDATED_PRODUCT_SPEC_ID);
        assertThat(testProductMms.getUnit()).isEqualTo(UPDATED_UNIT);
        assertThat(testProductMms.getVolume()).isEqualTo(UPDATED_VOLUME);
        assertThat(testProductMms.getMmsType()).isEqualTo(UPDATED_MMS_TYPE);
        assertThat(testProductMms.isRoamingAllowed()).isEqualTo(UPDATED_ROAMING_ALLOWED);
        assertThat(testProductMms.getMinTransferQuota()).isEqualTo(UPDATED_MIN_TRANSFER_QUOTA);
        assertThat(testProductMms.getMaxTransferQuota()).isEqualTo(UPDATED_MAX_TRANSFER_QUOTA);
        assertThat(testProductMms.getMinRetentionQuota()).isEqualTo(UPDATED_MIN_RETENTION_QUOTA);
        assertThat(testProductMms.getLockCount()).isEqualTo(UPDATED_LOCK_COUNT);
        assertThat(testProductMms.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testProductMms.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testProductMms.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testProductMms.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
        assertThat(testProductMms.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    public void updateNonExistingProductMms() throws Exception {
        int databaseSizeBeforeUpdate = productMmsRepository.findAll().size();

        // Create the ProductMms

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProductMmsMockMvc.perform(put("/api/product-mms").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productMms)))
            .andExpect(status().isBadRequest());

        // Validate the ProductMms in the database
        List<ProductMms> productMmsList = productMmsRepository.findAll();
        assertThat(productMmsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteProductMms() throws Exception {
        // Initialize the database
        productMmsService.save(productMms);

        int databaseSizeBeforeDelete = productMmsRepository.findAll().size();

        // Delete the productMms
        restProductMmsMockMvc.perform(delete("/api/product-mms/{id}", productMms.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProductMms> productMmsList = productMmsRepository.findAll();
        assertThat(productMmsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
