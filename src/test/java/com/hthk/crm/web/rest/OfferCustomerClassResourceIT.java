package com.hthk.crm.web.rest;

import com.hthk.crm.CrmwebApp;
import com.hthk.crm.config.TestSecurityConfiguration;
import com.hthk.crm.domain.OfferCustomerClass;
import com.hthk.crm.repository.OfferCustomerClassRepository;
import com.hthk.crm.service.OfferCustomerClassService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link OfferCustomerClassResource} REST controller.
 */
@SpringBootTest(classes = { CrmwebApp.class, TestSecurityConfiguration.class })

@AutoConfigureMockMvc
@WithMockUser
public class OfferCustomerClassResourceIT {

    private static final String DEFAULT_CUSTOMER_CLASS = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_CLASS = "BBBBBBBBBB";

    @Autowired
    private OfferCustomerClassRepository offerCustomerClassRepository;

    @Autowired
    private OfferCustomerClassService offerCustomerClassService;

    @Autowired
    private MockMvc restOfferCustomerClassMockMvc;

    private OfferCustomerClass offerCustomerClass;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OfferCustomerClass createEntity() {
        OfferCustomerClass offerCustomerClass = new OfferCustomerClass()
            .customerClass(DEFAULT_CUSTOMER_CLASS);
        return offerCustomerClass;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OfferCustomerClass createUpdatedEntity() {
        OfferCustomerClass offerCustomerClass = new OfferCustomerClass()
            .customerClass(UPDATED_CUSTOMER_CLASS);
        return offerCustomerClass;
    }

    @BeforeEach
    public void initTest() {
        offerCustomerClassRepository.deleteAll();
        offerCustomerClass = createEntity();
    }

    @Test
    public void createOfferCustomerClass() throws Exception {
        int databaseSizeBeforeCreate = offerCustomerClassRepository.findAll().size();

        // Create the OfferCustomerClass
        restOfferCustomerClassMockMvc.perform(post("/api/offer-customer-classes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offerCustomerClass)))
            .andExpect(status().isCreated());

        // Validate the OfferCustomerClass in the database
        List<OfferCustomerClass> offerCustomerClassList = offerCustomerClassRepository.findAll();
        assertThat(offerCustomerClassList).hasSize(databaseSizeBeforeCreate + 1);
        OfferCustomerClass testOfferCustomerClass = offerCustomerClassList.get(offerCustomerClassList.size() - 1);
        assertThat(testOfferCustomerClass.getCustomerClass()).isEqualTo(DEFAULT_CUSTOMER_CLASS);
    }

    @Test
    public void createOfferCustomerClassWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = offerCustomerClassRepository.findAll().size();

        // Create the OfferCustomerClass with an existing ID
        offerCustomerClass.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restOfferCustomerClassMockMvc.perform(post("/api/offer-customer-classes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offerCustomerClass)))
            .andExpect(status().isBadRequest());

        // Validate the OfferCustomerClass in the database
        List<OfferCustomerClass> offerCustomerClassList = offerCustomerClassRepository.findAll();
        assertThat(offerCustomerClassList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllOfferCustomerClasses() throws Exception {
        // Initialize the database
        offerCustomerClassRepository.save(offerCustomerClass);

        // Get all the offerCustomerClassList
        restOfferCustomerClassMockMvc.perform(get("/api/offer-customer-classes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(offerCustomerClass.getId())))
            .andExpect(jsonPath("$.[*].customerClass").value(hasItem(DEFAULT_CUSTOMER_CLASS)));
    }
    
    @Test
    public void getOfferCustomerClass() throws Exception {
        // Initialize the database
        offerCustomerClassRepository.save(offerCustomerClass);

        // Get the offerCustomerClass
        restOfferCustomerClassMockMvc.perform(get("/api/offer-customer-classes/{id}", offerCustomerClass.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(offerCustomerClass.getId()))
            .andExpect(jsonPath("$.customerClass").value(DEFAULT_CUSTOMER_CLASS));
    }

    @Test
    public void getNonExistingOfferCustomerClass() throws Exception {
        // Get the offerCustomerClass
        restOfferCustomerClassMockMvc.perform(get("/api/offer-customer-classes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateOfferCustomerClass() throws Exception {
        // Initialize the database
        offerCustomerClassService.save(offerCustomerClass);

        int databaseSizeBeforeUpdate = offerCustomerClassRepository.findAll().size();

        // Update the offerCustomerClass
        OfferCustomerClass updatedOfferCustomerClass = offerCustomerClassRepository.findById(offerCustomerClass.getId()).get();
        updatedOfferCustomerClass
            .customerClass(UPDATED_CUSTOMER_CLASS);

        restOfferCustomerClassMockMvc.perform(put("/api/offer-customer-classes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedOfferCustomerClass)))
            .andExpect(status().isOk());

        // Validate the OfferCustomerClass in the database
        List<OfferCustomerClass> offerCustomerClassList = offerCustomerClassRepository.findAll();
        assertThat(offerCustomerClassList).hasSize(databaseSizeBeforeUpdate);
        OfferCustomerClass testOfferCustomerClass = offerCustomerClassList.get(offerCustomerClassList.size() - 1);
        assertThat(testOfferCustomerClass.getCustomerClass()).isEqualTo(UPDATED_CUSTOMER_CLASS);
    }

    @Test
    public void updateNonExistingOfferCustomerClass() throws Exception {
        int databaseSizeBeforeUpdate = offerCustomerClassRepository.findAll().size();

        // Create the OfferCustomerClass

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOfferCustomerClassMockMvc.perform(put("/api/offer-customer-classes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offerCustomerClass)))
            .andExpect(status().isBadRequest());

        // Validate the OfferCustomerClass in the database
        List<OfferCustomerClass> offerCustomerClassList = offerCustomerClassRepository.findAll();
        assertThat(offerCustomerClassList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteOfferCustomerClass() throws Exception {
        // Initialize the database
        offerCustomerClassService.save(offerCustomerClass);

        int databaseSizeBeforeDelete = offerCustomerClassRepository.findAll().size();

        // Delete the offerCustomerClass
        restOfferCustomerClassMockMvc.perform(delete("/api/offer-customer-classes/{id}", offerCustomerClass.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<OfferCustomerClass> offerCustomerClassList = offerCustomerClassRepository.findAll();
        assertThat(offerCustomerClassList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
