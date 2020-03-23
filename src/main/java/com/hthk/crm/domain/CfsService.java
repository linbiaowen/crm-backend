package com.hthk.crm.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.time.Instant;

/**
 * A CfsService.
 */
@Document(collection = "cfs_service")
public class CfsService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("service_id")
    private String serviceId;

    @NotNull
    @Field("service_name")
    private String serviceName;

    @Field("temp_voice_spec_ids")
    private String tempVoiceSpecIds;

    @Field("temp_data_spec_ids")
    private String tempDataSpecIds;

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

    public String getServiceId() {
        return serviceId;
    }

    public CfsService serviceId(String serviceId) {
        this.serviceId = serviceId;
        return this;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public CfsService serviceName(String serviceName) {
        this.serviceName = serviceName;
        return this;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getTempVoiceSpecIds() {
        return tempVoiceSpecIds;
    }

    public CfsService tempVoiceSpecIds(String tempVoiceSpecIds) {
        this.tempVoiceSpecIds = tempVoiceSpecIds;
        return this;
    }

    public void setTempVoiceSpecIds(String tempVoiceSpecIds) {
        this.tempVoiceSpecIds = tempVoiceSpecIds;
    }

    public String getTempDataSpecIds() {
        return tempDataSpecIds;
    }

    public CfsService tempDataSpecIds(String tempDataSpecIds) {
        this.tempDataSpecIds = tempDataSpecIds;
        return this;
    }

    public void setTempDataSpecIds(String tempDataSpecIds) {
        this.tempDataSpecIds = tempDataSpecIds;
    }

    public Integer getLockCount() {
        return lockCount;
    }

    public CfsService lockCount(Integer lockCount) {
        this.lockCount = lockCount;
        return this;
    }

    public void setLockCount(Integer lockCount) {
        this.lockCount = lockCount;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public CfsService createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public CfsService createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public CfsService lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public CfsService lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getTenantId() {
        return tenantId;
    }

    public CfsService tenantId(String tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public Product getProduct() {
        return product;
    }

    public CfsService product(Product product) {
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
        if (!(o instanceof CfsService)) {
            return false;
        }
        return id != null && id.equals(((CfsService) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "CfsService{" +
            "id=" + getId() +
            ", serviceId='" + getServiceId() + "'" +
            ", serviceName='" + getServiceName() + "'" +
            ", tempVoiceSpecIds='" + getTempVoiceSpecIds() + "'" +
            ", tempDataSpecIds='" + getTempDataSpecIds() + "'" +
            ", lockCount=" + getLockCount() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", tenantId='" + getTenantId() + "'" +
            "}";
    }
}
