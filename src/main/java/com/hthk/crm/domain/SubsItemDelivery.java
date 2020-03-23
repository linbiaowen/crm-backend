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

import com.hthk.crm.domain.enumeration.DeliverOptions;

/**
 * A SubsItemDelivery.
 */
@Document(collection = "subs_item_delivery")
public class SubsItemDelivery implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("delivery_id")
    private Long deliveryId;

    @NotNull
    @Field("delivery_status")
    private String deliveryStatus;

    @NotNull
    @Field("delivery_option")
    private DeliverOptions deliveryOption;

    @Field("temp_ef_locker_code")
    private String tempEfLockerCode;

    @Field("temp_address_id")
    private String tempAddressId;

    @Field("delivery_ref_code")
    private String deliveryRefCode;

    @Field("file_generation_date")
    private Instant fileGenerationDate;

    @Field("file_received_date")
    private Instant fileReceivedDate;

    @Field("delivery_date")
    private Instant deliveryDate;

    @Field("remarks")
    private String remarks;

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
    @Field("efLockerLocation")
    private EfLockerLocation efLockerLocation;

    //@DBRef
    @Field("custAddress")
    private CustAddress custAddress;

    @DBRef
    @Field("subscriptionProduct")
    @JsonIgnoreProperties("subsItemDeliverys")
    private SubscriptionProduct subscriptionProduct;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getDeliveryId() {
        return deliveryId;
    }

    public SubsItemDelivery deliveryId(Long deliveryId) {
        this.deliveryId = deliveryId;
        return this;
    }

    public void setDeliveryId(Long deliveryId) {
        this.deliveryId = deliveryId;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public SubsItemDelivery deliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
        return this;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public DeliverOptions getDeliveryOption() {
        return deliveryOption;
    }

    public SubsItemDelivery deliveryOption(DeliverOptions deliveryOption) {
        this.deliveryOption = deliveryOption;
        return this;
    }

    public void setDeliveryOption(DeliverOptions deliveryOption) {
        this.deliveryOption = deliveryOption;
    }

    public String getTempEfLockerCode() {
        return tempEfLockerCode;
    }

    public SubsItemDelivery tempEfLockerCode(String tempEfLockerCode) {
        this.tempEfLockerCode = tempEfLockerCode;
        return this;
    }

    public void setTempEfLockerCode(String tempEfLockerCode) {
        this.tempEfLockerCode = tempEfLockerCode;
    }

    public String getTempAddressId() {
        return tempAddressId;
    }

    public SubsItemDelivery tempAddressId(String tempAddressId) {
        this.tempAddressId = tempAddressId;
        return this;
    }

    public void setTempAddressId(String tempAddressId) {
        this.tempAddressId = tempAddressId;
    }

    public String getDeliveryRefCode() {
        return deliveryRefCode;
    }

    public SubsItemDelivery deliveryRefCode(String deliveryRefCode) {
        this.deliveryRefCode = deliveryRefCode;
        return this;
    }

    public void setDeliveryRefCode(String deliveryRefCode) {
        this.deliveryRefCode = deliveryRefCode;
    }

    public Instant getFileGenerationDate() {
        return fileGenerationDate;
    }

    public SubsItemDelivery fileGenerationDate(Instant fileGenerationDate) {
        this.fileGenerationDate = fileGenerationDate;
        return this;
    }

    public void setFileGenerationDate(Instant fileGenerationDate) {
        this.fileGenerationDate = fileGenerationDate;
    }

    public Instant getFileReceivedDate() {
        return fileReceivedDate;
    }

    public SubsItemDelivery fileReceivedDate(Instant fileReceivedDate) {
        this.fileReceivedDate = fileReceivedDate;
        return this;
    }

    public void setFileReceivedDate(Instant fileReceivedDate) {
        this.fileReceivedDate = fileReceivedDate;
    }

    public Instant getDeliveryDate() {
        return deliveryDate;
    }

    public SubsItemDelivery deliveryDate(Instant deliveryDate) {
        this.deliveryDate = deliveryDate;
        return this;
    }

    public void setDeliveryDate(Instant deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public SubsItemDelivery remarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getLockCount() {
        return lockCount;
    }

    public SubsItemDelivery lockCount(Integer lockCount) {
        this.lockCount = lockCount;
        return this;
    }

    public void setLockCount(Integer lockCount) {
        this.lockCount = lockCount;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public SubsItemDelivery createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public SubsItemDelivery createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public SubsItemDelivery lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public SubsItemDelivery lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getTenantId() {
        return tenantId;
    }

    public SubsItemDelivery tenantId(String tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public EfLockerLocation getEfLockerLocation() {
        return efLockerLocation;
    }

    public SubsItemDelivery efLockerLocation(EfLockerLocation efLockerLocation) {
        this.efLockerLocation = efLockerLocation;
        return this;
    }

    public void setEfLockerLocation(EfLockerLocation efLockerLocation) {
        this.efLockerLocation = efLockerLocation;
    }

    public CustAddress getCustAddress() {
        return custAddress;
    }

    public SubsItemDelivery custAddress(CustAddress custAddress) {
        this.custAddress = custAddress;
        return this;
    }

    public void setCustAddress(CustAddress custAddress) {
        this.custAddress = custAddress;
    }

    public SubscriptionProduct getSubscriptionProduct() {
        return subscriptionProduct;
    }

    public SubsItemDelivery subscriptionProduct(SubscriptionProduct subscriptionProduct) {
        this.subscriptionProduct = subscriptionProduct;
        return this;
    }

    public void setSubscriptionProduct(SubscriptionProduct subscriptionProduct) {
        this.subscriptionProduct = subscriptionProduct;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SubsItemDelivery)) {
            return false;
        }
        return id != null && id.equals(((SubsItemDelivery) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "SubsItemDelivery{" +
            "id=" + getId() +
            ", deliveryId=" + getDeliveryId() +
            ", deliveryStatus='" + getDeliveryStatus() + "'" +
            ", deliveryOption='" + getDeliveryOption() + "'" +
            ", tempEfLockerCode='" + getTempEfLockerCode() + "'" +
            ", tempAddressId='" + getTempAddressId() + "'" +
            ", deliveryRefCode='" + getDeliveryRefCode() + "'" +
            ", fileGenerationDate='" + getFileGenerationDate() + "'" +
            ", fileReceivedDate='" + getFileReceivedDate() + "'" +
            ", deliveryDate='" + getDeliveryDate() + "'" +
            ", remarks='" + getRemarks() + "'" +
            ", lockCount=" + getLockCount() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", tenantId='" + getTenantId() + "'" +
            "}";
    }
}
