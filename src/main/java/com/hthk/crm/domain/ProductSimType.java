package com.hthk.crm.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.time.Instant;

import com.hthk.crm.domain.enumeration.SimType;

/**
 * A ProductSimType.
 */
@Document(collection = "product_sim_type")
public class ProductSimType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("sim_type")
    private SimType simType;

    @Field("imsi_range_from")
    private String imsiRangeFrom;

    @Field("imsi_range_to")
    private String imsiRangeTo;

    @Field("sim_repository_ref")
    private String simRepositoryRef;

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

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SimType getSimType() {
        return simType;
    }

    public ProductSimType simType(SimType simType) {
        this.simType = simType;
        return this;
    }

    public void setSimType(SimType simType) {
        this.simType = simType;
    }

    public String getImsiRangeFrom() {
        return imsiRangeFrom;
    }

    public ProductSimType imsiRangeFrom(String imsiRangeFrom) {
        this.imsiRangeFrom = imsiRangeFrom;
        return this;
    }

    public void setImsiRangeFrom(String imsiRangeFrom) {
        this.imsiRangeFrom = imsiRangeFrom;
    }

    public String getImsiRangeTo() {
        return imsiRangeTo;
    }

    public ProductSimType imsiRangeTo(String imsiRangeTo) {
        this.imsiRangeTo = imsiRangeTo;
        return this;
    }

    public void setImsiRangeTo(String imsiRangeTo) {
        this.imsiRangeTo = imsiRangeTo;
    }

    public String getSimRepositoryRef() {
        return simRepositoryRef;
    }

    public ProductSimType simRepositoryRef(String simRepositoryRef) {
        this.simRepositoryRef = simRepositoryRef;
        return this;
    }

    public void setSimRepositoryRef(String simRepositoryRef) {
        this.simRepositoryRef = simRepositoryRef;
    }

    public Integer getLockCount() {
        return lockCount;
    }

    public ProductSimType lockCount(Integer lockCount) {
        this.lockCount = lockCount;
        return this;
    }

    public void setLockCount(Integer lockCount) {
        this.lockCount = lockCount;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public ProductSimType createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public ProductSimType createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public ProductSimType lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public ProductSimType lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getTenantId() {
        return tenantId;
    }

    public ProductSimType tenantId(String tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductSimType)) {
            return false;
        }
        return id != null && id.equals(((ProductSimType) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ProductSimType{" +
            "id=" + getId() +
            ", simType='" + getSimType() + "'" +
            ", imsiRangeFrom='" + getImsiRangeFrom() + "'" +
            ", imsiRangeTo='" + getImsiRangeTo() + "'" +
            ", simRepositoryRef='" + getSimRepositoryRef() + "'" +
            ", lockCount=" + getLockCount() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", tenantId='" + getTenantId() + "'" +
            "}";
    }
}
