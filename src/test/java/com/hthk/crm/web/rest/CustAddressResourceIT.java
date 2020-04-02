package com.hthk.crm.web.rest;

import com.hthk.crm.CrmwebApp;
import com.hthk.crm.config.TestSecurityConfiguration;
import com.hthk.crm.domain.CustAddress;
import com.hthk.crm.repository.CustAddressRepository;
import com.hthk.crm.service.CustAddressService;

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

import com.hthk.crm.domain.enumeration.AddressType;
import com.hthk.crm.domain.enumeration.Language;
/**
 * Integration tests for the {@link CustAddressResource} REST controller.
 */
@SpringBootTest(classes = { CrmwebApp.class, TestSecurityConfiguration.class })

@AutoConfigureMockMvc
@WithMockUser
public class CustAddressResourceIT {

    private static final Long DEFAULT_ADDRESS_ID = 1L;
    private static final Long UPDATED_ADDRESS_ID = 2L;

    private static final AddressType DEFAULT_ADDRESS_TYPE = AddressType.BILLING;
    private static final AddressType UPDATED_ADDRESS_TYPE = AddressType.DELIVERY;

    private static final Language DEFAULT_ADDRESS_LANG = Language.CHINESE;
    private static final Language UPDATED_ADDRESS_LANG = Language.ENGLISH;

    private static final String DEFAULT_LOCKER_CODE = "AAAAAAAAAA";
    private static final String UPDATED_LOCKER_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_RECEIVER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_RECEIVER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_RECEIVER_CONTACT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_RECEIVER_CONTACT_NUMBER = "BBBBBBBBBB";

    private static final Boolean DEFAULT_FORMATTED_ADDRESS = false;
    private static final Boolean UPDATED_FORMATTED_ADDRESS = true;

    private static final String DEFAULT_ROOM = "AAAAAAAAAA";
    private static final String UPDATED_ROOM = "BBBBBBBBBB";

    private static final String DEFAULT_FLOOR = "AAAAAAAAAA";
    private static final String UPDATED_FLOOR = "BBBBBBBBBB";

    private static final String DEFAULT_BLOCK = "AAAAAAAAAA";
    private static final String UPDATED_BLOCK = "BBBBBBBBBB";

    private static final String DEFAULT_BUILDING = "AAAAAAAAAA";
    private static final String UPDATED_BUILDING = "BBBBBBBBBB";

    private static final String DEFAULT_STREET_ESTATE = "AAAAAAAAAA";
    private static final String UPDATED_STREET_ESTATE = "BBBBBBBBBB";

    private static final String DEFAULT_DISTRICT = "AAAAAAAAAA";
    private static final String UPDATED_DISTRICT = "BBBBBBBBBB";

    private static final String DEFAULT_REGION = "AAAAAAAAAA";
    private static final String UPDATED_REGION = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS_1 = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS_1 = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS_2 = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS_2 = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS_3 = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS_3 = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS_4 = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS_4 = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS_5 = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS_5 = "BBBBBBBBBB";

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
    private CustAddressRepository custAddressRepository;

    @Autowired
    private CustAddressService custAddressService;

    @Autowired
    private MockMvc restCustAddressMockMvc;

    private CustAddress custAddress;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CustAddress createEntity() {
        CustAddress custAddress = new CustAddress()
            .addressId(DEFAULT_ADDRESS_ID)
            .addressType(DEFAULT_ADDRESS_TYPE)
            .addressLang(DEFAULT_ADDRESS_LANG)
            .lockerCode(DEFAULT_LOCKER_CODE)
            .receiverName(DEFAULT_RECEIVER_NAME)
            .receiverContactNumber(DEFAULT_RECEIVER_CONTACT_NUMBER)
            .formattedAddress(DEFAULT_FORMATTED_ADDRESS)
            .room(DEFAULT_ROOM)
            .floor(DEFAULT_FLOOR)
            .block(DEFAULT_BLOCK)
            .building(DEFAULT_BUILDING)
            .streetEstate(DEFAULT_STREET_ESTATE)
            .district(DEFAULT_DISTRICT)
            .region(DEFAULT_REGION)
            .address1(DEFAULT_ADDRESS_1)
            .address2(DEFAULT_ADDRESS_2)
            .address3(DEFAULT_ADDRESS_3)
            .address4(DEFAULT_ADDRESS_4)
            .address5(DEFAULT_ADDRESS_5)
            .lockCount(DEFAULT_LOCK_COUNT)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdatedDate(DEFAULT_LAST_UPDATED_DATE)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY)
            .tenantId(DEFAULT_TENANT_ID);
        return custAddress;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CustAddress createUpdatedEntity() {
        CustAddress custAddress = new CustAddress()
            .addressId(UPDATED_ADDRESS_ID)
            .addressType(UPDATED_ADDRESS_TYPE)
            .addressLang(UPDATED_ADDRESS_LANG)
            .lockerCode(UPDATED_LOCKER_CODE)
            .receiverName(UPDATED_RECEIVER_NAME)
            .receiverContactNumber(UPDATED_RECEIVER_CONTACT_NUMBER)
            .formattedAddress(UPDATED_FORMATTED_ADDRESS)
            .room(UPDATED_ROOM)
            .floor(UPDATED_FLOOR)
            .block(UPDATED_BLOCK)
            .building(UPDATED_BUILDING)
            .streetEstate(UPDATED_STREET_ESTATE)
            .district(UPDATED_DISTRICT)
            .region(UPDATED_REGION)
            .address1(UPDATED_ADDRESS_1)
            .address2(UPDATED_ADDRESS_2)
            .address3(UPDATED_ADDRESS_3)
            .address4(UPDATED_ADDRESS_4)
            .address5(UPDATED_ADDRESS_5)
            .lockCount(UPDATED_LOCK_COUNT)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);
        return custAddress;
    }

    @BeforeEach
    public void initTest() {
        custAddressRepository.deleteAll();
        custAddress = createEntity();
    }

    @Test
    public void createCustAddress() throws Exception {
        int databaseSizeBeforeCreate = custAddressRepository.findAll().size();

        // Create the CustAddress
        restCustAddressMockMvc.perform(post("/api/cust-addresses").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(custAddress)))
            .andExpect(status().isCreated());

        // Validate the CustAddress in the database
        List<CustAddress> custAddressList = custAddressRepository.findAll();
        assertThat(custAddressList).hasSize(databaseSizeBeforeCreate + 1);
        CustAddress testCustAddress = custAddressList.get(custAddressList.size() - 1);
        assertThat(testCustAddress.getAddressId()).isEqualTo(DEFAULT_ADDRESS_ID);
        assertThat(testCustAddress.getAddressType()).isEqualTo(DEFAULT_ADDRESS_TYPE);
        assertThat(testCustAddress.getAddressLang()).isEqualTo(DEFAULT_ADDRESS_LANG);
        assertThat(testCustAddress.getLockerCode()).isEqualTo(DEFAULT_LOCKER_CODE);
        assertThat(testCustAddress.getReceiverName()).isEqualTo(DEFAULT_RECEIVER_NAME);
        assertThat(testCustAddress.getReceiverContactNumber()).isEqualTo(DEFAULT_RECEIVER_CONTACT_NUMBER);
        assertThat(testCustAddress.isFormattedAddress()).isEqualTo(DEFAULT_FORMATTED_ADDRESS);
        assertThat(testCustAddress.getRoom()).isEqualTo(DEFAULT_ROOM);
        assertThat(testCustAddress.getFloor()).isEqualTo(DEFAULT_FLOOR);
        assertThat(testCustAddress.getBlock()).isEqualTo(DEFAULT_BLOCK);
        assertThat(testCustAddress.getBuilding()).isEqualTo(DEFAULT_BUILDING);
        assertThat(testCustAddress.getStreetEstate()).isEqualTo(DEFAULT_STREET_ESTATE);
        assertThat(testCustAddress.getDistrict()).isEqualTo(DEFAULT_DISTRICT);
        assertThat(testCustAddress.getRegion()).isEqualTo(DEFAULT_REGION);
        assertThat(testCustAddress.getAddress1()).isEqualTo(DEFAULT_ADDRESS_1);
        assertThat(testCustAddress.getAddress2()).isEqualTo(DEFAULT_ADDRESS_2);
        assertThat(testCustAddress.getAddress3()).isEqualTo(DEFAULT_ADDRESS_3);
        assertThat(testCustAddress.getAddress4()).isEqualTo(DEFAULT_ADDRESS_4);
        assertThat(testCustAddress.getAddress5()).isEqualTo(DEFAULT_ADDRESS_5);
        assertThat(testCustAddress.getLockCount()).isEqualTo(DEFAULT_LOCK_COUNT);
        assertThat(testCustAddress.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testCustAddress.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testCustAddress.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testCustAddress.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
        assertThat(testCustAddress.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
    }

    @Test
    public void createCustAddressWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = custAddressRepository.findAll().size();

        // Create the CustAddress with an existing ID
        custAddress.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restCustAddressMockMvc.perform(post("/api/cust-addresses").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(custAddress)))
            .andExpect(status().isBadRequest());

        // Validate the CustAddress in the database
        List<CustAddress> custAddressList = custAddressRepository.findAll();
        assertThat(custAddressList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkAddressIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = custAddressRepository.findAll().size();
        // set the field null
        custAddress.setAddressId(null);

        // Create the CustAddress, which fails.

        restCustAddressMockMvc.perform(post("/api/cust-addresses").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(custAddress)))
            .andExpect(status().isBadRequest());

        List<CustAddress> custAddressList = custAddressRepository.findAll();
        assertThat(custAddressList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkAddressTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = custAddressRepository.findAll().size();
        // set the field null
        custAddress.setAddressType(null);

        // Create the CustAddress, which fails.

        restCustAddressMockMvc.perform(post("/api/cust-addresses").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(custAddress)))
            .andExpect(status().isBadRequest());

        List<CustAddress> custAddressList = custAddressRepository.findAll();
        assertThat(custAddressList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkAddressLangIsRequired() throws Exception {
        int databaseSizeBeforeTest = custAddressRepository.findAll().size();
        // set the field null
        custAddress.setAddressLang(null);

        // Create the CustAddress, which fails.

        restCustAddressMockMvc.perform(post("/api/cust-addresses").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(custAddress)))
            .andExpect(status().isBadRequest());

        List<CustAddress> custAddressList = custAddressRepository.findAll();
        assertThat(custAddressList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkFormattedAddressIsRequired() throws Exception {
        int databaseSizeBeforeTest = custAddressRepository.findAll().size();
        // set the field null
        custAddress.setFormattedAddress(null);

        // Create the CustAddress, which fails.

        restCustAddressMockMvc.perform(post("/api/cust-addresses").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(custAddress)))
            .andExpect(status().isBadRequest());

        List<CustAddress> custAddressList = custAddressRepository.findAll();
        assertThat(custAddressList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = custAddressRepository.findAll().size();
        // set the field null
        custAddress.setCreatedDate(null);

        // Create the CustAddress, which fails.

        restCustAddressMockMvc.perform(post("/api/cust-addresses").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(custAddress)))
            .andExpect(status().isBadRequest());

        List<CustAddress> custAddressList = custAddressRepository.findAll();
        assertThat(custAddressList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = custAddressRepository.findAll().size();
        // set the field null
        custAddress.setCreatedBy(null);

        // Create the CustAddress, which fails.

        restCustAddressMockMvc.perform(post("/api/cust-addresses").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(custAddress)))
            .andExpect(status().isBadRequest());

        List<CustAddress> custAddressList = custAddressRepository.findAll();
        assertThat(custAddressList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = custAddressRepository.findAll().size();
        // set the field null
        custAddress.setLastUpdatedDate(null);

        // Create the CustAddress, which fails.

        restCustAddressMockMvc.perform(post("/api/cust-addresses").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(custAddress)))
            .andExpect(status().isBadRequest());

        List<CustAddress> custAddressList = custAddressRepository.findAll();
        assertThat(custAddressList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = custAddressRepository.findAll().size();
        // set the field null
        custAddress.setLastUpdatedBy(null);

        // Create the CustAddress, which fails.

        restCustAddressMockMvc.perform(post("/api/cust-addresses").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(custAddress)))
            .andExpect(status().isBadRequest());

        List<CustAddress> custAddressList = custAddressRepository.findAll();
        assertThat(custAddressList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTenantIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = custAddressRepository.findAll().size();
        // set the field null
        custAddress.setTenantId(null);

        // Create the CustAddress, which fails.

        restCustAddressMockMvc.perform(post("/api/cust-addresses").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(custAddress)))
            .andExpect(status().isBadRequest());

        List<CustAddress> custAddressList = custAddressRepository.findAll();
        assertThat(custAddressList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllCustAddresses() throws Exception {
        // Initialize the database
        custAddressRepository.save(custAddress);

        // Get all the custAddressList
        restCustAddressMockMvc.perform(get("/api/cust-addresses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(custAddress.getId())))
            .andExpect(jsonPath("$.[*].addressId").value(hasItem(DEFAULT_ADDRESS_ID.intValue())))
            .andExpect(jsonPath("$.[*].addressType").value(hasItem(DEFAULT_ADDRESS_TYPE.toString())))
            .andExpect(jsonPath("$.[*].addressLang").value(hasItem(DEFAULT_ADDRESS_LANG.toString())))
            .andExpect(jsonPath("$.[*].lockerCode").value(hasItem(DEFAULT_LOCKER_CODE)))
            .andExpect(jsonPath("$.[*].receiverName").value(hasItem(DEFAULT_RECEIVER_NAME)))
            .andExpect(jsonPath("$.[*].receiverContactNumber").value(hasItem(DEFAULT_RECEIVER_CONTACT_NUMBER)))
            .andExpect(jsonPath("$.[*].formattedAddress").value(hasItem(DEFAULT_FORMATTED_ADDRESS.booleanValue())))
            .andExpect(jsonPath("$.[*].room").value(hasItem(DEFAULT_ROOM)))
            .andExpect(jsonPath("$.[*].floor").value(hasItem(DEFAULT_FLOOR)))
            .andExpect(jsonPath("$.[*].block").value(hasItem(DEFAULT_BLOCK)))
            .andExpect(jsonPath("$.[*].building").value(hasItem(DEFAULT_BUILDING)))
            .andExpect(jsonPath("$.[*].streetEstate").value(hasItem(DEFAULT_STREET_ESTATE)))
            .andExpect(jsonPath("$.[*].district").value(hasItem(DEFAULT_DISTRICT)))
            .andExpect(jsonPath("$.[*].region").value(hasItem(DEFAULT_REGION)))
            .andExpect(jsonPath("$.[*].address1").value(hasItem(DEFAULT_ADDRESS_1)))
            .andExpect(jsonPath("$.[*].address2").value(hasItem(DEFAULT_ADDRESS_2)))
            .andExpect(jsonPath("$.[*].address3").value(hasItem(DEFAULT_ADDRESS_3)))
            .andExpect(jsonPath("$.[*].address4").value(hasItem(DEFAULT_ADDRESS_4)))
            .andExpect(jsonPath("$.[*].address5").value(hasItem(DEFAULT_ADDRESS_5)))
            .andExpect(jsonPath("$.[*].lockCount").value(hasItem(DEFAULT_LOCK_COUNT)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].lastUpdatedDate").value(hasItem(DEFAULT_LAST_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].tenantId").value(hasItem(DEFAULT_TENANT_ID)));
    }
    
    @Test
    public void getCustAddress() throws Exception {
        // Initialize the database
        custAddressRepository.save(custAddress);

        // Get the custAddress
        restCustAddressMockMvc.perform(get("/api/cust-addresses/{id}", custAddress.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(custAddress.getId()))
            .andExpect(jsonPath("$.addressId").value(DEFAULT_ADDRESS_ID.intValue()))
            .andExpect(jsonPath("$.addressType").value(DEFAULT_ADDRESS_TYPE.toString()))
            .andExpect(jsonPath("$.addressLang").value(DEFAULT_ADDRESS_LANG.toString()))
            .andExpect(jsonPath("$.lockerCode").value(DEFAULT_LOCKER_CODE))
            .andExpect(jsonPath("$.receiverName").value(DEFAULT_RECEIVER_NAME))
            .andExpect(jsonPath("$.receiverContactNumber").value(DEFAULT_RECEIVER_CONTACT_NUMBER))
            .andExpect(jsonPath("$.formattedAddress").value(DEFAULT_FORMATTED_ADDRESS.booleanValue()))
            .andExpect(jsonPath("$.room").value(DEFAULT_ROOM))
            .andExpect(jsonPath("$.floor").value(DEFAULT_FLOOR))
            .andExpect(jsonPath("$.block").value(DEFAULT_BLOCK))
            .andExpect(jsonPath("$.building").value(DEFAULT_BUILDING))
            .andExpect(jsonPath("$.streetEstate").value(DEFAULT_STREET_ESTATE))
            .andExpect(jsonPath("$.district").value(DEFAULT_DISTRICT))
            .andExpect(jsonPath("$.region").value(DEFAULT_REGION))
            .andExpect(jsonPath("$.address1").value(DEFAULT_ADDRESS_1))
            .andExpect(jsonPath("$.address2").value(DEFAULT_ADDRESS_2))
            .andExpect(jsonPath("$.address3").value(DEFAULT_ADDRESS_3))
            .andExpect(jsonPath("$.address4").value(DEFAULT_ADDRESS_4))
            .andExpect(jsonPath("$.address5").value(DEFAULT_ADDRESS_5))
            .andExpect(jsonPath("$.lockCount").value(DEFAULT_LOCK_COUNT))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.lastUpdatedDate").value(DEFAULT_LAST_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY))
            .andExpect(jsonPath("$.tenantId").value(DEFAULT_TENANT_ID));
    }

    @Test
    public void getNonExistingCustAddress() throws Exception {
        // Get the custAddress
        restCustAddressMockMvc.perform(get("/api/cust-addresses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateCustAddress() throws Exception {
        // Initialize the database
        custAddressService.save(custAddress);

        int databaseSizeBeforeUpdate = custAddressRepository.findAll().size();

        // Update the custAddress
        CustAddress updatedCustAddress = custAddressRepository.findById(custAddress.getId()).get();
        updatedCustAddress
            .addressId(UPDATED_ADDRESS_ID)
            .addressType(UPDATED_ADDRESS_TYPE)
            .addressLang(UPDATED_ADDRESS_LANG)
            .lockerCode(UPDATED_LOCKER_CODE)
            .receiverName(UPDATED_RECEIVER_NAME)
            .receiverContactNumber(UPDATED_RECEIVER_CONTACT_NUMBER)
            .formattedAddress(UPDATED_FORMATTED_ADDRESS)
            .room(UPDATED_ROOM)
            .floor(UPDATED_FLOOR)
            .block(UPDATED_BLOCK)
            .building(UPDATED_BUILDING)
            .streetEstate(UPDATED_STREET_ESTATE)
            .district(UPDATED_DISTRICT)
            .region(UPDATED_REGION)
            .address1(UPDATED_ADDRESS_1)
            .address2(UPDATED_ADDRESS_2)
            .address3(UPDATED_ADDRESS_3)
            .address4(UPDATED_ADDRESS_4)
            .address5(UPDATED_ADDRESS_5)
            .lockCount(UPDATED_LOCK_COUNT)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);

        restCustAddressMockMvc.perform(put("/api/cust-addresses").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedCustAddress)))
            .andExpect(status().isOk());

        // Validate the CustAddress in the database
        List<CustAddress> custAddressList = custAddressRepository.findAll();
        assertThat(custAddressList).hasSize(databaseSizeBeforeUpdate);
        CustAddress testCustAddress = custAddressList.get(custAddressList.size() - 1);
        assertThat(testCustAddress.getAddressId()).isEqualTo(UPDATED_ADDRESS_ID);
        assertThat(testCustAddress.getAddressType()).isEqualTo(UPDATED_ADDRESS_TYPE);
        assertThat(testCustAddress.getAddressLang()).isEqualTo(UPDATED_ADDRESS_LANG);
        assertThat(testCustAddress.getLockerCode()).isEqualTo(UPDATED_LOCKER_CODE);
        assertThat(testCustAddress.getReceiverName()).isEqualTo(UPDATED_RECEIVER_NAME);
        assertThat(testCustAddress.getReceiverContactNumber()).isEqualTo(UPDATED_RECEIVER_CONTACT_NUMBER);
        assertThat(testCustAddress.isFormattedAddress()).isEqualTo(UPDATED_FORMATTED_ADDRESS);
        assertThat(testCustAddress.getRoom()).isEqualTo(UPDATED_ROOM);
        assertThat(testCustAddress.getFloor()).isEqualTo(UPDATED_FLOOR);
        assertThat(testCustAddress.getBlock()).isEqualTo(UPDATED_BLOCK);
        assertThat(testCustAddress.getBuilding()).isEqualTo(UPDATED_BUILDING);
        assertThat(testCustAddress.getStreetEstate()).isEqualTo(UPDATED_STREET_ESTATE);
        assertThat(testCustAddress.getDistrict()).isEqualTo(UPDATED_DISTRICT);
        assertThat(testCustAddress.getRegion()).isEqualTo(UPDATED_REGION);
        assertThat(testCustAddress.getAddress1()).isEqualTo(UPDATED_ADDRESS_1);
        assertThat(testCustAddress.getAddress2()).isEqualTo(UPDATED_ADDRESS_2);
        assertThat(testCustAddress.getAddress3()).isEqualTo(UPDATED_ADDRESS_3);
        assertThat(testCustAddress.getAddress4()).isEqualTo(UPDATED_ADDRESS_4);
        assertThat(testCustAddress.getAddress5()).isEqualTo(UPDATED_ADDRESS_5);
        assertThat(testCustAddress.getLockCount()).isEqualTo(UPDATED_LOCK_COUNT);
        assertThat(testCustAddress.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testCustAddress.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testCustAddress.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testCustAddress.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
        assertThat(testCustAddress.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    public void updateNonExistingCustAddress() throws Exception {
        int databaseSizeBeforeUpdate = custAddressRepository.findAll().size();

        // Create the CustAddress

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCustAddressMockMvc.perform(put("/api/cust-addresses").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(custAddress)))
            .andExpect(status().isBadRequest());

        // Validate the CustAddress in the database
        List<CustAddress> custAddressList = custAddressRepository.findAll();
        assertThat(custAddressList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteCustAddress() throws Exception {
        // Initialize the database
        custAddressService.save(custAddress);

        int databaseSizeBeforeDelete = custAddressRepository.findAll().size();

        // Delete the custAddress
        restCustAddressMockMvc.perform(delete("/api/cust-addresses/{id}", custAddress.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CustAddress> custAddressList = custAddressRepository.findAll();
        assertThat(custAddressList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
