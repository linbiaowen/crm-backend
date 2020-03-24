package com.hthk.crm.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import com.hthk.crm.domain.enumeration.OfferType;

/**
 * A Offer.
 */
@Document(collection = "offer")
public class Offer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("offer_id")
    private String offerId;

    @Field("offer_external_id")
    private String offerExternalId;

    @NotNull
    @Field("offer_name")
    private String offerName;

    @Field("offer_name_chi")
    private String offerNameChi;

    @Field("offer_desc")
    private String offerDesc;

    @Field("offer_desc_chi")
    private String offerDescChi;

    @Field("offer_type")
    private OfferType offerType;

    @Field("offer_price")
    private BigDecimal offerPrice;

    @Field("temp_customer_segments")
    private String tempCustomerSegments;

    @Field("temp_customer_classes")
    private String tempCustomerClasses;

    @Field("temp_sales_channels")
    private String tempSalesChannels;

    @Field("start_date")
    private Instant startDate;

    @Field("end_date")
    private Instant endDate;

    @Field("temp_child_offer_ids")
    private String tempChildOfferIds;

    @Field("temp_product_ids")
    private String tempProductIds;

    @Field("temp_advance_payment_ids")
    private String tempAdvancePaymentIds;

    @Field("temp_promo_codes")
    private String tempPromoCodes;

    @Field("temp_discount_codes")
    private String tempDiscountCodes;

    @Field("temp_image_ids")
    private String tempImageIds;

    @Field("limited_activation_period")
    private Boolean limitedActivationPeriod;

    @Field("allowed_activation_start_date")
    private Instant allowedActivationStartDate;

    @Field("allowed_activation_end_date")
    private Instant allowedActivationEndDate;

    @Field("is_group_sharing_offer")
    private Boolean isGroupSharingOffer;

    @Field("is_mnp_offer")
    private Boolean isMnpOffer;

    @Field("auto_renewal")
    private Boolean autoRenewal;

    @Field("transfer_allowed")
    private Boolean transferAllowed;

    @Field("info_sharing_allowed")
    private Boolean infoSharingAllowed;

    @Field("info_sharing_options")
    private String infoSharingOptions;

    @Field("offer_period")
    private Integer offerPeriod;

    @Field("offer_period_term")
    private String offerPeriodTerm;

    @Field("payment_type")
    private String paymentType;

    @Field("priority")
    private Integer priority;

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
    @Field("customerSegments")
    private Set<OfferCustomerSegment> customerSegments = new HashSet<>();

    //@DBRef
    @Field("customerClasses")
    private Set<OfferCustomerClass> customerClasses = new HashSet<>();

    //@DBRef
    @Field("salesChannels")
    private Set<OfferSalesChannel> salesChannels = new HashSet<>();

    //@DBRef
    @Field("products")
    private Set<Product> products = new HashSet<>();

    //@DBRef
    @Field("offerAdvancePayment")
    private Set<OfferAdvancePayment> offerAdvancePayments = new HashSet<>();

    //@DBRef
    @Field("offerPromotions")
    private Set<OfferPromotion> offerPromotions = new HashSet<>();

    //@DBRef
    @Field("offerDiscounts")
    private Set<OfferDiscount> offerDiscounts = new HashSet<>();

    //@DBRef
    @Field("images")
    private Set<Image> images = new HashSet<>();

    @DBRef
    @Field("parentOffers")
    private Set<Offer> parentOffers = new HashSet<>();

    @DBRef
    @Field("childOffers")
    @JsonIgnore
    private Set<Offer> childOffers = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOfferId() {
        return offerId;
    }

    public Offer offerId(String offerId) {
        this.offerId = offerId;
        return this;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public String getOfferExternalId() {
        return offerExternalId;
    }

    public Offer offerExternalId(String offerExternalId) {
        this.offerExternalId = offerExternalId;
        return this;
    }

    public void setOfferExternalId(String offerExternalId) {
        this.offerExternalId = offerExternalId;
    }

    public String getOfferName() {
        return offerName;
    }

    public Offer offerName(String offerName) {
        this.offerName = offerName;
        return this;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }

    public String getOfferNameChi() {
        return offerNameChi;
    }

    public Offer offerNameChi(String offerNameChi) {
        this.offerNameChi = offerNameChi;
        return this;
    }

    public void setOfferNameChi(String offerNameChi) {
        this.offerNameChi = offerNameChi;
    }

    public String getOfferDesc() {
        return offerDesc;
    }

    public Offer offerDesc(String offerDesc) {
        this.offerDesc = offerDesc;
        return this;
    }

    public void setOfferDesc(String offerDesc) {
        this.offerDesc = offerDesc;
    }

    public String getOfferDescChi() {
        return offerDescChi;
    }

    public Offer offerDescChi(String offerDescChi) {
        this.offerDescChi = offerDescChi;
        return this;
    }

    public void setOfferDescChi(String offerDescChi) {
        this.offerDescChi = offerDescChi;
    }

    public OfferType getOfferType() {
        return offerType;
    }

    public Offer offerType(OfferType offerType) {
        this.offerType = offerType;
        return this;
    }

    public void setOfferType(OfferType offerType) {
        this.offerType = offerType;
    }

    public BigDecimal getOfferPrice() {
        return offerPrice;
    }

    public Offer offerPrice(BigDecimal offerPrice) {
        this.offerPrice = offerPrice;
        return this;
    }

    public void setOfferPrice(BigDecimal offerPrice) {
        this.offerPrice = offerPrice;
    }

    public String getTempCustomerSegments() {
        return tempCustomerSegments;
    }

    public Offer tempCustomerSegments(String tempCustomerSegments) {
        this.tempCustomerSegments = tempCustomerSegments;
        return this;
    }

    public void setTempCustomerSegments(String tempCustomerSegments) {
        this.tempCustomerSegments = tempCustomerSegments;
    }

    public String getTempCustomerClasses() {
        return tempCustomerClasses;
    }

    public Offer tempCustomerClasses(String tempCustomerClasses) {
        this.tempCustomerClasses = tempCustomerClasses;
        return this;
    }

    public void setTempCustomerClasses(String tempCustomerClasses) {
        this.tempCustomerClasses = tempCustomerClasses;
    }

    public String getTempSalesChannels() {
        return tempSalesChannels;
    }

    public Offer tempSalesChannels(String tempSalesChannels) {
        this.tempSalesChannels = tempSalesChannels;
        return this;
    }

    public void setTempSalesChannels(String tempSalesChannels) {
        this.tempSalesChannels = tempSalesChannels;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public Offer startDate(Instant startDate) {
        this.startDate = startDate;
        return this;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public Offer endDate(Instant endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    public String getTempChildOfferIds() {
        return tempChildOfferIds;
    }

    public Offer tempChildOfferIds(String tempChildOfferIds) {
        this.tempChildOfferIds = tempChildOfferIds;
        return this;
    }

    public void setTempChildOfferIds(String tempChildOfferIds) {
        this.tempChildOfferIds = tempChildOfferIds;
    }

    public String getTempProductIds() {
        return tempProductIds;
    }

    public Offer tempProductIds(String tempProductIds) {
        this.tempProductIds = tempProductIds;
        return this;
    }

    public void setTempProductIds(String tempProductIds) {
        this.tempProductIds = tempProductIds;
    }

    public String getTempAdvancePaymentIds() {
        return tempAdvancePaymentIds;
    }

    public Offer tempAdvancePaymentIds(String tempAdvancePaymentIds) {
        this.tempAdvancePaymentIds = tempAdvancePaymentIds;
        return this;
    }

    public void setTempAdvancePaymentIds(String tempAdvancePaymentIds) {
        this.tempAdvancePaymentIds = tempAdvancePaymentIds;
    }

    public String getTempPromoCodes() {
        return tempPromoCodes;
    }

    public Offer tempPromoCodes(String tempPromoCodes) {
        this.tempPromoCodes = tempPromoCodes;
        return this;
    }

    public void setTempPromoCodes(String tempPromoCodes) {
        this.tempPromoCodes = tempPromoCodes;
    }

    public String getTempDiscountCodes() {
        return tempDiscountCodes;
    }

    public Offer tempDiscountCodes(String tempDiscountCodes) {
        this.tempDiscountCodes = tempDiscountCodes;
        return this;
    }

    public void setTempDiscountCodes(String tempDiscountCodes) {
        this.tempDiscountCodes = tempDiscountCodes;
    }

    public String getTempImageIds() {
        return tempImageIds;
    }

    public Offer tempImageIds(String tempImageIds) {
        this.tempImageIds = tempImageIds;
        return this;
    }

    public void setTempImageIds(String tempImageIds) {
        this.tempImageIds = tempImageIds;
    }

    public Boolean isLimitedActivationPeriod() {
        return limitedActivationPeriod;
    }

    public Offer limitedActivationPeriod(Boolean limitedActivationPeriod) {
        this.limitedActivationPeriod = limitedActivationPeriod;
        return this;
    }

    public void setLimitedActivationPeriod(Boolean limitedActivationPeriod) {
        this.limitedActivationPeriod = limitedActivationPeriod;
    }

    public Instant getAllowedActivationStartDate() {
        return allowedActivationStartDate;
    }

    public Offer allowedActivationStartDate(Instant allowedActivationStartDate) {
        this.allowedActivationStartDate = allowedActivationStartDate;
        return this;
    }

    public void setAllowedActivationStartDate(Instant allowedActivationStartDate) {
        this.allowedActivationStartDate = allowedActivationStartDate;
    }

    public Instant getAllowedActivationEndDate() {
        return allowedActivationEndDate;
    }

    public Offer allowedActivationEndDate(Instant allowedActivationEndDate) {
        this.allowedActivationEndDate = allowedActivationEndDate;
        return this;
    }

    public void setAllowedActivationEndDate(Instant allowedActivationEndDate) {
        this.allowedActivationEndDate = allowedActivationEndDate;
    }

    public Boolean isIsGroupSharingOffer() {
        return isGroupSharingOffer;
    }

    public Offer isGroupSharingOffer(Boolean isGroupSharingOffer) {
        this.isGroupSharingOffer = isGroupSharingOffer;
        return this;
    }

    public void setIsGroupSharingOffer(Boolean isGroupSharingOffer) {
        this.isGroupSharingOffer = isGroupSharingOffer;
    }

    public Boolean isIsMnpOffer() {
        return isMnpOffer;
    }

    public Offer isMnpOffer(Boolean isMnpOffer) {
        this.isMnpOffer = isMnpOffer;
        return this;
    }

    public void setIsMnpOffer(Boolean isMnpOffer) {
        this.isMnpOffer = isMnpOffer;
    }

    public Boolean isAutoRenewal() {
        return autoRenewal;
    }

    public Offer autoRenewal(Boolean autoRenewal) {
        this.autoRenewal = autoRenewal;
        return this;
    }

    public void setAutoRenewal(Boolean autoRenewal) {
        this.autoRenewal = autoRenewal;
    }

    public Boolean isTransferAllowed() {
        return transferAllowed;
    }

    public Offer transferAllowed(Boolean transferAllowed) {
        this.transferAllowed = transferAllowed;
        return this;
    }

    public void setTransferAllowed(Boolean transferAllowed) {
        this.transferAllowed = transferAllowed;
    }

    public Boolean isInfoSharingAllowed() {
        return infoSharingAllowed;
    }

    public Offer infoSharingAllowed(Boolean infoSharingAllowed) {
        this.infoSharingAllowed = infoSharingAllowed;
        return this;
    }

    public void setInfoSharingAllowed(Boolean infoSharingAllowed) {
        this.infoSharingAllowed = infoSharingAllowed;
    }

    public String getInfoSharingOptions() {
        return infoSharingOptions;
    }

    public Offer infoSharingOptions(String infoSharingOptions) {
        this.infoSharingOptions = infoSharingOptions;
        return this;
    }

    public void setInfoSharingOptions(String infoSharingOptions) {
        this.infoSharingOptions = infoSharingOptions;
    }

    public Integer getOfferPeriod() {
        return offerPeriod;
    }

    public Offer offerPeriod(Integer offerPeriod) {
        this.offerPeriod = offerPeriod;
        return this;
    }

    public void setOfferPeriod(Integer offerPeriod) {
        this.offerPeriod = offerPeriod;
    }

    public String getOfferPeriodTerm() {
        return offerPeriodTerm;
    }

    public Offer offerPeriodTerm(String offerPeriodTerm) {
        this.offerPeriodTerm = offerPeriodTerm;
        return this;
    }

    public void setOfferPeriodTerm(String offerPeriodTerm) {
        this.offerPeriodTerm = offerPeriodTerm;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public Offer paymentType(String paymentType) {
        this.paymentType = paymentType;
        return this;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Integer getPriority() {
        return priority;
    }

    public Offer priority(Integer priority) {
        this.priority = priority;
        return this;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getLockCount() {
        return lockCount;
    }

    public Offer lockCount(Integer lockCount) {
        this.lockCount = lockCount;
        return this;
    }

    public void setLockCount(Integer lockCount) {
        this.lockCount = lockCount;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public Offer createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Offer createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public Offer lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public Offer lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getTenantId() {
        return tenantId;
    }

    public Offer tenantId(String tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public Set<OfferCustomerSegment> getCustomerSegments() {
        return customerSegments;
    }

    public Offer customerSegments(Set<OfferCustomerSegment> offerCustomerSegments) {
        this.customerSegments = offerCustomerSegments;
        return this;
    }

    public Offer addCustomerSegments(OfferCustomerSegment offerCustomerSegment) {
        this.customerSegments.add(offerCustomerSegment);
        offerCustomerSegment.setOffer(this);
        return this;
    }

    public Offer removeCustomerSegments(OfferCustomerSegment offerCustomerSegment) {
        this.customerSegments.remove(offerCustomerSegment);
        offerCustomerSegment.setOffer(null);
        return this;
    }

    public void setCustomerSegments(Set<OfferCustomerSegment> offerCustomerSegments) {
        this.customerSegments = offerCustomerSegments;
    }

    public Set<OfferCustomerClass> getCustomerClasses() {
        return customerClasses;
    }

    public Offer customerClasses(Set<OfferCustomerClass> offerCustomerClasses) {
        this.customerClasses = offerCustomerClasses;
        return this;
    }

    public Offer addCustomerClasses(OfferCustomerClass offerCustomerClass) {
        this.customerClasses.add(offerCustomerClass);
        offerCustomerClass.setOffer(this);
        return this;
    }

    public Offer removeCustomerClasses(OfferCustomerClass offerCustomerClass) {
        this.customerClasses.remove(offerCustomerClass);
        offerCustomerClass.setOffer(null);
        return this;
    }

    public void setCustomerClasses(Set<OfferCustomerClass> offerCustomerClasses) {
        this.customerClasses = offerCustomerClasses;
    }

    public Set<OfferSalesChannel> getSalesChannels() {
        return salesChannels;
    }

    public Offer salesChannels(Set<OfferSalesChannel> offerSalesChannels) {
        this.salesChannels = offerSalesChannels;
        return this;
    }

    public Offer addSalesChannels(OfferSalesChannel offerSalesChannel) {
        this.salesChannels.add(offerSalesChannel);
        offerSalesChannel.setOffer(this);
        return this;
    }

    public Offer removeSalesChannels(OfferSalesChannel offerSalesChannel) {
        this.salesChannels.remove(offerSalesChannel);
        offerSalesChannel.setOffer(null);
        return this;
    }

    public void setSalesChannels(Set<OfferSalesChannel> offerSalesChannels) {
        this.salesChannels = offerSalesChannels;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public Offer products(Set<Product> products) {
        this.products = products;
        return this;
    }

    public Offer addProducts(Product product) {
        this.products.add(product);
        product.setOffer(this);
        return this;
    }

    public Offer removeProducts(Product product) {
        this.products.remove(product);
        product.setOffer(null);
        return this;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Set<OfferAdvancePayment> getOfferAdvancePayments() {
        return offerAdvancePayments;
    }

    public Offer offerAdvancePayments(Set<OfferAdvancePayment> offerAdvancePayments) {
        this.offerAdvancePayments = offerAdvancePayments;
        return this;
    }

    public Offer addOfferAdvancePayment(OfferAdvancePayment offerAdvancePayment) {
        this.offerAdvancePayments.add(offerAdvancePayment);
        offerAdvancePayment.setOffer(this);
        return this;
    }

    public Offer removeOfferAdvancePayment(OfferAdvancePayment offerAdvancePayment) {
        this.offerAdvancePayments.remove(offerAdvancePayment);
        offerAdvancePayment.setOffer(null);
        return this;
    }

    public void setOfferAdvancePayments(Set<OfferAdvancePayment> offerAdvancePayments) {
        this.offerAdvancePayments = offerAdvancePayments;
    }

    public Set<OfferPromotion> getOfferPromotions() {
        return offerPromotions;
    }

    public Offer offerPromotions(Set<OfferPromotion> offerPromotions) {
        this.offerPromotions = offerPromotions;
        return this;
    }

    public Offer addOfferPromotions(OfferPromotion offerPromotion) {
        this.offerPromotions.add(offerPromotion);
        offerPromotion.setOffer(this);
        return this;
    }

    public Offer removeOfferPromotions(OfferPromotion offerPromotion) {
        this.offerPromotions.remove(offerPromotion);
        offerPromotion.setOffer(null);
        return this;
    }

    public void setOfferPromotions(Set<OfferPromotion> offerPromotions) {
        this.offerPromotions = offerPromotions;
    }

    public Set<OfferDiscount> getOfferDiscounts() {
        return offerDiscounts;
    }

    public Offer offerDiscounts(Set<OfferDiscount> offerDiscounts) {
        this.offerDiscounts = offerDiscounts;
        return this;
    }

    public Offer addOfferDiscounts(OfferDiscount offerDiscount) {
        this.offerDiscounts.add(offerDiscount);
        offerDiscount.setOffer(this);
        return this;
    }

    public Offer removeOfferDiscounts(OfferDiscount offerDiscount) {
        this.offerDiscounts.remove(offerDiscount);
        offerDiscount.setOffer(null);
        return this;
    }

    public void setOfferDiscounts(Set<OfferDiscount> offerDiscounts) {
        this.offerDiscounts = offerDiscounts;
    }

    public Set<Image> getImages() {
        return images;
    }

    public Offer images(Set<Image> images) {
        this.images = images;
        return this;
    }

    public Offer addImages(Image image) {
        this.images.add(image);
        image.setOffer(this);
        return this;
    }

    public Offer removeImages(Image image) {
        this.images.remove(image);
        image.setOffer(null);
        return this;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }

    public Set<Offer> getParentOffers() {
        return parentOffers;
    }

    public Offer parentOffers(Set<Offer> offers) {
        this.parentOffers = offers;
        return this;
    }

    public Offer addParentOffer(Offer offer) {
        this.parentOffers.add(offer);
        offer.getChildOffers().add(this);
        return this;
    }

    public Offer removeParentOffer(Offer offer) {
        this.parentOffers.remove(offer);
        offer.getChildOffers().remove(this);
        return this;
    }

    public void setParentOffers(Set<Offer> offers) {
        this.parentOffers = offers;
    }

    public Set<Offer> getChildOffers() {
        return childOffers;
    }

    public Offer childOffers(Set<Offer> offers) {
        this.childOffers = offers;
        return this;
    }

    public Offer addChildOffer(Offer offer) {
        this.childOffers.add(offer);
        offer.getParentOffers().add(this);
        return this;
    }

    public Offer removeChildOffer(Offer offer) {
        this.childOffers.remove(offer);
        offer.getParentOffers().remove(this);
        return this;
    }

    public void setChildOffers(Set<Offer> offers) {
        this.childOffers = offers;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Offer)) {
            return false;
        }
        return id != null && id.equals(((Offer) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Offer{" +
            "id=" + getId() +
            ", offerId='" + getOfferId() + "'" +
            ", offerExternalId='" + getOfferExternalId() + "'" +
            ", offerName='" + getOfferName() + "'" +
            ", offerNameChi='" + getOfferNameChi() + "'" +
            ", offerDesc='" + getOfferDesc() + "'" +
            ", offerDescChi='" + getOfferDescChi() + "'" +
            ", offerType='" + getOfferType() + "'" +
            ", offerPrice=" + getOfferPrice() +
            ", tempCustomerSegments='" + getTempCustomerSegments() + "'" +
            ", tempCustomerClasses='" + getTempCustomerClasses() + "'" +
            ", tempSalesChannels='" + getTempSalesChannels() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", tempChildOfferIds='" + getTempChildOfferIds() + "'" +
            ", tempProductIds='" + getTempProductIds() + "'" +
            ", tempAdvancePaymentIds='" + getTempAdvancePaymentIds() + "'" +
            ", tempPromoCodes='" + getTempPromoCodes() + "'" +
            ", tempDiscountCodes='" + getTempDiscountCodes() + "'" +
            ", tempImageIds='" + getTempImageIds() + "'" +
            ", limitedActivationPeriod='" + isLimitedActivationPeriod() + "'" +
            ", allowedActivationStartDate='" + getAllowedActivationStartDate() + "'" +
            ", allowedActivationEndDate='" + getAllowedActivationEndDate() + "'" +
            ", isGroupSharingOffer='" + isIsGroupSharingOffer() + "'" +
            ", isMnpOffer='" + isIsMnpOffer() + "'" +
            ", autoRenewal='" + isAutoRenewal() + "'" +
            ", transferAllowed='" + isTransferAllowed() + "'" +
            ", infoSharingAllowed='" + isInfoSharingAllowed() + "'" +
            ", infoSharingOptions='" + getInfoSharingOptions() + "'" +
            ", offerPeriod=" + getOfferPeriod() +
            ", offerPeriodTerm='" + getOfferPeriodTerm() + "'" +
            ", paymentType='" + getPaymentType() + "'" +
            ", priority=" + getPriority() +
            ", lockCount=" + getLockCount() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", tenantId='" + getTenantId() + "'" +
            "}";
    }
}
