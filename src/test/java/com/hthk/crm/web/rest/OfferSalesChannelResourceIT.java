package com.hthk.crm.web.rest;

import com.hthk.crm.CrmwebApp;
import com.hthk.crm.config.TestSecurityConfiguration;
import com.hthk.crm.domain.OfferSalesChannel;
import com.hthk.crm.repository.OfferSalesChannelRepository;
import com.hthk.crm.service.OfferSalesChannelService;

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
 * Integration tests for the {@link OfferSalesChannelResource} REST controller.
 */
@SpringBootTest(classes = { CrmwebApp.class, TestSecurityConfiguration.class })

@AutoConfigureMockMvc
@WithMockUser
public class OfferSalesChannelResourceIT {

    private static final String DEFAULT_SALES_CHANNEL = "AAAAAAAAAA";
    private static final String UPDATED_SALES_CHANNEL = "BBBBBBBBBB";

    @Autowired
    private OfferSalesChannelRepository offerSalesChannelRepository;

    @Autowired
    private OfferSalesChannelService offerSalesChannelService;

    @Autowired
    private MockMvc restOfferSalesChannelMockMvc;

    private OfferSalesChannel offerSalesChannel;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OfferSalesChannel createEntity() {
        OfferSalesChannel offerSalesChannel = new OfferSalesChannel()
            .salesChannel(DEFAULT_SALES_CHANNEL);
        return offerSalesChannel;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OfferSalesChannel createUpdatedEntity() {
        OfferSalesChannel offerSalesChannel = new OfferSalesChannel()
            .salesChannel(UPDATED_SALES_CHANNEL);
        return offerSalesChannel;
    }

    @BeforeEach
    public void initTest() {
        offerSalesChannelRepository.deleteAll();
        offerSalesChannel = createEntity();
    }

    @Test
    public void createOfferSalesChannel() throws Exception {
        int databaseSizeBeforeCreate = offerSalesChannelRepository.findAll().size();

        // Create the OfferSalesChannel
        restOfferSalesChannelMockMvc.perform(post("/api/offer-sales-channels").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offerSalesChannel)))
            .andExpect(status().isCreated());

        // Validate the OfferSalesChannel in the database
        List<OfferSalesChannel> offerSalesChannelList = offerSalesChannelRepository.findAll();
        assertThat(offerSalesChannelList).hasSize(databaseSizeBeforeCreate + 1);
        OfferSalesChannel testOfferSalesChannel = offerSalesChannelList.get(offerSalesChannelList.size() - 1);
        assertThat(testOfferSalesChannel.getSalesChannel()).isEqualTo(DEFAULT_SALES_CHANNEL);
    }

    @Test
    public void createOfferSalesChannelWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = offerSalesChannelRepository.findAll().size();

        // Create the OfferSalesChannel with an existing ID
        offerSalesChannel.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restOfferSalesChannelMockMvc.perform(post("/api/offer-sales-channels").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offerSalesChannel)))
            .andExpect(status().isBadRequest());

        // Validate the OfferSalesChannel in the database
        List<OfferSalesChannel> offerSalesChannelList = offerSalesChannelRepository.findAll();
        assertThat(offerSalesChannelList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllOfferSalesChannels() throws Exception {
        // Initialize the database
        offerSalesChannelRepository.save(offerSalesChannel);

        // Get all the offerSalesChannelList
        restOfferSalesChannelMockMvc.perform(get("/api/offer-sales-channels?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(offerSalesChannel.getId())))
            .andExpect(jsonPath("$.[*].salesChannel").value(hasItem(DEFAULT_SALES_CHANNEL)));
    }
    
    @Test
    public void getOfferSalesChannel() throws Exception {
        // Initialize the database
        offerSalesChannelRepository.save(offerSalesChannel);

        // Get the offerSalesChannel
        restOfferSalesChannelMockMvc.perform(get("/api/offer-sales-channels/{id}", offerSalesChannel.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(offerSalesChannel.getId()))
            .andExpect(jsonPath("$.salesChannel").value(DEFAULT_SALES_CHANNEL));
    }

    @Test
    public void getNonExistingOfferSalesChannel() throws Exception {
        // Get the offerSalesChannel
        restOfferSalesChannelMockMvc.perform(get("/api/offer-sales-channels/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateOfferSalesChannel() throws Exception {
        // Initialize the database
        offerSalesChannelService.save(offerSalesChannel);

        int databaseSizeBeforeUpdate = offerSalesChannelRepository.findAll().size();

        // Update the offerSalesChannel
        OfferSalesChannel updatedOfferSalesChannel = offerSalesChannelRepository.findById(offerSalesChannel.getId()).get();
        updatedOfferSalesChannel
            .salesChannel(UPDATED_SALES_CHANNEL);

        restOfferSalesChannelMockMvc.perform(put("/api/offer-sales-channels").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedOfferSalesChannel)))
            .andExpect(status().isOk());

        // Validate the OfferSalesChannel in the database
        List<OfferSalesChannel> offerSalesChannelList = offerSalesChannelRepository.findAll();
        assertThat(offerSalesChannelList).hasSize(databaseSizeBeforeUpdate);
        OfferSalesChannel testOfferSalesChannel = offerSalesChannelList.get(offerSalesChannelList.size() - 1);
        assertThat(testOfferSalesChannel.getSalesChannel()).isEqualTo(UPDATED_SALES_CHANNEL);
    }

    @Test
    public void updateNonExistingOfferSalesChannel() throws Exception {
        int databaseSizeBeforeUpdate = offerSalesChannelRepository.findAll().size();

        // Create the OfferSalesChannel

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOfferSalesChannelMockMvc.perform(put("/api/offer-sales-channels").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offerSalesChannel)))
            .andExpect(status().isBadRequest());

        // Validate the OfferSalesChannel in the database
        List<OfferSalesChannel> offerSalesChannelList = offerSalesChannelRepository.findAll();
        assertThat(offerSalesChannelList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteOfferSalesChannel() throws Exception {
        // Initialize the database
        offerSalesChannelService.save(offerSalesChannel);

        int databaseSizeBeforeDelete = offerSalesChannelRepository.findAll().size();

        // Delete the offerSalesChannel
        restOfferSalesChannelMockMvc.perform(delete("/api/offer-sales-channels/{id}", offerSalesChannel.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<OfferSalesChannel> offerSalesChannelList = offerSalesChannelRepository.findAll();
        assertThat(offerSalesChannelList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
