package com.hthk.crm.web.rest;

import com.hthk.crm.CrmwebApp;
import com.hthk.crm.config.TestSecurityConfiguration;
import com.hthk.crm.domain.ModelCategory;
import com.hthk.crm.repository.ModelCategoryRepository;
import com.hthk.crm.service.ModelCategoryService;

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
 * Integration tests for the {@link ModelCategoryResource} REST controller.
 */
@SpringBootTest(classes = { CrmwebApp.class, TestSecurityConfiguration.class })

@AutoConfigureMockMvc
@WithMockUser
public class ModelCategoryResourceIT {

    private static final String DEFAULT_MODEL_CATE = "AAAAAAAAAA";
    private static final String UPDATED_MODEL_CATE = "BBBBBBBBBB";

    private static final String DEFAULT_PARENT_MODEL_CATE = "AAAAAAAAAA";
    private static final String UPDATED_PARENT_MODEL_CATE = "BBBBBBBBBB";

    private static final String DEFAULT_MODEL_CATE_DESC = "AAAAAAAAAA";
    private static final String UPDATED_MODEL_CATE_DESC = "BBBBBBBBBB";

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
    private ModelCategoryRepository modelCategoryRepository;

    @Autowired
    private ModelCategoryService modelCategoryService;

    @Autowired
    private MockMvc restModelCategoryMockMvc;

    private ModelCategory modelCategory;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ModelCategory createEntity() {
        ModelCategory modelCategory = new ModelCategory()
            .modelCate(DEFAULT_MODEL_CATE)
            .parentModelCate(DEFAULT_PARENT_MODEL_CATE)
            .modelCateDesc(DEFAULT_MODEL_CATE_DESC)
            .lockCount(DEFAULT_LOCK_COUNT)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdatedDate(DEFAULT_LAST_UPDATED_DATE)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY)
            .tenantId(DEFAULT_TENANT_ID);
        return modelCategory;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ModelCategory createUpdatedEntity() {
        ModelCategory modelCategory = new ModelCategory()
            .modelCate(UPDATED_MODEL_CATE)
            .parentModelCate(UPDATED_PARENT_MODEL_CATE)
            .modelCateDesc(UPDATED_MODEL_CATE_DESC)
            .lockCount(UPDATED_LOCK_COUNT)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);
        return modelCategory;
    }

    @BeforeEach
    public void initTest() {
        modelCategoryRepository.deleteAll();
        modelCategory = createEntity();
    }

    @Test
    public void createModelCategory() throws Exception {
        int databaseSizeBeforeCreate = modelCategoryRepository.findAll().size();

        // Create the ModelCategory
        restModelCategoryMockMvc.perform(post("/api/model-categories").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(modelCategory)))
            .andExpect(status().isCreated());

        // Validate the ModelCategory in the database
        List<ModelCategory> modelCategoryList = modelCategoryRepository.findAll();
        assertThat(modelCategoryList).hasSize(databaseSizeBeforeCreate + 1);
        ModelCategory testModelCategory = modelCategoryList.get(modelCategoryList.size() - 1);
        assertThat(testModelCategory.getModelCate()).isEqualTo(DEFAULT_MODEL_CATE);
        assertThat(testModelCategory.getParentModelCate()).isEqualTo(DEFAULT_PARENT_MODEL_CATE);
        assertThat(testModelCategory.getModelCateDesc()).isEqualTo(DEFAULT_MODEL_CATE_DESC);
        assertThat(testModelCategory.getLockCount()).isEqualTo(DEFAULT_LOCK_COUNT);
        assertThat(testModelCategory.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testModelCategory.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testModelCategory.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testModelCategory.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
        assertThat(testModelCategory.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
    }

    @Test
    public void createModelCategoryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = modelCategoryRepository.findAll().size();

        // Create the ModelCategory with an existing ID
        modelCategory.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restModelCategoryMockMvc.perform(post("/api/model-categories").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(modelCategory)))
            .andExpect(status().isBadRequest());

        // Validate the ModelCategory in the database
        List<ModelCategory> modelCategoryList = modelCategoryRepository.findAll();
        assertThat(modelCategoryList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = modelCategoryRepository.findAll().size();
        // set the field null
        modelCategory.setCreatedDate(null);

        // Create the ModelCategory, which fails.

        restModelCategoryMockMvc.perform(post("/api/model-categories").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(modelCategory)))
            .andExpect(status().isBadRequest());

        List<ModelCategory> modelCategoryList = modelCategoryRepository.findAll();
        assertThat(modelCategoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = modelCategoryRepository.findAll().size();
        // set the field null
        modelCategory.setCreatedBy(null);

        // Create the ModelCategory, which fails.

        restModelCategoryMockMvc.perform(post("/api/model-categories").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(modelCategory)))
            .andExpect(status().isBadRequest());

        List<ModelCategory> modelCategoryList = modelCategoryRepository.findAll();
        assertThat(modelCategoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = modelCategoryRepository.findAll().size();
        // set the field null
        modelCategory.setLastUpdatedDate(null);

        // Create the ModelCategory, which fails.

        restModelCategoryMockMvc.perform(post("/api/model-categories").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(modelCategory)))
            .andExpect(status().isBadRequest());

        List<ModelCategory> modelCategoryList = modelCategoryRepository.findAll();
        assertThat(modelCategoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = modelCategoryRepository.findAll().size();
        // set the field null
        modelCategory.setLastUpdatedBy(null);

        // Create the ModelCategory, which fails.

        restModelCategoryMockMvc.perform(post("/api/model-categories").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(modelCategory)))
            .andExpect(status().isBadRequest());

        List<ModelCategory> modelCategoryList = modelCategoryRepository.findAll();
        assertThat(modelCategoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTenantIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = modelCategoryRepository.findAll().size();
        // set the field null
        modelCategory.setTenantId(null);

        // Create the ModelCategory, which fails.

        restModelCategoryMockMvc.perform(post("/api/model-categories").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(modelCategory)))
            .andExpect(status().isBadRequest());

        List<ModelCategory> modelCategoryList = modelCategoryRepository.findAll();
        assertThat(modelCategoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllModelCategories() throws Exception {
        // Initialize the database
        modelCategoryRepository.save(modelCategory);

        // Get all the modelCategoryList
        restModelCategoryMockMvc.perform(get("/api/model-categories?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(modelCategory.getId())))
            .andExpect(jsonPath("$.[*].modelCate").value(hasItem(DEFAULT_MODEL_CATE)))
            .andExpect(jsonPath("$.[*].parentModelCate").value(hasItem(DEFAULT_PARENT_MODEL_CATE)))
            .andExpect(jsonPath("$.[*].modelCateDesc").value(hasItem(DEFAULT_MODEL_CATE_DESC)))
            .andExpect(jsonPath("$.[*].lockCount").value(hasItem(DEFAULT_LOCK_COUNT)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].lastUpdatedDate").value(hasItem(DEFAULT_LAST_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].tenantId").value(hasItem(DEFAULT_TENANT_ID)));
    }
    
    @Test
    public void getModelCategory() throws Exception {
        // Initialize the database
        modelCategoryRepository.save(modelCategory);

        // Get the modelCategory
        restModelCategoryMockMvc.perform(get("/api/model-categories/{id}", modelCategory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(modelCategory.getId()))
            .andExpect(jsonPath("$.modelCate").value(DEFAULT_MODEL_CATE))
            .andExpect(jsonPath("$.parentModelCate").value(DEFAULT_PARENT_MODEL_CATE))
            .andExpect(jsonPath("$.modelCateDesc").value(DEFAULT_MODEL_CATE_DESC))
            .andExpect(jsonPath("$.lockCount").value(DEFAULT_LOCK_COUNT))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.lastUpdatedDate").value(DEFAULT_LAST_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY))
            .andExpect(jsonPath("$.tenantId").value(DEFAULT_TENANT_ID));
    }

    @Test
    public void getNonExistingModelCategory() throws Exception {
        // Get the modelCategory
        restModelCategoryMockMvc.perform(get("/api/model-categories/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateModelCategory() throws Exception {
        // Initialize the database
        modelCategoryService.save(modelCategory);

        int databaseSizeBeforeUpdate = modelCategoryRepository.findAll().size();

        // Update the modelCategory
        ModelCategory updatedModelCategory = modelCategoryRepository.findById(modelCategory.getId()).get();
        updatedModelCategory
            .modelCate(UPDATED_MODEL_CATE)
            .parentModelCate(UPDATED_PARENT_MODEL_CATE)
            .modelCateDesc(UPDATED_MODEL_CATE_DESC)
            .lockCount(UPDATED_LOCK_COUNT)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);

        restModelCategoryMockMvc.perform(put("/api/model-categories").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedModelCategory)))
            .andExpect(status().isOk());

        // Validate the ModelCategory in the database
        List<ModelCategory> modelCategoryList = modelCategoryRepository.findAll();
        assertThat(modelCategoryList).hasSize(databaseSizeBeforeUpdate);
        ModelCategory testModelCategory = modelCategoryList.get(modelCategoryList.size() - 1);
        assertThat(testModelCategory.getModelCate()).isEqualTo(UPDATED_MODEL_CATE);
        assertThat(testModelCategory.getParentModelCate()).isEqualTo(UPDATED_PARENT_MODEL_CATE);
        assertThat(testModelCategory.getModelCateDesc()).isEqualTo(UPDATED_MODEL_CATE_DESC);
        assertThat(testModelCategory.getLockCount()).isEqualTo(UPDATED_LOCK_COUNT);
        assertThat(testModelCategory.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testModelCategory.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testModelCategory.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testModelCategory.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
        assertThat(testModelCategory.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    public void updateNonExistingModelCategory() throws Exception {
        int databaseSizeBeforeUpdate = modelCategoryRepository.findAll().size();

        // Create the ModelCategory

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restModelCategoryMockMvc.perform(put("/api/model-categories").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(modelCategory)))
            .andExpect(status().isBadRequest());

        // Validate the ModelCategory in the database
        List<ModelCategory> modelCategoryList = modelCategoryRepository.findAll();
        assertThat(modelCategoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteModelCategory() throws Exception {
        // Initialize the database
        modelCategoryService.save(modelCategory);

        int databaseSizeBeforeDelete = modelCategoryRepository.findAll().size();

        // Delete the modelCategory
        restModelCategoryMockMvc.perform(delete("/api/model-categories/{id}", modelCategory.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ModelCategory> modelCategoryList = modelCategoryRepository.findAll();
        assertThat(modelCategoryList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
