package com.hthk.crm.web.rest;

import com.hthk.crm.CrmwebApp;
import com.hthk.crm.config.TestSecurityConfiguration;
import com.hthk.crm.domain.OfferCustomerSegment;
import com.hthk.crm.repository.OfferCustomerSegmentRepository;
import com.hthk.crm.service.OfferCustomerSegmentService;

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
 * Integration tests for the {@link OfferCustomerSegmentResource} REST controller.
 */
@SpringBootTest(classes = { CrmwebApp.class, TestSecurityConfiguration.class })

@AutoConfigureMockMvc
@WithMockUser
public class OfferCustomerSegmentResourceIT {

    private static final String DEFAULT_CUSTOMER_SEGMENT = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_SEGMENT = "BBBBBBBBBB";

    @Autowired
    private OfferCustomerSegmentRepository offerCustomerSegmentRepository;

    @Autowired
    private OfferCustomerSegmentService offerCustomerSegmentService;

    @Autowired
    private MockMvc restOfferCustomerSegmentMockMvc;

    private OfferCustomerSegment offerCustomerSegment;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OfferCustomerSegment createEntity() {
        OfferCustomerSegment offerCustomerSegment = new OfferCustomerSegment()
            .customerSegment(DEFAULT_CUSTOMER_SEGMENT);
        return offerCustomerSegment;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OfferCustomerSegment createUpdatedEntity() {
        OfferCustomerSegment offerCustomerSegment = new OfferCustomerSegment()
            .customerSegment(UPDATED_CUSTOMER_SEGMENT);
        return offerCustomerSegment;
    }

    @BeforeEach
    public void initTest() {
        offerCustomerSegmentRepository.deleteAll();
        offerCustomerSegment = createEntity();
    }

    @Test
    public void createOfferCustomerSegment() throws Exception {
        int databaseSizeBeforeCreate = offerCustomerSegmentRepository.findAll().size();

        // Create the OfferCustomerSegment
        restOfferCustomerSegmentMockMvc.perform(post("/api/offer-customer-segments").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offerCustomerSegment)))
            .andExpect(status().isCreated());

        // Validate the OfferCustomerSegment in the database
        List<OfferCustomerSegment> offerCustomerSegmentList = offerCustomerSegmentRepository.findAll();
        assertThat(offerCustomerSegmentList).hasSize(databaseSizeBeforeCreate + 1);
        OfferCustomerSegment testOfferCustomerSegment = offerCustomerSegmentList.get(offerCustomerSegmentList.size() - 1);
        assertThat(testOfferCustomerSegment.getCustomerSegment()).isEqualTo(DEFAULT_CUSTOMER_SEGMENT);
    }

    @Test
    public void createOfferCustomerSegmentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = offerCustomerSegmentRepository.findAll().size();

        // Create the OfferCustomerSegment with an existing ID
        offerCustomerSegment.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restOfferCustomerSegmentMockMvc.perform(post("/api/offer-customer-segments").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offerCustomerSegment)))
            .andExpect(status().isBadRequest());

        // Validate the OfferCustomerSegment in the database
        List<OfferCustomerSegment> offerCustomerSegmentList = offerCustomerSegmentRepository.findAll();
        assertThat(offerCustomerSegmentList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllOfferCustomerSegments() throws Exception {
        // Initialize the database
        offerCustomerSegmentRepository.save(offerCustomerSegment);

        // Get all the offerCustomerSegmentList
        restOfferCustomerSegmentMockMvc.perform(get("/api/offer-customer-segments?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(offerCustomerSegment.getId())))
            .andExpect(jsonPath("$.[*].customerSegment").value(hasItem(DEFAULT_CUSTOMER_SEGMENT)));
    }
    
    @Test
    public void getOfferCustomerSegment() throws Exception {
        // Initialize the database
        offerCustomerSegmentRepository.save(offerCustomerSegment);

        // Get the offerCustomerSegment
        restOfferCustomerSegmentMockMvc.perform(get("/api/offer-customer-segments/{id}", offerCustomerSegment.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(offerCustomerSegment.getId()))
            .andExpect(jsonPath("$.customerSegment").value(DEFAULT_CUSTOMER_SEGMENT));
    }

    @Test
    public void getNonExistingOfferCustomerSegment() throws Exception {
        // Get the offerCustomerSegment
        restOfferCustomerSegmentMockMvc.perform(get("/api/offer-customer-segments/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateOfferCustomerSegment() throws Exception {
        // Initialize the database
        offerCustomerSegmentService.save(offerCustomerSegment);

        int databaseSizeBeforeUpdate = offerCustomerSegmentRepository.findAll().size();

        // Update the offerCustomerSegment
        OfferCustomerSegment updatedOfferCustomerSegment = offerCustomerSegmentRepository.findById(offerCustomerSegment.getId()).get();
        updatedOfferCustomerSegment
            .customerSegment(UPDATED_CUSTOMER_SEGMENT);

        restOfferCustomerSegmentMockMvc.perform(put("/api/offer-customer-segments").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedOfferCustomerSegment)))
            .andExpect(status().isOk());

        // Validate the OfferCustomerSegment in the database
        List<OfferCustomerSegment> offerCustomerSegmentList = offerCustomerSegmentRepository.findAll();
        assertThat(offerCustomerSegmentList).hasSize(databaseSizeBeforeUpdate);
        OfferCustomerSegment testOfferCustomerSegment = offerCustomerSegmentList.get(offerCustomerSegmentList.size() - 1);
        assertThat(testOfferCustomerSegment.getCustomerSegment()).isEqualTo(UPDATED_CUSTOMER_SEGMENT);
    }

    @Test
    public void updateNonExistingOfferCustomerSegment() throws Exception {
        int databaseSizeBeforeUpdate = offerCustomerSegmentRepository.findAll().size();

        // Create the OfferCustomerSegment

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOfferCustomerSegmentMockMvc.perform(put("/api/offer-customer-segments").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offerCustomerSegment)))
            .andExpect(status().isBadRequest());

        // Validate the OfferCustomerSegment in the database
        List<OfferCustomerSegment> offerCustomerSegmentList = offerCustomerSegmentRepository.findAll();
        assertThat(offerCustomerSegmentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteOfferCustomerSegment() throws Exception {
        // Initialize the database
        offerCustomerSegmentService.save(offerCustomerSegment);

        int databaseSizeBeforeDelete = offerCustomerSegmentRepository.findAll().size();

        // Delete the offerCustomerSegment
        restOfferCustomerSegmentMockMvc.perform(delete("/api/offer-customer-segments/{id}", offerCustomerSegment.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<OfferCustomerSegment> offerCustomerSegmentList = offerCustomerSegmentRepository.findAll();
        assertThat(offerCustomerSegmentList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
