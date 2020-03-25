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

import com.hthk.crm.domain.enumeration.MmsType;

/**
 * A ProductMms.
 */
@Document(collection = "product_mms")
public class ProductMms implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("mms_id")
    private String mmsId;

    @Field("product_id")
    private String productId;

    @NotNull
    @Field("unit")
    private String unit;

    @NotNull
    @Field("volume")
    private Integer volume;

    @Field("mms_type")
    private MmsType mmsType;

    @Field("roaming_allowed")
    private Boolean roamingAllowed;

    @Field("min_transfer_quota")
    private BigDecimal minTransferQuota;

    @Field("max_transfer_quota")
    private BigDecimal maxTransferQuota;

    @Field("min_retention_quota")
    private BigDecimal minRetentionQuota;

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

    @DBRef
    @Field("product")
    @com.fasterxml.jackson.annotation.JsonBackReference
    private Product product;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMmsId() {
        return mmsId;
    }

    public ProductMms mmsId(String mmsId) {
        this.mmsId = mmsId;
        return this;
    }

    public void setMmsId(String mmsId) {
        this.mmsId = mmsId;
    }

    public String getProductId() {
        return productId;
    }

    public ProductMms productId(String productId) {
        this.productId = productId;
        return this;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getUnit() {
        return unit;
    }

    public ProductMms unit(String unit) {
        this.unit = unit;
        return this;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getVolume() {
        return volume;
    }

    public ProductMms volume(Integer volume) {
        this.volume = volume;
        return this;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public MmsType getMmsType() {
        return mmsType;
    }

    public ProductMms mmsType(MmsType mmsType) {
        this.mmsType = mmsType;
        return this;
    }

    public void setMmsType(MmsType mmsType) {
        this.mmsType = mmsType;
    }

    public Boolean isRoamingAllowed() {
        return roamingAllowed;
    }

    public ProductMms roamingAllowed(Boolean roamingAllowed) {
        this.roamingAllowed = roamingAllowed;
        return this;
    }

    public void setRoamingAllowed(Boolean roamingAllowed) {
        this.roamingAllowed = roamingAllowed;
    }

    public BigDecimal getMinTransferQuota() {
        return minTransferQuota;
    }

    public ProductMms minTransferQuota(BigDecimal minTransferQuota) {
        this.minTransferQuota = minTransferQuota;
        return this;
    }

    public void setMinTransferQuota(BigDecimal minTransferQuota) {
        this.minTransferQuota = minTransferQuota;
    }

    public BigDecimal getMaxTransferQuota() {
        return maxTransferQuota;
    }

    public ProductMms maxTransferQuota(BigDecimal maxTransferQuota) {
        this.maxTransferQuota = maxTransferQuota;
        return this;
    }

    public void setMaxTransferQuota(BigDecimal maxTransferQuota) {
        this.maxTransferQuota = maxTransferQuota;
    }

    public BigDecimal getMinRetentionQuota() {
        return minRetentionQuota;
    }

    public ProductMms minRetentionQuota(BigDecimal minRetentionQuota) {
        this.minRetentionQuota = minRetentionQuota;
        return this;
    }

    public void setMinRetentionQuota(BigDecimal minRetentionQuota) {
        this.minRetentionQuota = minRetentionQuota;
    }

    public Integer getLockCount() {
        return lockCount;
    }

    public ProductMms lockCount(Integer lockCount) {
        this.lockCount = lockCount;
        return this;
    }

    public void setLockCount(Integer lockCount) {
        this.lockCount = lockCount;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public ProductMms createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public ProductMms createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public ProductMms lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public ProductMms lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getTenantId() {
        return tenantId;
    }

    public ProductMms tenantId(String tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public Product getProduct() {
        return product;
    }

    public ProductMms product(Product product) {
        this.product = product;
        return this;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductMms)) {
            return false;
        }
        return id != null && id.equals(((ProductMms) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ProductMms{" +
            "id=" + getId() +
            ", mmsId='" + getMmsId() + "'" +
            ", productId='" + getProductId() + "'" +
            ", unit='" + getUnit() + "'" +
            ", volume=" + getVolume() +
            ", mmsType='" + getMmsType() + "'" +
            ", roamingAllowed='" + isRoamingAllowed() + "'" +
            ", minTransferQuota=" + getMinTransferQuota() +
            ", maxTransferQuota=" + getMaxTransferQuota() +
            ", minRetentionQuota=" + getMinRetentionQuota() +
            ", lockCount=" + getLockCount() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", tenantId='" + getTenantId() + "'" +
            "}";
    }
}
