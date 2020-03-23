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

/**
 * A SubscriptionProduct.
 */
@Document(collection = "subscription_product")
public class SubscriptionProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("subscription_product_seq_id")
    private Long subscriptionProductSeqId;

    @NotNull
    @Field("order_id")
    private String orderId;

    @NotNull
    @Field("subscription_id")
    private String subscriptionId;

    @NotNull
    @Field("product_id")
    private String productId;

    @Field("product_name")
    private String productName;

    @Field("device_type")
    private String deviceType;

    @Field("device_model")
    private String deviceModel;

    @Field("device_serial_nbr")
    private String deviceSerialNbr;

    @Field("imei")
    private String imei;

    @Field("product_theme")
    private String productTheme;

    @Field("activation_date")
    private Instant activationDate;

    @Field("end_date")
    private Instant endDate;

    @Field("second_msisdn")
    private String secondMsisdn;

    @Field("second_imsi")
    private String secondImsi;

    @Field("quantity")
    private Integer quantity;

    @Field("termination_reason_code")
    private String terminationReasonCode;

    @NotNull
    @Field("offer_id")
    private String offerId;

    @Field("offer_name")
    private String offerName;

    @Field("offer_type")
    private String offerType;

    @Field("matrixx_catalog_id")
    private String matrixxCatalogId;

    @Field("matrixx_resource_id")
    private String matrixxResourceId;

    @Field("matrixx_object_id")
    private String matrixxObjectId;

    @Field("sales_channel")
    private String salesChannel;

    @Field("contract_id")
    private String contractId;

    @Field("auto_renewal")
    private Boolean autoRenewal;

    @Field("auto_pay")
    private Boolean autoPay;

    @Field("remarks")
    private String remarks;

    @Field("vendor_provision_ind")
    private Boolean vendorProvisionInd;

    @Field("temp_provision_seq_ids")
    private String tempProvisionSeqIds;

    @Field("delivery_ids")
    private String deliveryIds;

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
    @Field("subscriptionProvisions")
    private Set<SubscriptionProvision> subscriptionProvisions = new HashSet<>();

    //@DBRef
    @Field("subsItemDeliverys")
    private Set<SubsItemDelivery> subsItemDeliverys = new HashSet<>();

    @DBRef
    @Field("subsOrderDetail")
    @JsonIgnoreProperties("subscriptionProducts")
    private SubsOrderDetails subsOrderDetail;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getSubscriptionProductSeqId() {
        return subscriptionProductSeqId;
    }

    public SubscriptionProduct subscriptionProductSeqId(Long subscriptionProductSeqId) {
        this.subscriptionProductSeqId = subscriptionProductSeqId;
        return this;
    }

    public void setSubscriptionProductSeqId(Long subscriptionProductSeqId) {
        this.subscriptionProductSeqId = subscriptionProductSeqId;
    }

    public String getOrderId() {
        return orderId;
    }

    public SubscriptionProduct orderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public SubscriptionProduct subscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
        return this;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public String getProductId() {
        return productId;
    }

    public SubscriptionProduct productId(String productId) {
        this.productId = productId;
        return this;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public SubscriptionProduct productName(String productName) {
        this.productName = productName;
        return this;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public SubscriptionProduct deviceType(String deviceType) {
        this.deviceType = deviceType;
        return this;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public SubscriptionProduct deviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
        return this;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public String getDeviceSerialNbr() {
        return deviceSerialNbr;
    }

    public SubscriptionProduct deviceSerialNbr(String deviceSerialNbr) {
        this.deviceSerialNbr = deviceSerialNbr;
        return this;
    }

    public void setDeviceSerialNbr(String deviceSerialNbr) {
        this.deviceSerialNbr = deviceSerialNbr;
    }

    public String getImei() {
        return imei;
    }

    public SubscriptionProduct imei(String imei) {
        this.imei = imei;
        return this;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getProductTheme() {
        return productTheme;
    }

    public SubscriptionProduct productTheme(String productTheme) {
        this.productTheme = productTheme;
        return this;
    }

    public void setProductTheme(String productTheme) {
        this.productTheme = productTheme;
    }

    public Instant getActivationDate() {
        return activationDate;
    }

    public SubscriptionProduct activationDate(Instant activationDate) {
        this.activationDate = activationDate;
        return this;
    }

    public void setActivationDate(Instant activationDate) {
        this.activationDate = activationDate;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public SubscriptionProduct endDate(Instant endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    public String getSecondMsisdn() {
        return secondMsisdn;
    }

    public SubscriptionProduct secondMsisdn(String secondMsisdn) {
        this.secondMsisdn = secondMsisdn;
        return this;
    }

    public void setSecondMsisdn(String secondMsisdn) {
        this.secondMsisdn = secondMsisdn;
    }

    public String getSecondImsi() {
        return secondImsi;
    }

    public SubscriptionProduct secondImsi(String secondImsi) {
        this.secondImsi = secondImsi;
        return this;
    }

    public void setSecondImsi(String secondImsi) {
        this.secondImsi = secondImsi;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public SubscriptionProduct quantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getTerminationReasonCode() {
        return terminationReasonCode;
    }

    public SubscriptionProduct terminationReasonCode(String terminationReasonCode) {
        this.terminationReasonCode = terminationReasonCode;
        return this;
    }

    public void setTerminationReasonCode(String terminationReasonCode) {
        this.terminationReasonCode = terminationReasonCode;
    }

    public String getOfferId() {
        return offerId;
    }

    public SubscriptionProduct offerId(String offerId) {
        this.offerId = offerId;
        return this;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public String getOfferName() {
        return offerName;
    }

    public SubscriptionProduct offerName(String offerName) {
        this.offerName = offerName;
        return this;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }

    public String getOfferType() {
        return offerType;
    }

    public SubscriptionProduct offerType(String offerType) {
        this.offerType = offerType;
        return this;
    }

    public void setOfferType(String offerType) {
        this.offerType = offerType;
    }

    public String getMatrixxCatalogId() {
        return matrixxCatalogId;
    }

    public SubscriptionProduct matrixxCatalogId(String matrixxCatalogId) {
        this.matrixxCatalogId = matrixxCatalogId;
        return this;
    }

    public void setMatrixxCatalogId(String matrixxCatalogId) {
        this.matrixxCatalogId = matrixxCatalogId;
    }

    public String getMatrixxResourceId() {
        return matrixxResourceId;
    }

    public SubscriptionProduct matrixxResourceId(String matrixxResourceId) {
        this.matrixxResourceId = matrixxResourceId;
        return this;
    }

    public void setMatrixxResourceId(String matrixxResourceId) {
        this.matrixxResourceId = matrixxResourceId;
    }

    public String getMatrixxObjectId() {
        return matrixxObjectId;
    }

    public SubscriptionProduct matrixxObjectId(String matrixxObjectId) {
        this.matrixxObjectId = matrixxObjectId;
        return this;
    }

    public void setMatrixxObjectId(String matrixxObjectId) {
        this.matrixxObjectId = matrixxObjectId;
    }

    public String getSalesChannel() {
        return salesChannel;
    }

    public SubscriptionProduct salesChannel(String salesChannel) {
        this.salesChannel = salesChannel;
        return this;
    }

    public void setSalesChannel(String salesChannel) {
        this.salesChannel = salesChannel;
    }

    public String getContractId() {
        return contractId;
    }

    public SubscriptionProduct contractId(String contractId) {
        this.contractId = contractId;
        return this;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public Boolean isAutoRenewal() {
        return autoRenewal;
    }

    public SubscriptionProduct autoRenewal(Boolean autoRenewal) {
        this.autoRenewal = autoRenewal;
        return this;
    }

    public void setAutoRenewal(Boolean autoRenewal) {
        this.autoRenewal = autoRenewal;
    }

    public Boolean isAutoPay() {
        return autoPay;
    }

    public SubscriptionProduct autoPay(Boolean autoPay) {
        this.autoPay = autoPay;
        return this;
    }

    public void setAutoPay(Boolean autoPay) {
        this.autoPay = autoPay;
    }

    public String getRemarks() {
        return remarks;
    }

    public SubscriptionProduct remarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Boolean isVendorProvisionInd() {
        return vendorProvisionInd;
    }

    public SubscriptionProduct vendorProvisionInd(Boolean vendorProvisionInd) {
        this.vendorProvisionInd = vendorProvisionInd;
        return this;
    }

    public void setVendorProvisionInd(Boolean vendorProvisionInd) {
        this.vendorProvisionInd = vendorProvisionInd;
    }

    public String getTempProvisionSeqIds() {
        return tempProvisionSeqIds;
    }

    public SubscriptionProduct tempProvisionSeqIds(String tempProvisionSeqIds) {
        this.tempProvisionSeqIds = tempProvisionSeqIds;
        return this;
    }

    public void setTempProvisionSeqIds(String tempProvisionSeqIds) {
        this.tempProvisionSeqIds = tempProvisionSeqIds;
    }

    public String getDeliveryIds() {
        return deliveryIds;
    }

    public SubscriptionProduct deliveryIds(String deliveryIds) {
        this.deliveryIds = deliveryIds;
        return this;
    }

    public void setDeliveryIds(String deliveryIds) {
        this.deliveryIds = deliveryIds;
    }

    public Integer getLockCount() {
        return lockCount;
    }

    public SubscriptionProduct lockCount(Integer lockCount) {
        this.lockCount = lockCount;
        return this;
    }

    public void setLockCount(Integer lockCount) {
        this.lockCount = lockCount;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public SubscriptionProduct createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public SubscriptionProduct createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public SubscriptionProduct lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public SubscriptionProduct lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getTenantId() {
        return tenantId;
    }

    public SubscriptionProduct tenantId(String tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public Set<SubscriptionProvision> getSubscriptionProvisions() {
        return subscriptionProvisions;
    }

    public SubscriptionProduct subscriptionProvisions(Set<SubscriptionProvision> subscriptionProvisions) {
        this.subscriptionProvisions = subscriptionProvisions;
        return this;
    }

    public SubscriptionProduct addSubscriptionProvisions(SubscriptionProvision subscriptionProvision) {
        this.subscriptionProvisions.add(subscriptionProvision);
        subscriptionProvision.setSubscriptionProduct(this);
        return this;
    }

    public SubscriptionProduct removeSubscriptionProvisions(SubscriptionProvision subscriptionProvision) {
        this.subscriptionProvisions.remove(subscriptionProvision);
        subscriptionProvision.setSubscriptionProduct(null);
        return this;
    }

    public void setSubscriptionProvisions(Set<SubscriptionProvision> subscriptionProvisions) {
        this.subscriptionProvisions = subscriptionProvisions;
    }

    public Set<SubsItemDelivery> getSubsItemDeliverys() {
        return subsItemDeliverys;
    }

    public SubscriptionProduct subsItemDeliverys(Set<SubsItemDelivery> subsItemDeliveries) {
        this.subsItemDeliverys = subsItemDeliveries;
        return this;
    }

    public SubscriptionProduct addSubsItemDeliverys(SubsItemDelivery subsItemDelivery) {
        this.subsItemDeliverys.add(subsItemDelivery);
        subsItemDelivery.setSubscriptionProduct(this);
        return this;
    }

    public SubscriptionProduct removeSubsItemDeliverys(SubsItemDelivery subsItemDelivery) {
        this.subsItemDeliverys.remove(subsItemDelivery);
        subsItemDelivery.setSubscriptionProduct(null);
        return this;
    }

    public void setSubsItemDeliverys(Set<SubsItemDelivery> subsItemDeliveries) {
        this.subsItemDeliverys = subsItemDeliveries;
    }

    public SubsOrderDetails getSubsOrderDetail() {
        return subsOrderDetail;
    }

    public SubscriptionProduct subsOrderDetail(SubsOrderDetails subsOrderDetails) {
        this.subsOrderDetail = subsOrderDetails;
        return this;
    }

    public void setSubsOrderDetail(SubsOrderDetails subsOrderDetails) {
        this.subsOrderDetail = subsOrderDetails;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SubscriptionProduct)) {
            return false;
        }
        return id != null && id.equals(((SubscriptionProduct) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "SubscriptionProduct{" +
            "id=" + getId() +
            ", subscriptionProductSeqId=" + getSubscriptionProductSeqId() +
            ", orderId='" + getOrderId() + "'" +
            ", subscriptionId='" + getSubscriptionId() + "'" +
            ", productId='" + getProductId() + "'" +
            ", productName='" + getProductName() + "'" +
            ", deviceType='" + getDeviceType() + "'" +
            ", deviceModel='" + getDeviceModel() + "'" +
            ", deviceSerialNbr='" + getDeviceSerialNbr() + "'" +
            ", imei='" + getImei() + "'" +
            ", productTheme='" + getProductTheme() + "'" +
            ", activationDate='" + getActivationDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", secondMsisdn='" + getSecondMsisdn() + "'" +
            ", secondImsi='" + getSecondImsi() + "'" +
            ", quantity=" + getQuantity() +
            ", terminationReasonCode='" + getTerminationReasonCode() + "'" +
            ", offerId='" + getOfferId() + "'" +
            ", offerName='" + getOfferName() + "'" +
            ", offerType='" + getOfferType() + "'" +
            ", matrixxCatalogId='" + getMatrixxCatalogId() + "'" +
            ", matrixxResourceId='" + getMatrixxResourceId() + "'" +
            ", matrixxObjectId='" + getMatrixxObjectId() + "'" +
            ", salesChannel='" + getSalesChannel() + "'" +
            ", contractId='" + getContractId() + "'" +
            ", autoRenewal='" + isAutoRenewal() + "'" +
            ", autoPay='" + isAutoPay() + "'" +
            ", remarks='" + getRemarks() + "'" +
            ", vendorProvisionInd='" + isVendorProvisionInd() + "'" +
            ", tempProvisionSeqIds='" + getTempProvisionSeqIds() + "'" +
            ", deliveryIds='" + getDeliveryIds() + "'" +
            ", lockCount=" + getLockCount() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", tenantId='" + getTenantId() + "'" +
            "}";
    }
}
