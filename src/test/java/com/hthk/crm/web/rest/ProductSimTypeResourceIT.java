package com.hthk.crm.web.rest;

import com.hthk.crm.CrmwebApp;
import com.hthk.crm.config.TestSecurityConfiguration;
import com.hthk.crm.domain.ProductSimType;
import com.hthk.crm.repository.ProductSimTypeRepository;
import com.hthk.crm.service.ProductSimTypeService;

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

import com.hthk.crm.domain.enumeration.SimType;
/**
 * Integration tests for the {@link ProductSimTypeResource} REST controller.
 */
@SpringBootTest(classes = { CrmwebApp.class, TestSecurityConfiguration.class })

@AutoConfigureMockMvc
@WithMockUser
public class ProductSimTypeResourceIT {

    private static final SimType DEFAULT_SIM_TYPE = SimType.PHYSICAL_SIM;
    private static final SimType UPDATED_SIM_TYPE = SimType.ESIM;

    private static final String DEFAULT_IMSI_RANGE_FROM = "AAAAAAAAAA";
    private static final String UPDATED_IMSI_RANGE_FROM = "BBBBBBBBBB";

    private static final String DEFAULT_IMSI_RANGE_TO = "AAAAAAAAAA";
    private static final String UPDATED_IMSI_RANGE_TO = "BBBBBBBBBB";

    private static final String DEFAULT_SIM_REPOSITORY_REF = "AAAAAAAAAA";
    private static final String UPDATED_SIM_REPOSITORY_REF = "BBBBBBBBBB";

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
    private ProductSimTypeRepository productSimTypeRepository;

    @Autowired
    private ProductSimTypeService productSimTypeService;

    @Autowired
    private MockMvc restProductSimTypeMockMvc;

    private ProductSimType productSimType;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProductSimType createEntity() {
        ProductSimType productSimType = new ProductSimType()
            .simType(DEFAULT_SIM_TYPE)
            .imsiRangeFrom(DEFAULT_IMSI_RANGE_FROM)
            .imsiRangeTo(DEFAULT_IMSI_RANGE_TO)
            .simRepositoryRef(DEFAULT_SIM_REPOSITORY_REF)
            .lockCount(DEFAULT_LOCK_COUNT)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdatedDate(DEFAULT_LAST_UPDATED_DATE)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY)
            .tenantId(DEFAULT_TENANT_ID);
        return productSimType;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProductSimType createUpdatedEntity() {
        ProductSimType productSimType = new ProductSimType()
            .simType(UPDATED_SIM_TYPE)
            .imsiRangeFrom(UPDATED_IMSI_RANGE_FROM)
            .imsiRangeTo(UPDATED_IMSI_RANGE_TO)
            .simRepositoryRef(UPDATED_SIM_REPOSITORY_REF)
            .lockCount(UPDATED_LOCK_COUNT)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);
        return productSimType;
    }

    @BeforeEach
    public void initTest() {
        productSimTypeRepository.deleteAll();
        productSimType = createEntity();
    }

    @Test
    public void createProductSimType() throws Exception {
        int databaseSizeBeforeCreate = productSimTypeRepository.findAll().size();

        // Create the ProductSimType
        restProductSimTypeMockMvc.perform(post("/api/product-sim-types").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productSimType)))
            .andExpect(status().isCreated());

        // Validate the ProductSimType in the database
        List<ProductSimType> productSimTypeList = productSimTypeRepository.findAll();
        assertThat(productSimTypeList).hasSize(databaseSizeBeforeCreate + 1);
        ProductSimType testProductSimType = productSimTypeList.get(productSimTypeList.size() - 1);
        assertThat(testProductSimType.getSimType()).isEqualTo(DEFAULT_SIM_TYPE);
        assertThat(testProductSimType.getImsiRangeFrom()).isEqualTo(DEFAULT_IMSI_RANGE_FROM);
        assertThat(testProductSimType.getImsiRangeTo()).isEqualTo(DEFAULT_IMSI_RANGE_TO);
        assertThat(testProductSimType.getSimRepositoryRef()).isEqualTo(DEFAULT_SIM_REPOSITORY_REF);
        assertThat(testProductSimType.getLockCount()).isEqualTo(DEFAULT_LOCK_COUNT);
        assertThat(testProductSimType.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testProductSimType.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testProductSimType.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testProductSimType.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
        assertThat(testProductSimType.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
    }

    @Test
    public void createProductSimTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = productSimTypeRepository.findAll().size();

        // Create the ProductSimType with an existing ID
        productSimType.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restProductSimTypeMockMvc.perform(post("/api/product-sim-types").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productSimType)))
            .andExpect(status().isBadRequest());

        // Validate the ProductSimType in the database
        List<ProductSimType> productSimTypeList = productSimTypeRepository.findAll();
        assertThat(productSimTypeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkSimTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = productSimTypeRepository.findAll().size();
        // set the field null
        productSimType.setSimType(null);

        // Create the ProductSimType, which fails.

        restProductSimTypeMockMvc.perform(post("/api/product-sim-types").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productSimType)))
            .andExpect(status().isBadRequest());

        List<ProductSimType> productSimTypeList = productSimTypeRepository.findAll();
        assertThat(productSimTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = productSimTypeRepository.findAll().size();
        // set the field null
        productSimType.setCreatedDate(null);

        // Create the ProductSimType, which fails.

        restProductSimTypeMockMvc.perform(post("/api/product-sim-types").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productSimType)))
            .andExpect(status().isBadRequest());

        List<ProductSimType> productSimTypeList = productSimTypeRepository.findAll();
        assertThat(productSimTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = productSimTypeRepository.findAll().size();
        // set the field null
        productSimType.setCreatedBy(null);

        // Create the ProductSimType, which fails.

        restProductSimTypeMockMvc.perform(post("/api/product-sim-types").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productSimType)))
            .andExpect(status().isBadRequest());

        List<ProductSimType> productSimTypeList = productSimTypeRepository.findAll();
        assertThat(productSimTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = productSimTypeRepository.findAll().size();
        // set the field null
        productSimType.setLastUpdatedDate(null);

        // Create the ProductSimType, which fails.

        restProductSimTypeMockMvc.perform(post("/api/product-sim-types").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productSimType)))
            .andExpect(status().isBadRequest());

        List<ProductSimType> productSimTypeList = productSimTypeRepository.findAll();
        assertThat(productSimTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = productSimTypeRepository.findAll().size();
        // set the field null
        productSimType.setLastUpdatedBy(null);

        // Create the ProductSimType, which fails.

        restProductSimTypeMockMvc.perform(post("/api/product-sim-types").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productSimType)))
            .andExpect(status().isBadRequest());

        List<ProductSimType> productSimTypeList = productSimTypeRepository.findAll();
        assertThat(productSimTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTenantIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = productSimTypeRepository.findAll().size();
        // set the field null
        productSimType.setTenantId(null);

        // Create the ProductSimType, which fails.

        restProductSimTypeMockMvc.perform(post("/api/product-sim-types").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productSimType)))
            .andExpect(status().isBadRequest());

        List<ProductSimType> productSimTypeList = productSimTypeRepository.findAll();
        assertThat(productSimTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllProductSimTypes() throws Exception {
        // Initialize the database
        productSimTypeRepository.save(productSimType);

        // Get all the productSimTypeList
        restProductSimTypeMockMvc.perform(get("/api/product-sim-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(productSimType.getId())))
            .andExpect(jsonPath("$.[*].simType").value(hasItem(DEFAULT_SIM_TYPE.toString())))
            .andExpect(jsonPath("$.[*].imsiRangeFrom").value(hasItem(DEFAULT_IMSI_RANGE_FROM)))
            .andExpect(jsonPath("$.[*].imsiRangeTo").value(hasItem(DEFAULT_IMSI_RANGE_TO)))
            .andExpect(jsonPath("$.[*].simRepositoryRef").value(hasItem(DEFAULT_SIM_REPOSITORY_REF)))
            .andExpect(jsonPath("$.[*].lockCount").value(hasItem(DEFAULT_LOCK_COUNT)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].lastUpdatedDate").value(hasItem(DEFAULT_LAST_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].tenantId").value(hasItem(DEFAULT_TENANT_ID)));
    }
    
    @Test
    public void getProductSimType() throws Exception {
        // Initialize the database
        productSimTypeRepository.save(productSimType);

        // Get the productSimType
        restProductSimTypeMockMvc.perform(get("/api/product-sim-types/{id}", productSimType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(productSimType.getId()))
            .andExpect(jsonPath("$.simType").value(DEFAULT_SIM_TYPE.toString()))
            .andExpect(jsonPath("$.imsiRangeFrom").value(DEFAULT_IMSI_RANGE_FROM))
            .andExpect(jsonPath("$.imsiRangeTo").value(DEFAULT_IMSI_RANGE_TO))
            .andExpect(jsonPath("$.simRepositoryRef").value(DEFAULT_SIM_REPOSITORY_REF))
            .andExpect(jsonPath("$.lockCount").value(DEFAULT_LOCK_COUNT))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.lastUpdatedDate").value(DEFAULT_LAST_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY))
            .andExpect(jsonPath("$.tenantId").value(DEFAULT_TENANT_ID));
    }

    @Test
    public void getNonExistingProductSimType() throws Exception {
        // Get the productSimType
        restProductSimTypeMockMvc.perform(get("/api/product-sim-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateProductSimType() throws Exception {
        // Initialize the database
        productSimTypeService.save(productSimType);

        int databaseSizeBeforeUpdate = productSimTypeRepository.findAll().size();

        // Update the productSimType
        ProductSimType updatedProductSimType = productSimTypeRepository.findById(productSimType.getId()).get();
        updatedProductSimType
            .simType(UPDATED_SIM_TYPE)
            .imsiRangeFrom(UPDATED_IMSI_RANGE_FROM)
            .imsiRangeTo(UPDATED_IMSI_RANGE_TO)
            .simRepositoryRef(UPDATED_SIM_REPOSITORY_REF)
            .lockCount(UPDATED_LOCK_COUNT)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);

        restProductSimTypeMockMvc.perform(put("/api/product-sim-types").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedProductSimType)))
            .andExpect(status().isOk());

        // Validate the ProductSimType in the database
        List<ProductSimType> productSimTypeList = productSimTypeRepository.findAll();
        assertThat(productSimTypeList).hasSize(databaseSizeBeforeUpdate);
        ProductSimType testProductSimType = productSimTypeList.get(productSimTypeList.size() - 1);
        assertThat(testProductSimType.getSimType()).isEqualTo(UPDATED_SIM_TYPE);
        assertThat(testProductSimType.getImsiRangeFrom()).isEqualTo(UPDATED_IMSI_RANGE_FROM);
        assertThat(testProductSimType.getImsiRangeTo()).isEqualTo(UPDATED_IMSI_RANGE_TO);
        assertThat(testProductSimType.getSimRepositoryRef()).isEqualTo(UPDATED_SIM_REPOSITORY_REF);
        assertThat(testProductSimType.getLockCount()).isEqualTo(UPDATED_LOCK_COUNT);
        assertThat(testProductSimType.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testProductSimType.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testProductSimType.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testProductSimType.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
        assertThat(testProductSimType.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    public void updateNonExistingProductSimType() throws Exception {
        int databaseSizeBeforeUpdate = productSimTypeRepository.findAll().size();

        // Create the ProductSimType

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProductSimTypeMockMvc.perform(put("/api/product-sim-types").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productSimType)))
            .andExpect(status().isBadRequest());

        // Validate the ProductSimType in the database
        List<ProductSimType> productSimTypeList = productSimTypeRepository.findAll();
        assertThat(productSimTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteProductSimType() throws Exception {
        // Initialize the database
        productSimTypeService.save(productSimType);

        int databaseSizeBeforeDelete = productSimTypeRepository.findAll().size();

        // Delete the productSimType
        restProductSimTypeMockMvc.perform(delete("/api/product-sim-types/{id}", productSimType.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProductSimType> productSimTypeList = productSimTypeRepository.findAll();
        assertThat(productSimTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
