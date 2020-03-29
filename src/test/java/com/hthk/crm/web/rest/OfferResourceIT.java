package com.hthk.crm.web.rest;

import com.hthk.crm.CrmwebApp;
import com.hthk.crm.config.TestSecurityConfiguration;
import com.hthk.crm.domain.Offer;
import com.hthk.crm.repository.OfferRepository;
import com.hthk.crm.service.OfferService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.hthk.crm.domain.enumeration.OfferType;
import com.hthk.crm.domain.enumeration.OfferPeriodTerm;
/**
 * Integration tests for the {@link OfferResource} REST controller.
 */
@SpringBootTest(classes = { CrmwebApp.class, TestSecurityConfiguration.class })
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class OfferResourceIT {

    private static final String DEFAULT_OFFER_ID = "AAAAAAAAAA";
    private static final String UPDATED_OFFER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_OFFER_EXTERNAL_ID = "AAAAAAAAAA";
    private static final String UPDATED_OFFER_EXTERNAL_ID = "BBBBBBBBBB";

    private static final String DEFAULT_OFFER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_OFFER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_OFFER_NAME_CHI = "AAAAAAAAAA";
    private static final String UPDATED_OFFER_NAME_CHI = "BBBBBBBBBB";

    private static final String DEFAULT_OFFER_DESC = "AAAAAAAAAA";
    private static final String UPDATED_OFFER_DESC = "BBBBBBBBBB";

    private static final String DEFAULT_OFFER_DESC_CHI = "AAAAAAAAAA";
    private static final String UPDATED_OFFER_DESC_CHI = "BBBBBBBBBB";

    private static final OfferType DEFAULT_OFFER_TYPE = OfferType.BASE;
    private static final OfferType UPDATED_OFFER_TYPE = OfferType.ADDON;

    private static final BigDecimal DEFAULT_OFFER_PRICE = new BigDecimal(1);
    private static final BigDecimal UPDATED_OFFER_PRICE = new BigDecimal(2);

    private static final String DEFAULT_TEMP_CUSTOMER_SEGMENTS = "AAAAAAAAAA";
    private static final String UPDATED_TEMP_CUSTOMER_SEGMENTS = "BBBBBBBBBB";

    private static final String DEFAULT_TEMP_CUSTOMER_CLASSES = "AAAAAAAAAA";
    private static final String UPDATED_TEMP_CUSTOMER_CLASSES = "BBBBBBBBBB";

    private static final String DEFAULT_TEMP_SALES_CHANNELS = "AAAAAAAAAA";
    private static final String UPDATED_TEMP_SALES_CHANNELS = "BBBBBBBBBB";

    private static final Instant DEFAULT_START_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_START_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_END_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_END_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_TEMP_CHILD_OFFER_IDS = "AAAAAAAAAA";
    private static final String UPDATED_TEMP_CHILD_OFFER_IDS = "BBBBBBBBBB";

    private static final String DEFAULT_TEMP_PRODUCT_IDS = "AAAAAAAAAA";
    private static final String UPDATED_TEMP_PRODUCT_IDS = "BBBBBBBBBB";

    private static final String DEFAULT_TEMP_ADVANCE_PAYMENT_IDS = "AAAAAAAAAA";
    private static final String UPDATED_TEMP_ADVANCE_PAYMENT_IDS = "BBBBBBBBBB";

    private static final String DEFAULT_TEMP_PROMO_CODES = "AAAAAAAAAA";
    private static final String UPDATED_TEMP_PROMO_CODES = "BBBBBBBBBB";

    private static final String DEFAULT_TEMP_DISCOUNT_CODES = "AAAAAAAAAA";
    private static final String UPDATED_TEMP_DISCOUNT_CODES = "BBBBBBBBBB";

    private static final String DEFAULT_TEMP_IMAGE_IDS = "AAAAAAAAAA";
    private static final String UPDATED_TEMP_IMAGE_IDS = "BBBBBBBBBB";

    private static final Boolean DEFAULT_LIMITED_ACTIVATION_PERIOD = false;
    private static final Boolean UPDATED_LIMITED_ACTIVATION_PERIOD = true;

    private static final Instant DEFAULT_ALLOWED_ACTIVATION_START_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_ALLOWED_ACTIVATION_START_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_ALLOWED_ACTIVATION_END_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_ALLOWED_ACTIVATION_END_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Boolean DEFAULT_IS_GROUP_SHARING_OFFER = false;
    private static final Boolean UPDATED_IS_GROUP_SHARING_OFFER = true;

    private static final Boolean DEFAULT_IS_MNP_OFFER = false;
    private static final Boolean UPDATED_IS_MNP_OFFER = true;

    private static final Boolean DEFAULT_AUTO_RENEWAL = false;
    private static final Boolean UPDATED_AUTO_RENEWAL = true;

    private static final Boolean DEFAULT_TRANSFER_ALLOWED = false;
    private static final Boolean UPDATED_TRANSFER_ALLOWED = true;

    private static final Boolean DEFAULT_INFO_SHARING_ALLOWED = false;
    private static final Boolean UPDATED_INFO_SHARING_ALLOWED = true;

    private static final String DEFAULT_INFO_SHARING_OPTIONS = "AAAAAAAAAA";
    private static final String UPDATED_INFO_SHARING_OPTIONS = "BBBBBBBBBB";

    private static final Integer DEFAULT_OFFER_PERIOD = 1;
    private static final Integer UPDATED_OFFER_PERIOD = 2;

    private static final OfferPeriodTerm DEFAULT_OFFER_PERIOD_TERM = OfferPeriodTerm.DAYS;
    private static final OfferPeriodTerm UPDATED_OFFER_PERIOD_TERM = OfferPeriodTerm.WEEKS;

    private static final String DEFAULT_PAYMENT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_PAYMENT_TYPE = "BBBBBBBBBB";

    private static final Integer DEFAULT_PRIORITY = 1;
    private static final Integer UPDATED_PRIORITY = 2;

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
    private OfferRepository offerRepository;

    @Mock
    private OfferRepository offerRepositoryMock;

    @Mock
    private OfferService offerServiceMock;

    @Autowired
    private OfferService offerService;

    @Autowired
    private MockMvc restOfferMockMvc;

    private Offer offer;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Offer createEntity() {
        Offer offer = new Offer()
            .offerId(DEFAULT_OFFER_ID)
            .offerExternalId(DEFAULT_OFFER_EXTERNAL_ID)
            .offerName(DEFAULT_OFFER_NAME)
            .offerNameChi(DEFAULT_OFFER_NAME_CHI)
            .offerDesc(DEFAULT_OFFER_DESC)
            .offerDescChi(DEFAULT_OFFER_DESC_CHI)
            .offerType(DEFAULT_OFFER_TYPE)
            .offerPrice(DEFAULT_OFFER_PRICE)
            .tempCustomerSegments(DEFAULT_TEMP_CUSTOMER_SEGMENTS)
            .tempCustomerClasses(DEFAULT_TEMP_CUSTOMER_CLASSES)
            .tempSalesChannels(DEFAULT_TEMP_SALES_CHANNELS)
            .startDate(DEFAULT_START_DATE)
            .endDate(DEFAULT_END_DATE)
            .tempChildOfferIds(DEFAULT_TEMP_CHILD_OFFER_IDS)
            .tempProductIds(DEFAULT_TEMP_PRODUCT_IDS)
            .tempAdvancePaymentIds(DEFAULT_TEMP_ADVANCE_PAYMENT_IDS)
            .tempPromoCodes(DEFAULT_TEMP_PROMO_CODES)
            .tempDiscountCodes(DEFAULT_TEMP_DISCOUNT_CODES)
            .tempImageIds(DEFAULT_TEMP_IMAGE_IDS)
            .limitedActivationPeriod(DEFAULT_LIMITED_ACTIVATION_PERIOD)
            .allowedActivationStartDate(DEFAULT_ALLOWED_ACTIVATION_START_DATE)
            .allowedActivationEndDate(DEFAULT_ALLOWED_ACTIVATION_END_DATE)
            .isGroupSharingOffer(DEFAULT_IS_GROUP_SHARING_OFFER)
            .isMnpOffer(DEFAULT_IS_MNP_OFFER)
            .autoRenewal(DEFAULT_AUTO_RENEWAL)
            .transferAllowed(DEFAULT_TRANSFER_ALLOWED)
            .infoSharingAllowed(DEFAULT_INFO_SHARING_ALLOWED)
            .infoSharingOptions(DEFAULT_INFO_SHARING_OPTIONS)
            .offerPeriod(DEFAULT_OFFER_PERIOD)
            .offerPeriodTerm(DEFAULT_OFFER_PERIOD_TERM)
            .paymentType(DEFAULT_PAYMENT_TYPE)
            .priority(DEFAULT_PRIORITY)
            .lockCount(DEFAULT_LOCK_COUNT)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdatedDate(DEFAULT_LAST_UPDATED_DATE)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY)
            .tenantId(DEFAULT_TENANT_ID);
        return offer;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Offer createUpdatedEntity() {
        Offer offer = new Offer()
            .offerId(UPDATED_OFFER_ID)
            .offerExternalId(UPDATED_OFFER_EXTERNAL_ID)
            .offerName(UPDATED_OFFER_NAME)
            .offerNameChi(UPDATED_OFFER_NAME_CHI)
            .offerDesc(UPDATED_OFFER_DESC)
            .offerDescChi(UPDATED_OFFER_DESC_CHI)
            .offerType(UPDATED_OFFER_TYPE)
            .offerPrice(UPDATED_OFFER_PRICE)
            .tempCustomerSegments(UPDATED_TEMP_CUSTOMER_SEGMENTS)
            .tempCustomerClasses(UPDATED_TEMP_CUSTOMER_CLASSES)
            .tempSalesChannels(UPDATED_TEMP_SALES_CHANNELS)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .tempChildOfferIds(UPDATED_TEMP_CHILD_OFFER_IDS)
            .tempProductIds(UPDATED_TEMP_PRODUCT_IDS)
            .tempAdvancePaymentIds(UPDATED_TEMP_ADVANCE_PAYMENT_IDS)
            .tempPromoCodes(UPDATED_TEMP_PROMO_CODES)
            .tempDiscountCodes(UPDATED_TEMP_DISCOUNT_CODES)
            .tempImageIds(UPDATED_TEMP_IMAGE_IDS)
            .limitedActivationPeriod(UPDATED_LIMITED_ACTIVATION_PERIOD)
            .allowedActivationStartDate(UPDATED_ALLOWED_ACTIVATION_START_DATE)
            .allowedActivationEndDate(UPDATED_ALLOWED_ACTIVATION_END_DATE)
            .isGroupSharingOffer(UPDATED_IS_GROUP_SHARING_OFFER)
            .isMnpOffer(UPDATED_IS_MNP_OFFER)
            .autoRenewal(UPDATED_AUTO_RENEWAL)
            .transferAllowed(UPDATED_TRANSFER_ALLOWED)
            .infoSharingAllowed(UPDATED_INFO_SHARING_ALLOWED)
            .infoSharingOptions(UPDATED_INFO_SHARING_OPTIONS)
            .offerPeriod(UPDATED_OFFER_PERIOD)
            .offerPeriodTerm(UPDATED_OFFER_PERIOD_TERM)
            .paymentType(UPDATED_PAYMENT_TYPE)
            .priority(UPDATED_PRIORITY)
            .lockCount(UPDATED_LOCK_COUNT)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);
        return offer;
    }

    @BeforeEach
    public void initTest() {
        offerRepository.deleteAll();
        offer = createEntity();
    }

    @Test
    public void createOffer() throws Exception {
        int databaseSizeBeforeCreate = offerRepository.findAll().size();

        // Create the Offer
        restOfferMockMvc.perform(post("/api/offers").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offer)))
            .andExpect(status().isCreated());

        // Validate the Offer in the database
        List<Offer> offerList = offerRepository.findAll();
        assertThat(offerList).hasSize(databaseSizeBeforeCreate + 1);
        Offer testOffer = offerList.get(offerList.size() - 1);
        assertThat(testOffer.getOfferId()).isEqualTo(DEFAULT_OFFER_ID);
        assertThat(testOffer.getOfferExternalId()).isEqualTo(DEFAULT_OFFER_EXTERNAL_ID);
        assertThat(testOffer.getOfferName()).isEqualTo(DEFAULT_OFFER_NAME);
        assertThat(testOffer.getOfferNameChi()).isEqualTo(DEFAULT_OFFER_NAME_CHI);
        assertThat(testOffer.getOfferDesc()).isEqualTo(DEFAULT_OFFER_DESC);
        assertThat(testOffer.getOfferDescChi()).isEqualTo(DEFAULT_OFFER_DESC_CHI);
        assertThat(testOffer.getOfferType()).isEqualTo(DEFAULT_OFFER_TYPE);
        assertThat(testOffer.getOfferPrice()).isEqualTo(DEFAULT_OFFER_PRICE);
        assertThat(testOffer.getTempCustomerSegments()).isEqualTo(DEFAULT_TEMP_CUSTOMER_SEGMENTS);
        assertThat(testOffer.getTempCustomerClasses()).isEqualTo(DEFAULT_TEMP_CUSTOMER_CLASSES);
        assertThat(testOffer.getTempSalesChannels()).isEqualTo(DEFAULT_TEMP_SALES_CHANNELS);
        assertThat(testOffer.getStartDate()).isEqualTo(DEFAULT_START_DATE);
        assertThat(testOffer.getEndDate()).isEqualTo(DEFAULT_END_DATE);
        assertThat(testOffer.getTempChildOfferIds()).isEqualTo(DEFAULT_TEMP_CHILD_OFFER_IDS);
        assertThat(testOffer.getTempProductIds()).isEqualTo(DEFAULT_TEMP_PRODUCT_IDS);
        assertThat(testOffer.getTempAdvancePaymentIds()).isEqualTo(DEFAULT_TEMP_ADVANCE_PAYMENT_IDS);
        assertThat(testOffer.getTempPromoCodes()).isEqualTo(DEFAULT_TEMP_PROMO_CODES);
        assertThat(testOffer.getTempDiscountCodes()).isEqualTo(DEFAULT_TEMP_DISCOUNT_CODES);
        assertThat(testOffer.getTempImageIds()).isEqualTo(DEFAULT_TEMP_IMAGE_IDS);
        assertThat(testOffer.isLimitedActivationPeriod()).isEqualTo(DEFAULT_LIMITED_ACTIVATION_PERIOD);
        assertThat(testOffer.getAllowedActivationStartDate()).isEqualTo(DEFAULT_ALLOWED_ACTIVATION_START_DATE);
        assertThat(testOffer.getAllowedActivationEndDate()).isEqualTo(DEFAULT_ALLOWED_ACTIVATION_END_DATE);
        assertThat(testOffer.isIsGroupSharingOffer()).isEqualTo(DEFAULT_IS_GROUP_SHARING_OFFER);
        assertThat(testOffer.isIsMnpOffer()).isEqualTo(DEFAULT_IS_MNP_OFFER);
        assertThat(testOffer.isAutoRenewal()).isEqualTo(DEFAULT_AUTO_RENEWAL);
        assertThat(testOffer.isTransferAllowed()).isEqualTo(DEFAULT_TRANSFER_ALLOWED);
        assertThat(testOffer.isInfoSharingAllowed()).isEqualTo(DEFAULT_INFO_SHARING_ALLOWED);
        assertThat(testOffer.getInfoSharingOptions()).isEqualTo(DEFAULT_INFO_SHARING_OPTIONS);
        assertThat(testOffer.getOfferPeriod()).isEqualTo(DEFAULT_OFFER_PERIOD);
        assertThat(testOffer.getOfferPeriodTerm()).isEqualTo(DEFAULT_OFFER_PERIOD_TERM);
        assertThat(testOffer.getPaymentType()).isEqualTo(DEFAULT_PAYMENT_TYPE);
        assertThat(testOffer.getPriority()).isEqualTo(DEFAULT_PRIORITY);
        assertThat(testOffer.getLockCount()).isEqualTo(DEFAULT_LOCK_COUNT);
        assertThat(testOffer.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testOffer.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testOffer.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testOffer.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
        assertThat(testOffer.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
    }

    @Test
    public void createOfferWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = offerRepository.findAll().size();

        // Create the Offer with an existing ID
        offer.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restOfferMockMvc.perform(post("/api/offers").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offer)))
            .andExpect(status().isBadRequest());

        // Validate the Offer in the database
        List<Offer> offerList = offerRepository.findAll();
        assertThat(offerList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkOfferIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = offerRepository.findAll().size();
        // set the field null
        offer.setOfferId(null);

        // Create the Offer, which fails.

        restOfferMockMvc.perform(post("/api/offers").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offer)))
            .andExpect(status().isBadRequest());

        List<Offer> offerList = offerRepository.findAll();
        assertThat(offerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkOfferNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = offerRepository.findAll().size();
        // set the field null
        offer.setOfferName(null);

        // Create the Offer, which fails.

        restOfferMockMvc.perform(post("/api/offers").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offer)))
            .andExpect(status().isBadRequest());

        List<Offer> offerList = offerRepository.findAll();
        assertThat(offerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = offerRepository.findAll().size();
        // set the field null
        offer.setCreatedDate(null);

        // Create the Offer, which fails.

        restOfferMockMvc.perform(post("/api/offers").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offer)))
            .andExpect(status().isBadRequest());

        List<Offer> offerList = offerRepository.findAll();
        assertThat(offerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = offerRepository.findAll().size();
        // set the field null
        offer.setCreatedBy(null);

        // Create the Offer, which fails.

        restOfferMockMvc.perform(post("/api/offers").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offer)))
            .andExpect(status().isBadRequest());

        List<Offer> offerList = offerRepository.findAll();
        assertThat(offerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = offerRepository.findAll().size();
        // set the field null
        offer.setLastUpdatedDate(null);

        // Create the Offer, which fails.

        restOfferMockMvc.perform(post("/api/offers").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offer)))
            .andExpect(status().isBadRequest());

        List<Offer> offerList = offerRepository.findAll();
        assertThat(offerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = offerRepository.findAll().size();
        // set the field null
        offer.setLastUpdatedBy(null);

        // Create the Offer, which fails.

        restOfferMockMvc.perform(post("/api/offers").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offer)))
            .andExpect(status().isBadRequest());

        List<Offer> offerList = offerRepository.findAll();
        assertThat(offerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTenantIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = offerRepository.findAll().size();
        // set the field null
        offer.setTenantId(null);

        // Create the Offer, which fails.

        restOfferMockMvc.perform(post("/api/offers").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offer)))
            .andExpect(status().isBadRequest());

        List<Offer> offerList = offerRepository.findAll();
        assertThat(offerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllOffers() throws Exception {
        // Initialize the database
        offerRepository.save(offer);

        // Get all the offerList
        restOfferMockMvc.perform(get("/api/offers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(offer.getId())))
            .andExpect(jsonPath("$.[*].offerId").value(hasItem(DEFAULT_OFFER_ID)))
            .andExpect(jsonPath("$.[*].offerExternalId").value(hasItem(DEFAULT_OFFER_EXTERNAL_ID)))
            .andExpect(jsonPath("$.[*].offerName").value(hasItem(DEFAULT_OFFER_NAME)))
            .andExpect(jsonPath("$.[*].offerNameChi").value(hasItem(DEFAULT_OFFER_NAME_CHI)))
            .andExpect(jsonPath("$.[*].offerDesc").value(hasItem(DEFAULT_OFFER_DESC)))
            .andExpect(jsonPath("$.[*].offerDescChi").value(hasItem(DEFAULT_OFFER_DESC_CHI)))
            .andExpect(jsonPath("$.[*].offerType").value(hasItem(DEFAULT_OFFER_TYPE.toString())))
            .andExpect(jsonPath("$.[*].offerPrice").value(hasItem(DEFAULT_OFFER_PRICE.intValue())))
            .andExpect(jsonPath("$.[*].tempCustomerSegments").value(hasItem(DEFAULT_TEMP_CUSTOMER_SEGMENTS)))
            .andExpect(jsonPath("$.[*].tempCustomerClasses").value(hasItem(DEFAULT_TEMP_CUSTOMER_CLASSES)))
            .andExpect(jsonPath("$.[*].tempSalesChannels").value(hasItem(DEFAULT_TEMP_SALES_CHANNELS)))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].tempChildOfferIds").value(hasItem(DEFAULT_TEMP_CHILD_OFFER_IDS)))
            .andExpect(jsonPath("$.[*].tempProductIds").value(hasItem(DEFAULT_TEMP_PRODUCT_IDS)))
            .andExpect(jsonPath("$.[*].tempAdvancePaymentIds").value(hasItem(DEFAULT_TEMP_ADVANCE_PAYMENT_IDS)))
            .andExpect(jsonPath("$.[*].tempPromoCodes").value(hasItem(DEFAULT_TEMP_PROMO_CODES)))
            .andExpect(jsonPath("$.[*].tempDiscountCodes").value(hasItem(DEFAULT_TEMP_DISCOUNT_CODES)))
            .andExpect(jsonPath("$.[*].tempImageIds").value(hasItem(DEFAULT_TEMP_IMAGE_IDS)))
            .andExpect(jsonPath("$.[*].limitedActivationPeriod").value(hasItem(DEFAULT_LIMITED_ACTIVATION_PERIOD.booleanValue())))
            .andExpect(jsonPath("$.[*].allowedActivationStartDate").value(hasItem(DEFAULT_ALLOWED_ACTIVATION_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].allowedActivationEndDate").value(hasItem(DEFAULT_ALLOWED_ACTIVATION_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].isGroupSharingOffer").value(hasItem(DEFAULT_IS_GROUP_SHARING_OFFER.booleanValue())))
            .andExpect(jsonPath("$.[*].isMnpOffer").value(hasItem(DEFAULT_IS_MNP_OFFER.booleanValue())))
            .andExpect(jsonPath("$.[*].autoRenewal").value(hasItem(DEFAULT_AUTO_RENEWAL.booleanValue())))
            .andExpect(jsonPath("$.[*].transferAllowed").value(hasItem(DEFAULT_TRANSFER_ALLOWED.booleanValue())))
            .andExpect(jsonPath("$.[*].infoSharingAllowed").value(hasItem(DEFAULT_INFO_SHARING_ALLOWED.booleanValue())))
            .andExpect(jsonPath("$.[*].infoSharingOptions").value(hasItem(DEFAULT_INFO_SHARING_OPTIONS)))
            .andExpect(jsonPath("$.[*].offerPeriod").value(hasItem(DEFAULT_OFFER_PERIOD)))
            .andExpect(jsonPath("$.[*].offerPeriodTerm").value(hasItem(DEFAULT_OFFER_PERIOD_TERM.toString())))
            .andExpect(jsonPath("$.[*].paymentType").value(hasItem(DEFAULT_PAYMENT_TYPE)))
            .andExpect(jsonPath("$.[*].priority").value(hasItem(DEFAULT_PRIORITY)))
            .andExpect(jsonPath("$.[*].lockCount").value(hasItem(DEFAULT_LOCK_COUNT)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].lastUpdatedDate").value(hasItem(DEFAULT_LAST_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].tenantId").value(hasItem(DEFAULT_TENANT_ID)));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllOffersWithEagerRelationshipsIsEnabled() throws Exception {
        when(offerServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restOfferMockMvc.perform(get("/api/offers?eagerload=true"))
            .andExpect(status().isOk());

        verify(offerServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllOffersWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(offerServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restOfferMockMvc.perform(get("/api/offers?eagerload=true"))
            .andExpect(status().isOk());

        verify(offerServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    public void getOffer() throws Exception {
        // Initialize the database
        offerRepository.save(offer);

        // Get the offer
        restOfferMockMvc.perform(get("/api/offers/{id}", offer.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(offer.getId()))
            .andExpect(jsonPath("$.offerId").value(DEFAULT_OFFER_ID))
            .andExpect(jsonPath("$.offerExternalId").value(DEFAULT_OFFER_EXTERNAL_ID))
            .andExpect(jsonPath("$.offerName").value(DEFAULT_OFFER_NAME))
            .andExpect(jsonPath("$.offerNameChi").value(DEFAULT_OFFER_NAME_CHI))
            .andExpect(jsonPath("$.offerDesc").value(DEFAULT_OFFER_DESC))
            .andExpect(jsonPath("$.offerDescChi").value(DEFAULT_OFFER_DESC_CHI))
            .andExpect(jsonPath("$.offerType").value(DEFAULT_OFFER_TYPE.toString()))
            .andExpect(jsonPath("$.offerPrice").value(DEFAULT_OFFER_PRICE.intValue()))
            .andExpect(jsonPath("$.tempCustomerSegments").value(DEFAULT_TEMP_CUSTOMER_SEGMENTS))
            .andExpect(jsonPath("$.tempCustomerClasses").value(DEFAULT_TEMP_CUSTOMER_CLASSES))
            .andExpect(jsonPath("$.tempSalesChannels").value(DEFAULT_TEMP_SALES_CHANNELS))
            .andExpect(jsonPath("$.startDate").value(DEFAULT_START_DATE.toString()))
            .andExpect(jsonPath("$.endDate").value(DEFAULT_END_DATE.toString()))
            .andExpect(jsonPath("$.tempChildOfferIds").value(DEFAULT_TEMP_CHILD_OFFER_IDS))
            .andExpect(jsonPath("$.tempProductIds").value(DEFAULT_TEMP_PRODUCT_IDS))
            .andExpect(jsonPath("$.tempAdvancePaymentIds").value(DEFAULT_TEMP_ADVANCE_PAYMENT_IDS))
            .andExpect(jsonPath("$.tempPromoCodes").value(DEFAULT_TEMP_PROMO_CODES))
            .andExpect(jsonPath("$.tempDiscountCodes").value(DEFAULT_TEMP_DISCOUNT_CODES))
            .andExpect(jsonPath("$.tempImageIds").value(DEFAULT_TEMP_IMAGE_IDS))
            .andExpect(jsonPath("$.limitedActivationPeriod").value(DEFAULT_LIMITED_ACTIVATION_PERIOD.booleanValue()))
            .andExpect(jsonPath("$.allowedActivationStartDate").value(DEFAULT_ALLOWED_ACTIVATION_START_DATE.toString()))
            .andExpect(jsonPath("$.allowedActivationEndDate").value(DEFAULT_ALLOWED_ACTIVATION_END_DATE.toString()))
            .andExpect(jsonPath("$.isGroupSharingOffer").value(DEFAULT_IS_GROUP_SHARING_OFFER.booleanValue()))
            .andExpect(jsonPath("$.isMnpOffer").value(DEFAULT_IS_MNP_OFFER.booleanValue()))
            .andExpect(jsonPath("$.autoRenewal").value(DEFAULT_AUTO_RENEWAL.booleanValue()))
            .andExpect(jsonPath("$.transferAllowed").value(DEFAULT_TRANSFER_ALLOWED.booleanValue()))
            .andExpect(jsonPath("$.infoSharingAllowed").value(DEFAULT_INFO_SHARING_ALLOWED.booleanValue()))
            .andExpect(jsonPath("$.infoSharingOptions").value(DEFAULT_INFO_SHARING_OPTIONS))
            .andExpect(jsonPath("$.offerPeriod").value(DEFAULT_OFFER_PERIOD))
            .andExpect(jsonPath("$.offerPeriodTerm").value(DEFAULT_OFFER_PERIOD_TERM.toString()))
            .andExpect(jsonPath("$.paymentType").value(DEFAULT_PAYMENT_TYPE))
            .andExpect(jsonPath("$.priority").value(DEFAULT_PRIORITY))
            .andExpect(jsonPath("$.lockCount").value(DEFAULT_LOCK_COUNT))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.lastUpdatedDate").value(DEFAULT_LAST_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY))
            .andExpect(jsonPath("$.tenantId").value(DEFAULT_TENANT_ID));
    }

    @Test
    public void getNonExistingOffer() throws Exception {
        // Get the offer
        restOfferMockMvc.perform(get("/api/offers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateOffer() throws Exception {
        // Initialize the database
        offerService.save(offer);

        int databaseSizeBeforeUpdate = offerRepository.findAll().size();

        // Update the offer
        Offer updatedOffer = offerRepository.findById(offer.getId()).get();
        updatedOffer
            .offerId(UPDATED_OFFER_ID)
            .offerExternalId(UPDATED_OFFER_EXTERNAL_ID)
            .offerName(UPDATED_OFFER_NAME)
            .offerNameChi(UPDATED_OFFER_NAME_CHI)
            .offerDesc(UPDATED_OFFER_DESC)
            .offerDescChi(UPDATED_OFFER_DESC_CHI)
            .offerType(UPDATED_OFFER_TYPE)
            .offerPrice(UPDATED_OFFER_PRICE)
            .tempCustomerSegments(UPDATED_TEMP_CUSTOMER_SEGMENTS)
            .tempCustomerClasses(UPDATED_TEMP_CUSTOMER_CLASSES)
            .tempSalesChannels(UPDATED_TEMP_SALES_CHANNELS)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .tempChildOfferIds(UPDATED_TEMP_CHILD_OFFER_IDS)
            .tempProductIds(UPDATED_TEMP_PRODUCT_IDS)
            .tempAdvancePaymentIds(UPDATED_TEMP_ADVANCE_PAYMENT_IDS)
            .tempPromoCodes(UPDATED_TEMP_PROMO_CODES)
            .tempDiscountCodes(UPDATED_TEMP_DISCOUNT_CODES)
            .tempImageIds(UPDATED_TEMP_IMAGE_IDS)
            .limitedActivationPeriod(UPDATED_LIMITED_ACTIVATION_PERIOD)
            .allowedActivationStartDate(UPDATED_ALLOWED_ACTIVATION_START_DATE)
            .allowedActivationEndDate(UPDATED_ALLOWED_ACTIVATION_END_DATE)
            .isGroupSharingOffer(UPDATED_IS_GROUP_SHARING_OFFER)
            .isMnpOffer(UPDATED_IS_MNP_OFFER)
            .autoRenewal(UPDATED_AUTO_RENEWAL)
            .transferAllowed(UPDATED_TRANSFER_ALLOWED)
            .infoSharingAllowed(UPDATED_INFO_SHARING_ALLOWED)
            .infoSharingOptions(UPDATED_INFO_SHARING_OPTIONS)
            .offerPeriod(UPDATED_OFFER_PERIOD)
            .offerPeriodTerm(UPDATED_OFFER_PERIOD_TERM)
            .paymentType(UPDATED_PAYMENT_TYPE)
            .priority(UPDATED_PRIORITY)
            .lockCount(UPDATED_LOCK_COUNT)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);

        restOfferMockMvc.perform(put("/api/offers").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedOffer)))
            .andExpect(status().isOk());

        // Validate the Offer in the database
        List<Offer> offerList = offerRepository.findAll();
        assertThat(offerList).hasSize(databaseSizeBeforeUpdate);
        Offer testOffer = offerList.get(offerList.size() - 1);
        assertThat(testOffer.getOfferId()).isEqualTo(UPDATED_OFFER_ID);
        assertThat(testOffer.getOfferExternalId()).isEqualTo(UPDATED_OFFER_EXTERNAL_ID);
        assertThat(testOffer.getOfferName()).isEqualTo(UPDATED_OFFER_NAME);
        assertThat(testOffer.getOfferNameChi()).isEqualTo(UPDATED_OFFER_NAME_CHI);
        assertThat(testOffer.getOfferDesc()).isEqualTo(UPDATED_OFFER_DESC);
        assertThat(testOffer.getOfferDescChi()).isEqualTo(UPDATED_OFFER_DESC_CHI);
        assertThat(testOffer.getOfferType()).isEqualTo(UPDATED_OFFER_TYPE);
        assertThat(testOffer.getOfferPrice()).isEqualTo(UPDATED_OFFER_PRICE);
        assertThat(testOffer.getTempCustomerSegments()).isEqualTo(UPDATED_TEMP_CUSTOMER_SEGMENTS);
        assertThat(testOffer.getTempCustomerClasses()).isEqualTo(UPDATED_TEMP_CUSTOMER_CLASSES);
        assertThat(testOffer.getTempSalesChannels()).isEqualTo(UPDATED_TEMP_SALES_CHANNELS);
        assertThat(testOffer.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testOffer.getEndDate()).isEqualTo(UPDATED_END_DATE);
        assertThat(testOffer.getTempChildOfferIds()).isEqualTo(UPDATED_TEMP_CHILD_OFFER_IDS);
        assertThat(testOffer.getTempProductIds()).isEqualTo(UPDATED_TEMP_PRODUCT_IDS);
        assertThat(testOffer.getTempAdvancePaymentIds()).isEqualTo(UPDATED_TEMP_ADVANCE_PAYMENT_IDS);
        assertThat(testOffer.getTempPromoCodes()).isEqualTo(UPDATED_TEMP_PROMO_CODES);
        assertThat(testOffer.getTempDiscountCodes()).isEqualTo(UPDATED_TEMP_DISCOUNT_CODES);
        assertThat(testOffer.getTempImageIds()).isEqualTo(UPDATED_TEMP_IMAGE_IDS);
        assertThat(testOffer.isLimitedActivationPeriod()).isEqualTo(UPDATED_LIMITED_ACTIVATION_PERIOD);
        assertThat(testOffer.getAllowedActivationStartDate()).isEqualTo(UPDATED_ALLOWED_ACTIVATION_START_DATE);
        assertThat(testOffer.getAllowedActivationEndDate()).isEqualTo(UPDATED_ALLOWED_ACTIVATION_END_DATE);
        assertThat(testOffer.isIsGroupSharingOffer()).isEqualTo(UPDATED_IS_GROUP_SHARING_OFFER);
        assertThat(testOffer.isIsMnpOffer()).isEqualTo(UPDATED_IS_MNP_OFFER);
        assertThat(testOffer.isAutoRenewal()).isEqualTo(UPDATED_AUTO_RENEWAL);
        assertThat(testOffer.isTransferAllowed()).isEqualTo(UPDATED_TRANSFER_ALLOWED);
        assertThat(testOffer.isInfoSharingAllowed()).isEqualTo(UPDATED_INFO_SHARING_ALLOWED);
        assertThat(testOffer.getInfoSharingOptions()).isEqualTo(UPDATED_INFO_SHARING_OPTIONS);
        assertThat(testOffer.getOfferPeriod()).isEqualTo(UPDATED_OFFER_PERIOD);
        assertThat(testOffer.getOfferPeriodTerm()).isEqualTo(UPDATED_OFFER_PERIOD_TERM);
        assertThat(testOffer.getPaymentType()).isEqualTo(UPDATED_PAYMENT_TYPE);
        assertThat(testOffer.getPriority()).isEqualTo(UPDATED_PRIORITY);
        assertThat(testOffer.getLockCount()).isEqualTo(UPDATED_LOCK_COUNT);
        assertThat(testOffer.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testOffer.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testOffer.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testOffer.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
        assertThat(testOffer.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    public void updateNonExistingOffer() throws Exception {
        int databaseSizeBeforeUpdate = offerRepository.findAll().size();

        // Create the Offer

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOfferMockMvc.perform(put("/api/offers").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offer)))
            .andExpect(status().isBadRequest());

        // Validate the Offer in the database
        List<Offer> offerList = offerRepository.findAll();
        assertThat(offerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteOffer() throws Exception {
        // Initialize the database
        offerService.save(offer);

        int databaseSizeBeforeDelete = offerRepository.findAll().size();

        // Delete the offer
        restOfferMockMvc.perform(delete("/api/offers/{id}", offer.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Offer> offerList = offerRepository.findAll();
        assertThat(offerList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
