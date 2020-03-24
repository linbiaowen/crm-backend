package com.hthk.crm.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import com.hthk.crm.domain.enumeration.ProductCate;

import com.hthk.crm.domain.enumeration.ProductNature;

import com.hthk.crm.domain.enumeration.ProductFamily;

import com.hthk.crm.domain.enumeration.ProductType;

import com.hthk.crm.domain.enumeration.ProductSpecType;

import com.hthk.crm.domain.enumeration.SkuType;

import com.hthk.crm.domain.enumeration.SimType;

import com.hthk.crm.domain.enumeration.BoxType;

/**
 * A Product.
 */
@Document(collection = "product")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("product_id")
    private String productId;

    @NotNull
    @Field("product_name")
    private String productName;

    @Field("product_name_chi")
    private String productNameChi;

    @Field("product_desc")
    private String productDesc;

    @Field("product_desc_chi")
    private String productDescChi;

    @Field("product_cate")
    private ProductCate productCate;

    @Field("product_nature")
    private ProductNature productNature;

    @Field("product_family")
    private ProductFamily productFamily;

    @Field("product_type")
    private ProductType productType;

    @Field("model_code")
    private String modelCode;

    @Field("temp_service_id")
    private String tempServiceId;

    @Field("temp_resource_spec_ids")
    private String tempResourceSpecIds;

    @Field("product_spec_type")
    private ProductSpecType productSpecType;

    @Field("sku_type")
    private SkuType skuType;

    @Field("sim_type")
    private SimType simType;

    @Field("box_type")
    private BoxType boxType;

    @Field("shippable")
    private Boolean shippable;

    @Field("temp_delivery_options")
    private String tempDeliveryOptions;

    @Field("temp_voice_ids")
    private String tempVoiceIds;

    @Field("temp_data_ids")
    private String tempDataIds;

    @Field("temp_sms_ids")
    private String tempSmsIds;

    @Field("temp_mms_ids")
    private String tempMmsIds;

    @Field("temp_image_ids")
    private String tempImageIds;

    @Field("start_date")
    private Instant startDate;

    @Field("end_date")
    private Instant endDate;

    @Field("independently_orderable")
    private Boolean independentlyOrderable;

    @Field("network_provision_required")
    private Boolean networkProvisionRequired;

    @Field("change_entitlement_allowed")
    private Boolean changeEntitlementAllowed;

    @Field("lock_count")
    private Integer lockCount;

    @NotNull
    @Field("created_date")
    private Instant createdDate;

    @NotNull
    @Field("created_by")
    private String createdBy;

    @NotNull
    @Field("last_updated_date")
    private Instant lastUpdatedDate;

    @NotNull
    @Field("last_updated_by")
    private String lastUpdatedBy;

    @NotNull
    @Field("tenant_id")
    private String tenantId;

    //@DBRef
    @Field("productVoice")
    private ProductVoice productVoice;

    //@DBRef
    @Field("productData")
    private ProductData productData;

    //@DBRef
    @Field("productSms")
    private ProductSms productSms;

    //@DBRef
    @Field("productMms")
    private ProductMms productMms;

    //@DBRef
    @Field("cfsService")
    private CfsService cfsService;

    //@DBRef
    @Field("deliveryOptions")
    private Set<DeliveryOption> deliveryOptions = new HashSet<>();

    //@DBRef
    @Field("resourceSpecifications")
    private Set<ResourceSpecification> resourceSpecifications = new HashSet<>();

    //@DBRef
    @Field("images")
    private Set<Image> images = new HashSet<>();

    @DBRef
    @Field("offer")
    @JsonIgnoreProperties("products")
    private Offer offer;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public Product productId(String productId) {
        this.productId = productId;
        return this;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public Product productName(String productName) {
        this.productName = productName;
        return this;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductNameChi() {
        return productNameChi;
    }

    public Product productNameChi(String productNameChi) {
        this.productNameChi = productNameChi;
        return this;
    }

    public void setProductNameChi(String productNameChi) {
        this.productNameChi = productNameChi;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public Product productDesc(String productDesc) {
        this.productDesc = productDesc;
        return this;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getProductDescChi() {
        return productDescChi;
    }

    public Product productDescChi(String productDescChi) {
        this.productDescChi = productDescChi;
        return this;
    }

    public void setProductDescChi(String productDescChi) {
        this.productDescChi = productDescChi;
    }

    public ProductCate getProductCate() {
        return productCate;
    }

    public Product productCate(ProductCate productCate) {
        this.productCate = productCate;
        return this;
    }

    public void setProductCate(ProductCate productCate) {
        this.productCate = productCate;
    }

    public ProductNature getProductNature() {
        return productNature;
    }

    public Product productNature(ProductNature productNature) {
        this.productNature = productNature;
        return this;
    }

    public void setProductNature(ProductNature productNature) {
        this.productNature = productNature;
    }

    public ProductFamily getProductFamily() {
        return productFamily;
    }

    public Product productFamily(ProductFamily productFamily) {
        this.productFamily = productFamily;
        return this;
    }

    public void setProductFamily(ProductFamily productFamily) {
        this.productFamily = productFamily;
    }

    public ProductType getProductType() {
        return productType;
    }

    public Product productType(ProductType productType) {
        this.productType = productType;
        return this;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public String getModelCode() {
        return modelCode;
    }

    public Product modelCode(String modelCode) {
        this.modelCode = modelCode;
        return this;
    }

    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }

    public String getTempServiceId() {
        return tempServiceId;
    }

    public Product tempServiceId(String tempServiceId) {
        this.tempServiceId = tempServiceId;
        return this;
    }

    public void setTempServiceId(String tempServiceId) {
        this.tempServiceId = tempServiceId;
    }

    public String getTempResourceSpecIds() {
        return tempResourceSpecIds;
    }

    public Product tempResourceSpecIds(String tempResourceSpecIds) {
        this.tempResourceSpecIds = tempResourceSpecIds;
        return this;
    }

    public void setTempResourceSpecIds(String tempResourceSpecIds) {
        this.tempResourceSpecIds = tempResourceSpecIds;
    }

    public ProductSpecType getProductSpecType() {
        return productSpecType;
    }

    public Product productSpecType(ProductSpecType productSpecType) {
        this.productSpecType = productSpecType;
        return this;
    }

    public void setProductSpecType(ProductSpecType productSpecType) {
        this.productSpecType = productSpecType;
    }

    public SkuType getSkuType() {
        return skuType;
    }

    public Product skuType(SkuType skuType) {
        this.skuType = skuType;
        return this;
    }

    public void setSkuType(SkuType skuType) {
        this.skuType = skuType;
    }

    public SimType getSimType() {
        return simType;
    }

    public Product simType(SimType simType) {
        this.simType = simType;
        return this;
    }

    public void setSimType(SimType simType) {
        this.simType = simType;
    }

    public BoxType getBoxType() {
        return boxType;
    }

    public Product boxType(BoxType boxType) {
        this.boxType = boxType;
        return this;
    }

    public void setBoxType(BoxType boxType) {
        this.boxType = boxType;
    }

    public Boolean isShippable() {
        return shippable;
    }

    public Product shippable(Boolean shippable) {
        this.shippable = shippable;
        return this;
    }

    public void setShippable(Boolean shippable) {
        this.shippable = shippable;
    }

    public String getTempDeliveryOptions() {
        return tempDeliveryOptions;
    }

    public Product tempDeliveryOptions(String tempDeliveryOptions) {
        this.tempDeliveryOptions = tempDeliveryOptions;
        return this;
    }

    public void setTempDeliveryOptions(String tempDeliveryOptions) {
        this.tempDeliveryOptions = tempDeliveryOptions;
    }

    public String getTempVoiceIds() {
        return tempVoiceIds;
    }

    public Product tempVoiceIds(String tempVoiceIds) {
        this.tempVoiceIds = tempVoiceIds;
        return this;
    }

    public void setTempVoiceIds(String tempVoiceIds) {
        this.tempVoiceIds = tempVoiceIds;
    }

    public String getTempDataIds() {
        return tempDataIds;
    }

    public Product tempDataIds(String tempDataIds) {
        this.tempDataIds = tempDataIds;
        return this;
    }

    public void setTempDataIds(String tempDataIds) {
        this.tempDataIds = tempDataIds;
    }

    public String getTempSmsIds() {
        return tempSmsIds;
    }

    public Product tempSmsIds(String tempSmsIds) {
        this.tempSmsIds = tempSmsIds;
        return this;
    }

    public void setTempSmsIds(String tempSmsIds) {
        this.tempSmsIds = tempSmsIds;
    }

    public String getTempMmsIds() {
        return tempMmsIds;
    }

    public Product tempMmsIds(String tempMmsIds) {
        this.tempMmsIds = tempMmsIds;
        return this;
    }

    public void setTempMmsIds(String tempMmsIds) {
        this.tempMmsIds = tempMmsIds;
    }

    public String getTempImageIds() {
        return tempImageIds;
    }

    public Product tempImageIds(String tempImageIds) {
        this.tempImageIds = tempImageIds;
        return this;
    }

    public void setTempImageIds(String tempImageIds) {
        this.tempImageIds = tempImageIds;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public Product startDate(Instant startDate) {
        this.startDate = startDate;
        return this;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public Product endDate(Instant endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    public Boolean isIndependentlyOrderable() {
        return independentlyOrderable;
    }

    public Product independentlyOrderable(Boolean independentlyOrderable) {
        this.independentlyOrderable = independentlyOrderable;
        return this;
    }

    public void setIndependentlyOrderable(Boolean independentlyOrderable) {
        this.independentlyOrderable = independentlyOrderable;
    }

    public Boolean isNetworkProvisionRequired() {
        return networkProvisionRequired;
    }

    public Product networkProvisionRequired(Boolean networkProvisionRequired) {
        this.networkProvisionRequired = networkProvisionRequired;
        return this;
    }

    public void setNetworkProvisionRequired(Boolean networkProvisionRequired) {
        this.networkProvisionRequired = networkProvisionRequired;
    }

    public Boolean isChangeEntitlementAllowed() {
        return changeEntitlementAllowed;
    }

    public Product changeEntitlementAllowed(Boolean changeEntitlementAllowed) {
        this.changeEntitlementAllowed = changeEntitlementAllowed;
        return this;
    }

    public void setChangeEntitlementAllowed(Boolean changeEntitlementAllowed) {
        this.changeEntitlementAllowed = changeEntitlementAllowed;
    }

    public Integer getLockCount() {
        return lockCount;
    }

    public Product lockCount(Integer lockCount) {
        this.lockCount = lockCount;
        return this;
    }

    public void setLockCount(Integer lockCount) {
        this.lockCount = lockCount;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public Product createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Product createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public Product lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public Product lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getTenantId() {
        return tenantId;
    }

    public Product tenantId(String tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public ProductVoice getProductVoice() {
        return productVoice;
    }

    public Product productVoice(ProductVoice productVoice) {
        this.productVoice = productVoice;
        return this;
    }

    public void setProductVoice(ProductVoice productVoice) {
        this.productVoice = productVoice;
    }

    public ProductData getProductData() {
        return productData;
    }

    public Product productData(ProductData productData) {
        this.productData = productData;
        return this;
    }

    public void setProductData(ProductData productData) {
        this.productData = productData;
    }

    public ProductSms getProductSms() {
        return productSms;
    }

    public Product productSms(ProductSms productSms) {
        this.productSms = productSms;
        return this;
    }

    public void setProductSms(ProductSms productSms) {
        this.productSms = productSms;
    }

    public ProductMms getProductMms() {
        return productMms;
    }

    public Product productMms(ProductMms productMms) {
        this.productMms = productMms;
        return this;
    }

    public void setProductMms(ProductMms productMms) {
        this.productMms = productMms;
    }

    public CfsService getCfsService() {
        return cfsService;
    }

    public Product cfsService(CfsService cfsService) {
        this.cfsService = cfsService;
        return this;
    }

    public void setCfsService(CfsService cfsService) {
        this.cfsService = cfsService;
    }

    public Set<DeliveryOption> getDeliveryOptions() {
        return deliveryOptions;
    }

    public Product deliveryOptions(Set<DeliveryOption> deliveryOptions) {
        this.deliveryOptions = deliveryOptions;
        return this;
    }

    public Product addDeliveryOptions(DeliveryOption deliveryOption) {
        this.deliveryOptions.add(deliveryOption);
        deliveryOption.setProduct(this);
        return this;
    }

    public Product removeDeliveryOptions(DeliveryOption deliveryOption) {
        this.deliveryOptions.remove(deliveryOption);
        deliveryOption.setProduct(null);
        return this;
    }

    public void setDeliveryOptions(Set<DeliveryOption> deliveryOptions) {
        this.deliveryOptions = deliveryOptions;
    }

    public Set<ResourceSpecification> getResourceSpecifications() {
        return resourceSpecifications;
    }

    public Product resourceSpecifications(Set<ResourceSpecification> resourceSpecifications) {
        this.resourceSpecifications = resourceSpecifications;
        return this;
    }

    public Product addResourceSpecifications(ResourceSpecification resourceSpecification) {
        this.resourceSpecifications.add(resourceSpecification);
        resourceSpecification.setProduct(this);
        return this;
    }

    public Product removeResourceSpecifications(ResourceSpecification resourceSpecification) {
        this.resourceSpecifications.remove(resourceSpecification);
        resourceSpecification.setProduct(null);
        return this;
    }

    public void setResourceSpecifications(Set<ResourceSpecification> resourceSpecifications) {
        this.resourceSpecifications = resourceSpecifications;
    }

    public Set<Image> getImages() {
        return images;
    }

    public Product images(Set<Image> images) {
        this.images = images;
        return this;
    }

    public Product addImages(Image image) {
        this.images.add(image);
        image.setProduct(this);
        return this;
    }

    public Product removeImages(Image image) {
        this.images.remove(image);
        image.setProduct(null);
        return this;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }

    public Offer getOffer() {
        return offer;
    }

    public Product offer(Offer offer) {
        this.offer = offer;
        return this;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Product)) {
            return false;
        }
        return id != null && id.equals(((Product) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Product{" +
            "id=" + getId() +
            ", productId='" + getProductId() + "'" +
            ", productName='" + getProductName() + "'" +
            ", productNameChi='" + getProductNameChi() + "'" +
            ", productDesc='" + getProductDesc() + "'" +
            ", productDescChi='" + getProductDescChi() + "'" +
            ", productCate='" + getProductCate() + "'" +
            ", productNature='" + getProductNature() + "'" +
            ", productFamily='" + getProductFamily() + "'" +
            ", productType='" + getProductType() + "'" +
            ", modelCode='" + getModelCode() + "'" +
            ", tempServiceId='" + getTempServiceId() + "'" +
            ", tempResourceSpecIds='" + getTempResourceSpecIds() + "'" +
            ", productSpecType='" + getProductSpecType() + "'" +
            ", skuType='" + getSkuType() + "'" +
            ", simType='" + getSimType() + "'" +
            ", boxType='" + getBoxType() + "'" +
            ", shippable='" + isShippable() + "'" +
            ", tempDeliveryOptions='" + getTempDeliveryOptions() + "'" +
            ", tempVoiceIds='" + getTempVoiceIds() + "'" +
            ", tempDataIds='" + getTempDataIds() + "'" +
            ", tempSmsIds='" + getTempSmsIds() + "'" +
            ", tempMmsIds='" + getTempMmsIds() + "'" +
            ", tempImageIds='" + getTempImageIds() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", independentlyOrderable='" + isIndependentlyOrderable() + "'" +
            ", networkProvisionRequired='" + isNetworkProvisionRequired() + "'" +
            ", changeEntitlementAllowed='" + isChangeEntitlementAllowed() + "'" +
            ", lockCount=" + getLockCount() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", tenantId='" + getTenantId() + "'" +
            "}";
    }
}
