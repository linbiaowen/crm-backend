package com.hthk.crm.web.rest;

import com.hthk.crm.CrmwebApp;
import com.hthk.crm.config.TestSecurityConfiguration;
import com.hthk.crm.domain.Product;
import com.hthk.crm.repository.ProductRepository;
import com.hthk.crm.service.ProductService;

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

import com.hthk.crm.domain.enumeration.ProductCate;
import com.hthk.crm.domain.enumeration.ProductNature;
import com.hthk.crm.domain.enumeration.ProductFamily;
import com.hthk.crm.domain.enumeration.ProductType;
import com.hthk.crm.domain.enumeration.ProductSpecType;
import com.hthk.crm.domain.enumeration.SkuType;
import com.hthk.crm.domain.enumeration.SimType;
import com.hthk.crm.domain.enumeration.BoxType;
/**
 * Integration tests for the {@link ProductResource} REST controller.
 */
@SpringBootTest(classes = { CrmwebApp.class, TestSecurityConfiguration.class })

@AutoConfigureMockMvc
@WithMockUser
public class ProductResourceIT {

    private static final String DEFAULT_PRODUCT_ID = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_NAME_CHI = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_NAME_CHI = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_DESC = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_DESC = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_DESC_CHI = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_DESC_CHI = "BBBBBBBBBB";

    private static final ProductCate DEFAULT_PRODUCT_CATE = ProductCate.TELCO;
    private static final ProductCate UPDATED_PRODUCT_CATE = ProductCate.NON_TELCO;

    private static final ProductNature DEFAULT_PRODUCT_NATURE = ProductNature.DEVICE;
    private static final ProductNature UPDATED_PRODUCT_NATURE = ProductNature.SERVICE;

    private static final ProductFamily DEFAULT_PRODUCT_FAMILY = ProductFamily.VOICE;
    private static final ProductFamily UPDATED_PRODUCT_FAMILY = ProductFamily.DATA;

    private static final ProductType DEFAULT_PRODUCT_TYPE = ProductType.BASE;
    private static final ProductType UPDATED_PRODUCT_TYPE = ProductType.VAS;

    private static final String DEFAULT_MODEL_CODE = "AAAAAAAAAA";
    private static final String UPDATED_MODEL_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_TEMP_SERVICE_ID = "AAAAAAAAAA";
    private static final String UPDATED_TEMP_SERVICE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_TEMP_RESOURCE_SPEC_IDS = "AAAAAAAAAA";
    private static final String UPDATED_TEMP_RESOURCE_SPEC_IDS = "BBBBBBBBBB";

    private static final ProductSpecType DEFAULT_PRODUCT_SPEC_TYPE = ProductSpecType.DEVICE;
    private static final ProductSpecType UPDATED_PRODUCT_SPEC_TYPE = ProductSpecType.ACCESSORY;

    private static final SkuType DEFAULT_SKU_TYPE = SkuType.DEVICE;
    private static final SkuType UPDATED_SKU_TYPE = SkuType.ACCESSORY;

    private static final SimType DEFAULT_SIM_TYPE = SimType.PHYSICAL_SIM;
    private static final SimType UPDATED_SIM_TYPE = SimType.ESIM;

    private static final BoxType DEFAULT_BOX_TYPE = BoxType.TRAVEL;
    private static final BoxType UPDATED_BOX_TYPE = BoxType.BEAUTY;

    private static final Boolean DEFAULT_SHIPPABLE = false;
    private static final Boolean UPDATED_SHIPPABLE = true;

    private static final String DEFAULT_TEMP_DELIVERY_OPTIONS = "AAAAAAAAAA";
    private static final String UPDATED_TEMP_DELIVERY_OPTIONS = "BBBBBBBBBB";

    private static final String DEFAULT_TEMP_VOICE_IDS = "AAAAAAAAAA";
    private static final String UPDATED_TEMP_VOICE_IDS = "BBBBBBBBBB";

    private static final String DEFAULT_TEMP_DATA_IDS = "AAAAAAAAAA";
    private static final String UPDATED_TEMP_DATA_IDS = "BBBBBBBBBB";

    private static final String DEFAULT_TEMP_SMS_IDS = "AAAAAAAAAA";
    private static final String UPDATED_TEMP_SMS_IDS = "BBBBBBBBBB";

    private static final String DEFAULT_TEMP_MMS_IDS = "AAAAAAAAAA";
    private static final String UPDATED_TEMP_MMS_IDS = "BBBBBBBBBB";

    private static final Instant DEFAULT_START_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_START_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_END_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_END_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Boolean DEFAULT_INDEPENDENTLY_ORDERABLE = false;
    private static final Boolean UPDATED_INDEPENDENTLY_ORDERABLE = true;

    private static final Boolean DEFAULT_NETWORK_PROVISION_REQUIRED = false;
    private static final Boolean UPDATED_NETWORK_PROVISION_REQUIRED = true;

    private static final Boolean DEFAULT_CHANGE_ENTITLEMENT_ALLOWED = false;
    private static final Boolean UPDATED_CHANGE_ENTITLEMENT_ALLOWED = true;

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
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private MockMvc restProductMockMvc;

    private Product product;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Product createEntity() {
        Product product = new Product()
            .productId(DEFAULT_PRODUCT_ID)
            .productName(DEFAULT_PRODUCT_NAME)
            .productNameChi(DEFAULT_PRODUCT_NAME_CHI)
            .productDesc(DEFAULT_PRODUCT_DESC)
            .productDescChi(DEFAULT_PRODUCT_DESC_CHI)
            .productCate(DEFAULT_PRODUCT_CATE)
            .productNature(DEFAULT_PRODUCT_NATURE)
            .productFamily(DEFAULT_PRODUCT_FAMILY)
            .productType(DEFAULT_PRODUCT_TYPE)
            .modelCode(DEFAULT_MODEL_CODE)
            .tempServiceId(DEFAULT_TEMP_SERVICE_ID)
            .tempResourceSpecIds(DEFAULT_TEMP_RESOURCE_SPEC_IDS)
            .productSpecType(DEFAULT_PRODUCT_SPEC_TYPE)
            .skuType(DEFAULT_SKU_TYPE)
            .simType(DEFAULT_SIM_TYPE)
            .boxType(DEFAULT_BOX_TYPE)
            .shippable(DEFAULT_SHIPPABLE)
            .tempDeliveryOptions(DEFAULT_TEMP_DELIVERY_OPTIONS)
            .tempVoiceIds(DEFAULT_TEMP_VOICE_IDS)
            .tempDataIds(DEFAULT_TEMP_DATA_IDS)
            .tempSmsIds(DEFAULT_TEMP_SMS_IDS)
            .tempMmsIds(DEFAULT_TEMP_MMS_IDS)
            .startDate(DEFAULT_START_DATE)
            .endDate(DEFAULT_END_DATE)
            .independentlyOrderable(DEFAULT_INDEPENDENTLY_ORDERABLE)
            .networkProvisionRequired(DEFAULT_NETWORK_PROVISION_REQUIRED)
            .changeEntitlementAllowed(DEFAULT_CHANGE_ENTITLEMENT_ALLOWED)
            .lockCount(DEFAULT_LOCK_COUNT)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdatedDate(DEFAULT_LAST_UPDATED_DATE)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY)
            .tenantId(DEFAULT_TENANT_ID);
        return product;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Product createUpdatedEntity() {
        Product product = new Product()
            .productId(UPDATED_PRODUCT_ID)
            .productName(UPDATED_PRODUCT_NAME)
            .productNameChi(UPDATED_PRODUCT_NAME_CHI)
            .productDesc(UPDATED_PRODUCT_DESC)
            .productDescChi(UPDATED_PRODUCT_DESC_CHI)
            .productCate(UPDATED_PRODUCT_CATE)
            .productNature(UPDATED_PRODUCT_NATURE)
            .productFamily(UPDATED_PRODUCT_FAMILY)
            .productType(UPDATED_PRODUCT_TYPE)
            .modelCode(UPDATED_MODEL_CODE)
            .tempServiceId(UPDATED_TEMP_SERVICE_ID)
            .tempResourceSpecIds(UPDATED_TEMP_RESOURCE_SPEC_IDS)
            .productSpecType(UPDATED_PRODUCT_SPEC_TYPE)
            .skuType(UPDATED_SKU_TYPE)
            .simType(UPDATED_SIM_TYPE)
            .boxType(UPDATED_BOX_TYPE)
            .shippable(UPDATED_SHIPPABLE)
            .tempDeliveryOptions(UPDATED_TEMP_DELIVERY_OPTIONS)
            .tempVoiceIds(UPDATED_TEMP_VOICE_IDS)
            .tempDataIds(UPDATED_TEMP_DATA_IDS)
            .tempSmsIds(UPDATED_TEMP_SMS_IDS)
            .tempMmsIds(UPDATED_TEMP_MMS_IDS)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .independentlyOrderable(UPDATED_INDEPENDENTLY_ORDERABLE)
            .networkProvisionRequired(UPDATED_NETWORK_PROVISION_REQUIRED)
            .changeEntitlementAllowed(UPDATED_CHANGE_ENTITLEMENT_ALLOWED)
            .lockCount(UPDATED_LOCK_COUNT)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);
        return product;
    }

    @BeforeEach
    public void initTest() {
        productRepository.deleteAll();
        product = createEntity();
    }

    @Test
    public void createProduct() throws Exception {
        int databaseSizeBeforeCreate = productRepository.findAll().size();

        // Create the Product
        restProductMockMvc.perform(post("/api/products").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isCreated());

        // Validate the Product in the database
        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeCreate + 1);
        Product testProduct = productList.get(productList.size() - 1);
        assertThat(testProduct.getProductId()).isEqualTo(DEFAULT_PRODUCT_ID);
        assertThat(testProduct.getProductName()).isEqualTo(DEFAULT_PRODUCT_NAME);
        assertThat(testProduct.getProductNameChi()).isEqualTo(DEFAULT_PRODUCT_NAME_CHI);
        assertThat(testProduct.getProductDesc()).isEqualTo(DEFAULT_PRODUCT_DESC);
        assertThat(testProduct.getProductDescChi()).isEqualTo(DEFAULT_PRODUCT_DESC_CHI);
        assertThat(testProduct.getProductCate()).isEqualTo(DEFAULT_PRODUCT_CATE);
        assertThat(testProduct.getProductNature()).isEqualTo(DEFAULT_PRODUCT_NATURE);
        assertThat(testProduct.getProductFamily()).isEqualTo(DEFAULT_PRODUCT_FAMILY);
        assertThat(testProduct.getProductType()).isEqualTo(DEFAULT_PRODUCT_TYPE);
        assertThat(testProduct.getModelCode()).isEqualTo(DEFAULT_MODEL_CODE);
        assertThat(testProduct.getTempServiceId()).isEqualTo(DEFAULT_TEMP_SERVICE_ID);
        assertThat(testProduct.getTempResourceSpecIds()).isEqualTo(DEFAULT_TEMP_RESOURCE_SPEC_IDS);
        assertThat(testProduct.getProductSpecType()).isEqualTo(DEFAULT_PRODUCT_SPEC_TYPE);
        assertThat(testProduct.getSkuType()).isEqualTo(DEFAULT_SKU_TYPE);
        assertThat(testProduct.getSimType()).isEqualTo(DEFAULT_SIM_TYPE);
        assertThat(testProduct.getBoxType()).isEqualTo(DEFAULT_BOX_TYPE);
        assertThat(testProduct.isShippable()).isEqualTo(DEFAULT_SHIPPABLE);
        assertThat(testProduct.getTempDeliveryOptions()).isEqualTo(DEFAULT_TEMP_DELIVERY_OPTIONS);
        assertThat(testProduct.getTempVoiceIds()).isEqualTo(DEFAULT_TEMP_VOICE_IDS);
        assertThat(testProduct.getTempDataIds()).isEqualTo(DEFAULT_TEMP_DATA_IDS);
        assertThat(testProduct.getTempSmsIds()).isEqualTo(DEFAULT_TEMP_SMS_IDS);
        assertThat(testProduct.getTempMmsIds()).isEqualTo(DEFAULT_TEMP_MMS_IDS);
        assertThat(testProduct.getStartDate()).isEqualTo(DEFAULT_START_DATE);
        assertThat(testProduct.getEndDate()).isEqualTo(DEFAULT_END_DATE);
        assertThat(testProduct.isIndependentlyOrderable()).isEqualTo(DEFAULT_INDEPENDENTLY_ORDERABLE);
        assertThat(testProduct.isNetworkProvisionRequired()).isEqualTo(DEFAULT_NETWORK_PROVISION_REQUIRED);
        assertThat(testProduct.isChangeEntitlementAllowed()).isEqualTo(DEFAULT_CHANGE_ENTITLEMENT_ALLOWED);
        assertThat(testProduct.getLockCount()).isEqualTo(DEFAULT_LOCK_COUNT);
        assertThat(testProduct.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testProduct.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testProduct.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testProduct.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
        assertThat(testProduct.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
    }

    @Test
    public void createProductWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = productRepository.findAll().size();

        // Create the Product with an existing ID
        product.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restProductMockMvc.perform(post("/api/products").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isBadRequest());

        // Validate the Product in the database
        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkProductIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = productRepository.findAll().size();
        // set the field null
        product.setProductId(null);

        // Create the Product, which fails.

        restProductMockMvc.perform(post("/api/products").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isBadRequest());

        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkProductNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = productRepository.findAll().size();
        // set the field null
        product.setProductName(null);

        // Create the Product, which fails.

        restProductMockMvc.perform(post("/api/products").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isBadRequest());

        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = productRepository.findAll().size();
        // set the field null
        product.setCreatedDate(null);

        // Create the Product, which fails.

        restProductMockMvc.perform(post("/api/products").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isBadRequest());

        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = productRepository.findAll().size();
        // set the field null
        product.setCreatedBy(null);

        // Create the Product, which fails.

        restProductMockMvc.perform(post("/api/products").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isBadRequest());

        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = productRepository.findAll().size();
        // set the field null
        product.setLastUpdatedDate(null);

        // Create the Product, which fails.

        restProductMockMvc.perform(post("/api/products").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isBadRequest());

        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = productRepository.findAll().size();
        // set the field null
        product.setLastUpdatedBy(null);

        // Create the Product, which fails.

        restProductMockMvc.perform(post("/api/products").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isBadRequest());

        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTenantIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = productRepository.findAll().size();
        // set the field null
        product.setTenantId(null);

        // Create the Product, which fails.

        restProductMockMvc.perform(post("/api/products").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isBadRequest());

        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllProducts() throws Exception {
        // Initialize the database
        productRepository.save(product);

        // Get all the productList
        restProductMockMvc.perform(get("/api/products?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(product.getId())))
            .andExpect(jsonPath("$.[*].productId").value(hasItem(DEFAULT_PRODUCT_ID)))
            .andExpect(jsonPath("$.[*].productName").value(hasItem(DEFAULT_PRODUCT_NAME)))
            .andExpect(jsonPath("$.[*].productNameChi").value(hasItem(DEFAULT_PRODUCT_NAME_CHI)))
            .andExpect(jsonPath("$.[*].productDesc").value(hasItem(DEFAULT_PRODUCT_DESC)))
            .andExpect(jsonPath("$.[*].productDescChi").value(hasItem(DEFAULT_PRODUCT_DESC_CHI)))
            .andExpect(jsonPath("$.[*].productCate").value(hasItem(DEFAULT_PRODUCT_CATE.toString())))
            .andExpect(jsonPath("$.[*].productNature").value(hasItem(DEFAULT_PRODUCT_NATURE.toString())))
            .andExpect(jsonPath("$.[*].productFamily").value(hasItem(DEFAULT_PRODUCT_FAMILY.toString())))
            .andExpect(jsonPath("$.[*].productType").value(hasItem(DEFAULT_PRODUCT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].modelCode").value(hasItem(DEFAULT_MODEL_CODE)))
            .andExpect(jsonPath("$.[*].tempServiceId").value(hasItem(DEFAULT_TEMP_SERVICE_ID)))
            .andExpect(jsonPath("$.[*].tempResourceSpecIds").value(hasItem(DEFAULT_TEMP_RESOURCE_SPEC_IDS)))
            .andExpect(jsonPath("$.[*].productSpecType").value(hasItem(DEFAULT_PRODUCT_SPEC_TYPE.toString())))
            .andExpect(jsonPath("$.[*].skuType").value(hasItem(DEFAULT_SKU_TYPE.toString())))
            .andExpect(jsonPath("$.[*].simType").value(hasItem(DEFAULT_SIM_TYPE.toString())))
            .andExpect(jsonPath("$.[*].boxType").value(hasItem(DEFAULT_BOX_TYPE.toString())))
            .andExpect(jsonPath("$.[*].shippable").value(hasItem(DEFAULT_SHIPPABLE.booleanValue())))
            .andExpect(jsonPath("$.[*].tempDeliveryOptions").value(hasItem(DEFAULT_TEMP_DELIVERY_OPTIONS)))
            .andExpect(jsonPath("$.[*].tempVoiceIds").value(hasItem(DEFAULT_TEMP_VOICE_IDS)))
            .andExpect(jsonPath("$.[*].tempDataIds").value(hasItem(DEFAULT_TEMP_DATA_IDS)))
            .andExpect(jsonPath("$.[*].tempSmsIds").value(hasItem(DEFAULT_TEMP_SMS_IDS)))
            .andExpect(jsonPath("$.[*].tempMmsIds").value(hasItem(DEFAULT_TEMP_MMS_IDS)))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].independentlyOrderable").value(hasItem(DEFAULT_INDEPENDENTLY_ORDERABLE.booleanValue())))
            .andExpect(jsonPath("$.[*].networkProvisionRequired").value(hasItem(DEFAULT_NETWORK_PROVISION_REQUIRED.booleanValue())))
            .andExpect(jsonPath("$.[*].changeEntitlementAllowed").value(hasItem(DEFAULT_CHANGE_ENTITLEMENT_ALLOWED.booleanValue())))
            .andExpect(jsonPath("$.[*].lockCount").value(hasItem(DEFAULT_LOCK_COUNT)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].lastUpdatedDate").value(hasItem(DEFAULT_LAST_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].tenantId").value(hasItem(DEFAULT_TENANT_ID)));
    }
    
    @Test
    public void getProduct() throws Exception {
        // Initialize the database
        productRepository.save(product);

        // Get the product
        restProductMockMvc.perform(get("/api/products/{id}", product.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(product.getId()))
            .andExpect(jsonPath("$.productId").value(DEFAULT_PRODUCT_ID))
            .andExpect(jsonPath("$.productName").value(DEFAULT_PRODUCT_NAME))
            .andExpect(jsonPath("$.productNameChi").value(DEFAULT_PRODUCT_NAME_CHI))
            .andExpect(jsonPath("$.productDesc").value(DEFAULT_PRODUCT_DESC))
            .andExpect(jsonPath("$.productDescChi").value(DEFAULT_PRODUCT_DESC_CHI))
            .andExpect(jsonPath("$.productCate").value(DEFAULT_PRODUCT_CATE.toString()))
            .andExpect(jsonPath("$.productNature").value(DEFAULT_PRODUCT_NATURE.toString()))
            .andExpect(jsonPath("$.productFamily").value(DEFAULT_PRODUCT_FAMILY.toString()))
            .andExpect(jsonPath("$.productType").value(DEFAULT_PRODUCT_TYPE.toString()))
            .andExpect(jsonPath("$.modelCode").value(DEFAULT_MODEL_CODE))
            .andExpect(jsonPath("$.tempServiceId").value(DEFAULT_TEMP_SERVICE_ID))
            .andExpect(jsonPath("$.tempResourceSpecIds").value(DEFAULT_TEMP_RESOURCE_SPEC_IDS))
            .andExpect(jsonPath("$.productSpecType").value(DEFAULT_PRODUCT_SPEC_TYPE.toString()))
            .andExpect(jsonPath("$.skuType").value(DEFAULT_SKU_TYPE.toString()))
            .andExpect(jsonPath("$.simType").value(DEFAULT_SIM_TYPE.toString()))
            .andExpect(jsonPath("$.boxType").value(DEFAULT_BOX_TYPE.toString()))
            .andExpect(jsonPath("$.shippable").value(DEFAULT_SHIPPABLE.booleanValue()))
            .andExpect(jsonPath("$.tempDeliveryOptions").value(DEFAULT_TEMP_DELIVERY_OPTIONS))
            .andExpect(jsonPath("$.tempVoiceIds").value(DEFAULT_TEMP_VOICE_IDS))
            .andExpect(jsonPath("$.tempDataIds").value(DEFAULT_TEMP_DATA_IDS))
            .andExpect(jsonPath("$.tempSmsIds").value(DEFAULT_TEMP_SMS_IDS))
            .andExpect(jsonPath("$.tempMmsIds").value(DEFAULT_TEMP_MMS_IDS))
            .andExpect(jsonPath("$.startDate").value(DEFAULT_START_DATE.toString()))
            .andExpect(jsonPath("$.endDate").value(DEFAULT_END_DATE.toString()))
            .andExpect(jsonPath("$.independentlyOrderable").value(DEFAULT_INDEPENDENTLY_ORDERABLE.booleanValue()))
            .andExpect(jsonPath("$.networkProvisionRequired").value(DEFAULT_NETWORK_PROVISION_REQUIRED.booleanValue()))
            .andExpect(jsonPath("$.changeEntitlementAllowed").value(DEFAULT_CHANGE_ENTITLEMENT_ALLOWED.booleanValue()))
            .andExpect(jsonPath("$.lockCount").value(DEFAULT_LOCK_COUNT))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.lastUpdatedDate").value(DEFAULT_LAST_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY))
            .andExpect(jsonPath("$.tenantId").value(DEFAULT_TENANT_ID));
    }

    @Test
    public void getNonExistingProduct() throws Exception {
        // Get the product
        restProductMockMvc.perform(get("/api/products/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateProduct() throws Exception {
        // Initialize the database
        productService.save(product);

        int databaseSizeBeforeUpdate = productRepository.findAll().size();

        // Update the product
        Product updatedProduct = productRepository.findById(product.getId()).get();
        updatedProduct
            .productId(UPDATED_PRODUCT_ID)
            .productName(UPDATED_PRODUCT_NAME)
            .productNameChi(UPDATED_PRODUCT_NAME_CHI)
            .productDesc(UPDATED_PRODUCT_DESC)
            .productDescChi(UPDATED_PRODUCT_DESC_CHI)
            .productCate(UPDATED_PRODUCT_CATE)
            .productNature(UPDATED_PRODUCT_NATURE)
            .productFamily(UPDATED_PRODUCT_FAMILY)
            .productType(UPDATED_PRODUCT_TYPE)
            .modelCode(UPDATED_MODEL_CODE)
            .tempServiceId(UPDATED_TEMP_SERVICE_ID)
            .tempResourceSpecIds(UPDATED_TEMP_RESOURCE_SPEC_IDS)
            .productSpecType(UPDATED_PRODUCT_SPEC_TYPE)
            .skuType(UPDATED_SKU_TYPE)
            .simType(UPDATED_SIM_TYPE)
            .boxType(UPDATED_BOX_TYPE)
            .shippable(UPDATED_SHIPPABLE)
            .tempDeliveryOptions(UPDATED_TEMP_DELIVERY_OPTIONS)
            .tempVoiceIds(UPDATED_TEMP_VOICE_IDS)
            .tempDataIds(UPDATED_TEMP_DATA_IDS)
            .tempSmsIds(UPDATED_TEMP_SMS_IDS)
            .tempMmsIds(UPDATED_TEMP_MMS_IDS)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .independentlyOrderable(UPDATED_INDEPENDENTLY_ORDERABLE)
            .networkProvisionRequired(UPDATED_NETWORK_PROVISION_REQUIRED)
            .changeEntitlementAllowed(UPDATED_CHANGE_ENTITLEMENT_ALLOWED)
            .lockCount(UPDATED_LOCK_COUNT)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);

        restProductMockMvc.perform(put("/api/products").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedProduct)))
            .andExpect(status().isOk());

        // Validate the Product in the database
        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeUpdate);
        Product testProduct = productList.get(productList.size() - 1);
        assertThat(testProduct.getProductId()).isEqualTo(UPDATED_PRODUCT_ID);
        assertThat(testProduct.getProductName()).isEqualTo(UPDATED_PRODUCT_NAME);
        assertThat(testProduct.getProductNameChi()).isEqualTo(UPDATED_PRODUCT_NAME_CHI);
        assertThat(testProduct.getProductDesc()).isEqualTo(UPDATED_PRODUCT_DESC);
        assertThat(testProduct.getProductDescChi()).isEqualTo(UPDATED_PRODUCT_DESC_CHI);
        assertThat(testProduct.getProductCate()).isEqualTo(UPDATED_PRODUCT_CATE);
        assertThat(testProduct.getProductNature()).isEqualTo(UPDATED_PRODUCT_NATURE);
        assertThat(testProduct.getProductFamily()).isEqualTo(UPDATED_PRODUCT_FAMILY);
        assertThat(testProduct.getProductType()).isEqualTo(UPDATED_PRODUCT_TYPE);
        assertThat(testProduct.getModelCode()).isEqualTo(UPDATED_MODEL_CODE);
        assertThat(testProduct.getTempServiceId()).isEqualTo(UPDATED_TEMP_SERVICE_ID);
        assertThat(testProduct.getTempResourceSpecIds()).isEqualTo(UPDATED_TEMP_RESOURCE_SPEC_IDS);
        assertThat(testProduct.getProductSpecType()).isEqualTo(UPDATED_PRODUCT_SPEC_TYPE);
        assertThat(testProduct.getSkuType()).isEqualTo(UPDATED_SKU_TYPE);
        assertThat(testProduct.getSimType()).isEqualTo(UPDATED_SIM_TYPE);
        assertThat(testProduct.getBoxType()).isEqualTo(UPDATED_BOX_TYPE);
        assertThat(testProduct.isShippable()).isEqualTo(UPDATED_SHIPPABLE);
        assertThat(testProduct.getTempDeliveryOptions()).isEqualTo(UPDATED_TEMP_DELIVERY_OPTIONS);
        assertThat(testProduct.getTempVoiceIds()).isEqualTo(UPDATED_TEMP_VOICE_IDS);
        assertThat(testProduct.getTempDataIds()).isEqualTo(UPDATED_TEMP_DATA_IDS);
        assertThat(testProduct.getTempSmsIds()).isEqualTo(UPDATED_TEMP_SMS_IDS);
        assertThat(testProduct.getTempMmsIds()).isEqualTo(UPDATED_TEMP_MMS_IDS);
        assertThat(testProduct.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testProduct.getEndDate()).isEqualTo(UPDATED_END_DATE);
        assertThat(testProduct.isIndependentlyOrderable()).isEqualTo(UPDATED_INDEPENDENTLY_ORDERABLE);
        assertThat(testProduct.isNetworkProvisionRequired()).isEqualTo(UPDATED_NETWORK_PROVISION_REQUIRED);
        assertThat(testProduct.isChangeEntitlementAllowed()).isEqualTo(UPDATED_CHANGE_ENTITLEMENT_ALLOWED);
        assertThat(testProduct.getLockCount()).isEqualTo(UPDATED_LOCK_COUNT);
        assertThat(testProduct.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testProduct.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testProduct.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testProduct.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
        assertThat(testProduct.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    public void updateNonExistingProduct() throws Exception {
        int databaseSizeBeforeUpdate = productRepository.findAll().size();

        // Create the Product

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProductMockMvc.perform(put("/api/products").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isBadRequest());

        // Validate the Product in the database
        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteProduct() throws Exception {
        // Initialize the database
        productService.save(product);

        int databaseSizeBeforeDelete = productRepository.findAll().size();

        // Delete the product
        restProductMockMvc.perform(delete("/api/products/{id}", product.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
