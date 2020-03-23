package com.hthk.crm.web.rest;

import com.hthk.crm.CrmwebApp;
import com.hthk.crm.config.TestSecurityConfiguration;
import com.hthk.crm.domain.EfLockerLocation;
import com.hthk.crm.repository.EfLockerLocationRepository;
import com.hthk.crm.service.EfLockerLocationService;

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
 * Integration tests for the {@link EfLockerLocationResource} REST controller.
 */
@SpringBootTest(classes = { CrmwebApp.class, TestSecurityConfiguration.class })

@AutoConfigureMockMvc
@WithMockUser
public class EfLockerLocationResourceIT {

    private static final String DEFAULT_EF_LOCKER_CODE = "AAAAAAAAAA";
    private static final String UPDATED_EF_LOCKER_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_REGION_ENG = "AAAAAAAAAA";
    private static final String UPDATED_REGION_ENG = "BBBBBBBBBB";

    private static final String DEFAULT_AREA_ENG = "AAAAAAAAAA";
    private static final String UPDATED_AREA_ENG = "BBBBBBBBBB";

    private static final String DEFAULT_FULL_ADDRESS_ENG = "AAAAAAAAAA";
    private static final String UPDATED_FULL_ADDRESS_ENG = "BBBBBBBBBB";

    private static final String DEFAULT_REGION_CHI = "AAAAAAAAAA";
    private static final String UPDATED_REGION_CHI = "BBBBBBBBBB";

    private static final String DEFAULT_AREA_CHI = "AAAAAAAAAA";
    private static final String UPDATED_AREA_CHI = "BBBBBBBBBB";

    private static final String DEFAULT_FULL_ADDRESS_CHI = "AAAAAAAAAA";
    private static final String UPDATED_FULL_ADDRESS_CHI = "BBBBBBBBBB";

    @Autowired
    private EfLockerLocationRepository efLockerLocationRepository;

    @Autowired
    private EfLockerLocationService efLockerLocationService;

    @Autowired
    private MockMvc restEfLockerLocationMockMvc;

    private EfLockerLocation efLockerLocation;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EfLockerLocation createEntity() {
        EfLockerLocation efLockerLocation = new EfLockerLocation()
            .efLockerCode(DEFAULT_EF_LOCKER_CODE)
            .regionEng(DEFAULT_REGION_ENG)
            .areaEng(DEFAULT_AREA_ENG)
            .fullAddressEng(DEFAULT_FULL_ADDRESS_ENG)
            .regionChi(DEFAULT_REGION_CHI)
            .areaChi(DEFAULT_AREA_CHI)
            .fullAddressChi(DEFAULT_FULL_ADDRESS_CHI);
        return efLockerLocation;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EfLockerLocation createUpdatedEntity() {
        EfLockerLocation efLockerLocation = new EfLockerLocation()
            .efLockerCode(UPDATED_EF_LOCKER_CODE)
            .regionEng(UPDATED_REGION_ENG)
            .areaEng(UPDATED_AREA_ENG)
            .fullAddressEng(UPDATED_FULL_ADDRESS_ENG)
            .regionChi(UPDATED_REGION_CHI)
            .areaChi(UPDATED_AREA_CHI)
            .fullAddressChi(UPDATED_FULL_ADDRESS_CHI);
        return efLockerLocation;
    }

    @BeforeEach
    public void initTest() {
        efLockerLocationRepository.deleteAll();
        efLockerLocation = createEntity();
    }

    @Test
    public void createEfLockerLocation() throws Exception {
        int databaseSizeBeforeCreate = efLockerLocationRepository.findAll().size();

        // Create the EfLockerLocation
        restEfLockerLocationMockMvc.perform(post("/api/ef-locker-locations").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(efLockerLocation)))
            .andExpect(status().isCreated());

        // Validate the EfLockerLocation in the database
        List<EfLockerLocation> efLockerLocationList = efLockerLocationRepository.findAll();
        assertThat(efLockerLocationList).hasSize(databaseSizeBeforeCreate + 1);
        EfLockerLocation testEfLockerLocation = efLockerLocationList.get(efLockerLocationList.size() - 1);
        assertThat(testEfLockerLocation.getEfLockerCode()).isEqualTo(DEFAULT_EF_LOCKER_CODE);
        assertThat(testEfLockerLocation.getRegionEng()).isEqualTo(DEFAULT_REGION_ENG);
        assertThat(testEfLockerLocation.getAreaEng()).isEqualTo(DEFAULT_AREA_ENG);
        assertThat(testEfLockerLocation.getFullAddressEng()).isEqualTo(DEFAULT_FULL_ADDRESS_ENG);
        assertThat(testEfLockerLocation.getRegionChi()).isEqualTo(DEFAULT_REGION_CHI);
        assertThat(testEfLockerLocation.getAreaChi()).isEqualTo(DEFAULT_AREA_CHI);
        assertThat(testEfLockerLocation.getFullAddressChi()).isEqualTo(DEFAULT_FULL_ADDRESS_CHI);
    }

    @Test
    public void createEfLockerLocationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = efLockerLocationRepository.findAll().size();

        // Create the EfLockerLocation with an existing ID
        efLockerLocation.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restEfLockerLocationMockMvc.perform(post("/api/ef-locker-locations").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(efLockerLocation)))
            .andExpect(status().isBadRequest());

        // Validate the EfLockerLocation in the database
        List<EfLockerLocation> efLockerLocationList = efLockerLocationRepository.findAll();
        assertThat(efLockerLocationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkEfLockerCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = efLockerLocationRepository.findAll().size();
        // set the field null
        efLockerLocation.setEfLockerCode(null);

        // Create the EfLockerLocation, which fails.

        restEfLockerLocationMockMvc.perform(post("/api/ef-locker-locations").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(efLockerLocation)))
            .andExpect(status().isBadRequest());

        List<EfLockerLocation> efLockerLocationList = efLockerLocationRepository.findAll();
        assertThat(efLockerLocationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllEfLockerLocations() throws Exception {
        // Initialize the database
        efLockerLocationRepository.save(efLockerLocation);

        // Get all the efLockerLocationList
        restEfLockerLocationMockMvc.perform(get("/api/ef-locker-locations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(efLockerLocation.getId())))
            .andExpect(jsonPath("$.[*].efLockerCode").value(hasItem(DEFAULT_EF_LOCKER_CODE)))
            .andExpect(jsonPath("$.[*].regionEng").value(hasItem(DEFAULT_REGION_ENG)))
            .andExpect(jsonPath("$.[*].areaEng").value(hasItem(DEFAULT_AREA_ENG)))
            .andExpect(jsonPath("$.[*].fullAddressEng").value(hasItem(DEFAULT_FULL_ADDRESS_ENG)))
            .andExpect(jsonPath("$.[*].regionChi").value(hasItem(DEFAULT_REGION_CHI)))
            .andExpect(jsonPath("$.[*].areaChi").value(hasItem(DEFAULT_AREA_CHI)))
            .andExpect(jsonPath("$.[*].fullAddressChi").value(hasItem(DEFAULT_FULL_ADDRESS_CHI)));
    }
    
    @Test
    public void getEfLockerLocation() throws Exception {
        // Initialize the database
        efLockerLocationRepository.save(efLockerLocation);

        // Get the efLockerLocation
        restEfLockerLocationMockMvc.perform(get("/api/ef-locker-locations/{id}", efLockerLocation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(efLockerLocation.getId()))
            .andExpect(jsonPath("$.efLockerCode").value(DEFAULT_EF_LOCKER_CODE))
            .andExpect(jsonPath("$.regionEng").value(DEFAULT_REGION_ENG))
            .andExpect(jsonPath("$.areaEng").value(DEFAULT_AREA_ENG))
            .andExpect(jsonPath("$.fullAddressEng").value(DEFAULT_FULL_ADDRESS_ENG))
            .andExpect(jsonPath("$.regionChi").value(DEFAULT_REGION_CHI))
            .andExpect(jsonPath("$.areaChi").value(DEFAULT_AREA_CHI))
            .andExpect(jsonPath("$.fullAddressChi").value(DEFAULT_FULL_ADDRESS_CHI));
    }

    @Test
    public void getNonExistingEfLockerLocation() throws Exception {
        // Get the efLockerLocation
        restEfLockerLocationMockMvc.perform(get("/api/ef-locker-locations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateEfLockerLocation() throws Exception {
        // Initialize the database
        efLockerLocationService.save(efLockerLocation);

        int databaseSizeBeforeUpdate = efLockerLocationRepository.findAll().size();

        // Update the efLockerLocation
        EfLockerLocation updatedEfLockerLocation = efLockerLocationRepository.findById(efLockerLocation.getId()).get();
        updatedEfLockerLocation
            .efLockerCode(UPDATED_EF_LOCKER_CODE)
            .regionEng(UPDATED_REGION_ENG)
            .areaEng(UPDATED_AREA_ENG)
            .fullAddressEng(UPDATED_FULL_ADDRESS_ENG)
            .regionChi(UPDATED_REGION_CHI)
            .areaChi(UPDATED_AREA_CHI)
            .fullAddressChi(UPDATED_FULL_ADDRESS_CHI);

        restEfLockerLocationMockMvc.perform(put("/api/ef-locker-locations").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedEfLockerLocation)))
            .andExpect(status().isOk());

        // Validate the EfLockerLocation in the database
        List<EfLockerLocation> efLockerLocationList = efLockerLocationRepository.findAll();
        assertThat(efLockerLocationList).hasSize(databaseSizeBeforeUpdate);
        EfLockerLocation testEfLockerLocation = efLockerLocationList.get(efLockerLocationList.size() - 1);
        assertThat(testEfLockerLocation.getEfLockerCode()).isEqualTo(UPDATED_EF_LOCKER_CODE);
        assertThat(testEfLockerLocation.getRegionEng()).isEqualTo(UPDATED_REGION_ENG);
        assertThat(testEfLockerLocation.getAreaEng()).isEqualTo(UPDATED_AREA_ENG);
        assertThat(testEfLockerLocation.getFullAddressEng()).isEqualTo(UPDATED_FULL_ADDRESS_ENG);
        assertThat(testEfLockerLocation.getRegionChi()).isEqualTo(UPDATED_REGION_CHI);
        assertThat(testEfLockerLocation.getAreaChi()).isEqualTo(UPDATED_AREA_CHI);
        assertThat(testEfLockerLocation.getFullAddressChi()).isEqualTo(UPDATED_FULL_ADDRESS_CHI);
    }

    @Test
    public void updateNonExistingEfLockerLocation() throws Exception {
        int databaseSizeBeforeUpdate = efLockerLocationRepository.findAll().size();

        // Create the EfLockerLocation

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEfLockerLocationMockMvc.perform(put("/api/ef-locker-locations").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(efLockerLocation)))
            .andExpect(status().isBadRequest());

        // Validate the EfLockerLocation in the database
        List<EfLockerLocation> efLockerLocationList = efLockerLocationRepository.findAll();
        assertThat(efLockerLocationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteEfLockerLocation() throws Exception {
        // Initialize the database
        efLockerLocationService.save(efLockerLocation);

        int databaseSizeBeforeDelete = efLockerLocationRepository.findAll().size();

        // Delete the efLockerLocation
        restEfLockerLocationMockMvc.perform(delete("/api/ef-locker-locations/{id}", efLockerLocation.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EfLockerLocation> efLockerLocationList = efLockerLocationRepository.findAll();
        assertThat(efLockerLocationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
