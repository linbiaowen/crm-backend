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

import com.hthk.crm.domain.enumeration.SpecicalPackType;

import com.hthk.crm.domain.enumeration.DataServiceType;

/**
 * A ProductData.
 */
@Document(collection = "product_data")
public class ProductData implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("data_id")
    private String dataId;

    @Field("product_id")
    private String productId;

    @NotNull
    @Field("unit")
    private String unit;

    @NotNull
    @Field("volume")
    private Integer volume;

    @Field("data_slab")
    private String dataSlab;

    @Field("data_speed_category")
    private String dataSpeedCategory;

    @Field("specical_pack_type")
    private SpecicalPackType specicalPackType;

    @Field("data_service_type")
    private DataServiceType dataServiceType;

    @Field("roaming_regions")
    private String roamingRegions;

    @Field("roaming_countries")
    private String roamingCountries;

    @Field("roaming_day_pass_type")
    private String roamingDayPassType;

    @Field("roaming_pack_valid_period_type")
    private String roamingPackValidPeriodType;

    @Field("roaming_pack_period")
    private Integer roamingPackPeriod;

    @Field("roaming_pack_term")
    private String roamingPackTerm;

    @Field("min_transfer_quota")
    private BigDecimal minTransferQuota;

    @Field("max_transfer_quota")
    private BigDecimal maxTransferQuota;

    @Field("min_retention_quota")
    private BigDecimal minRetentionQuota;

    @Field("min_tp_transfer_quota")
    private BigDecimal minTpTransferQuota;

    @Field("max_tp_transfer_quota")
    private BigDecimal maxTpTransferQuota;

    @Field("min_tp_retention_quota")
    private BigDecimal minTpRetentionQuota;

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

    public String getDataId() {
        return dataId;
    }

    public ProductData dataId(String dataId) {
        this.dataId = dataId;
        return this;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public String getProductId() {
        return productId;
    }

    public ProductData productId(String productId) {
        this.productId = productId;
        return this;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getUnit() {
        return unit;
    }

    public ProductData unit(String unit) {
        this.unit = unit;
        return this;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getVolume() {
        return volume;
    }

    public ProductData volume(Integer volume) {
        this.volume = volume;
        return this;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public String getDataSlab() {
        return dataSlab;
    }

    public ProductData dataSlab(String dataSlab) {
        this.dataSlab = dataSlab;
        return this;
    }

    public void setDataSlab(String dataSlab) {
        this.dataSlab = dataSlab;
    }

    public String getDataSpeedCategory() {
        return dataSpeedCategory;
    }

    public ProductData dataSpeedCategory(String dataSpeedCategory) {
        this.dataSpeedCategory = dataSpeedCategory;
        return this;
    }

    public void setDataSpeedCategory(String dataSpeedCategory) {
        this.dataSpeedCategory = dataSpeedCategory;
    }

    public SpecicalPackType getSpecicalPackType() {
        return specicalPackType;
    }

    public ProductData specicalPackType(SpecicalPackType specicalPackType) {
        this.specicalPackType = specicalPackType;
        return this;
    }

    public void setSpecicalPackType(SpecicalPackType specicalPackType) {
        this.specicalPackType = specicalPackType;
    }

    public DataServiceType getDataServiceType() {
        return dataServiceType;
    }

    public ProductData dataServiceType(DataServiceType dataServiceType) {
        this.dataServiceType = dataServiceType;
        return this;
    }

    public void setDataServiceType(DataServiceType dataServiceType) {
        this.dataServiceType = dataServiceType;
    }

    public String getRoamingRegions() {
        return roamingRegions;
    }

    public ProductData roamingRegions(String roamingRegions) {
        this.roamingRegions = roamingRegions;
        return this;
    }

    public void setRoamingRegions(String roamingRegions) {
        this.roamingRegions = roamingRegions;
    }

    public String getRoamingCountries() {
        return roamingCountries;
    }

    public ProductData roamingCountries(String roamingCountries) {
        this.roamingCountries = roamingCountries;
        return this;
    }

    public void setRoamingCountries(String roamingCountries) {
        this.roamingCountries = roamingCountries;
    }

    public String getRoamingDayPassType() {
        return roamingDayPassType;
    }

    public ProductData roamingDayPassType(String roamingDayPassType) {
        this.roamingDayPassType = roamingDayPassType;
        return this;
    }

    public void setRoamingDayPassType(String roamingDayPassType) {
        this.roamingDayPassType = roamingDayPassType;
    }

    public String getRoamingPackValidPeriodType() {
        return roamingPackValidPeriodType;
    }

    public ProductData roamingPackValidPeriodType(String roamingPackValidPeriodType) {
        this.roamingPackValidPeriodType = roamingPackValidPeriodType;
        return this;
    }

    public void setRoamingPackValidPeriodType(String roamingPackValidPeriodType) {
        this.roamingPackValidPeriodType = roamingPackValidPeriodType;
    }

    public Integer getRoamingPackPeriod() {
        return roamingPackPeriod;
    }

    public ProductData roamingPackPeriod(Integer roamingPackPeriod) {
        this.roamingPackPeriod = roamingPackPeriod;
        return this;
    }

    public void setRoamingPackPeriod(Integer roamingPackPeriod) {
        this.roamingPackPeriod = roamingPackPeriod;
    }

    public String getRoamingPackTerm() {
        return roamingPackTerm;
    }

    public ProductData roamingPackTerm(String roamingPackTerm) {
        this.roamingPackTerm = roamingPackTerm;
        return this;
    }

    public void setRoamingPackTerm(String roamingPackTerm) {
        this.roamingPackTerm = roamingPackTerm;
    }

    public BigDecimal getMinTransferQuota() {
        return minTransferQuota;
    }

    public ProductData minTransferQuota(BigDecimal minTransferQuota) {
        this.minTransferQuota = minTransferQuota;
        return this;
    }

    public void setMinTransferQuota(BigDecimal minTransferQuota) {
        this.minTransferQuota = minTransferQuota;
    }

    public BigDecimal getMaxTransferQuota() {
        return maxTransferQuota;
    }

    public ProductData maxTransferQuota(BigDecimal maxTransferQuota) {
        this.maxTransferQuota = maxTransferQuota;
        return this;
    }

    public void setMaxTransferQuota(BigDecimal maxTransferQuota) {
        this.maxTransferQuota = maxTransferQuota;
    }

    public BigDecimal getMinRetentionQuota() {
        return minRetentionQuota;
    }

    public ProductData minRetentionQuota(BigDecimal minRetentionQuota) {
        this.minRetentionQuota = minRetentionQuota;
        return this;
    }

    public void setMinRetentionQuota(BigDecimal minRetentionQuota) {
        this.minRetentionQuota = minRetentionQuota;
    }

    public BigDecimal getMinTpTransferQuota() {
        return minTpTransferQuota;
    }

    public ProductData minTpTransferQuota(BigDecimal minTpTransferQuota) {
        this.minTpTransferQuota = minTpTransferQuota;
        return this;
    }

    public void setMinTpTransferQuota(BigDecimal minTpTransferQuota) {
        this.minTpTransferQuota = minTpTransferQuota;
    }

    public BigDecimal getMaxTpTransferQuota() {
        return maxTpTransferQuota;
    }

    public ProductData maxTpTransferQuota(BigDecimal maxTpTransferQuota) {
        this.maxTpTransferQuota = maxTpTransferQuota;
        return this;
    }

    public void setMaxTpTransferQuota(BigDecimal maxTpTransferQuota) {
        this.maxTpTransferQuota = maxTpTransferQuota;
    }

    public BigDecimal getMinTpRetentionQuota() {
        return minTpRetentionQuota;
    }

    public ProductData minTpRetentionQuota(BigDecimal minTpRetentionQuota) {
        this.minTpRetentionQuota = minTpRetentionQuota;
        return this;
    }

    public void setMinTpRetentionQuota(BigDecimal minTpRetentionQuota) {
        this.minTpRetentionQuota = minTpRetentionQuota;
    }

    public Integer getLockCount() {
        return lockCount;
    }

    public ProductData lockCount(Integer lockCount) {
        this.lockCount = lockCount;
        return this;
    }

    public void setLockCount(Integer lockCount) {
        this.lockCount = lockCount;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public ProductData createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public ProductData createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public ProductData lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public ProductData lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getTenantId() {
        return tenantId;
    }

    public ProductData tenantId(String tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public Product getProduct() {
        return product;
    }

    public ProductData product(Product product) {
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
        if (!(o instanceof ProductData)) {
            return false;
        }
        return id != null && id.equals(((ProductData) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ProductData{" +
            "id=" + getId() +
            ", dataId='" + getDataId() + "'" +
            ", productId='" + getProductId() + "'" +
            ", unit='" + getUnit() + "'" +
            ", volume=" + getVolume() +
            ", dataSlab='" + getDataSlab() + "'" +
            ", dataSpeedCategory='" + getDataSpeedCategory() + "'" +
            ", specicalPackType='" + getSpecicalPackType() + "'" +
            ", dataServiceType='" + getDataServiceType() + "'" +
            ", roamingRegions='" + getRoamingRegions() + "'" +
            ", roamingCountries='" + getRoamingCountries() + "'" +
            ", roamingDayPassType='" + getRoamingDayPassType() + "'" +
            ", roamingPackValidPeriodType='" + getRoamingPackValidPeriodType() + "'" +
            ", roamingPackPeriod=" + getRoamingPackPeriod() +
            ", roamingPackTerm='" + getRoamingPackTerm() + "'" +
            ", minTransferQuota=" + getMinTransferQuota() +
            ", maxTransferQuota=" + getMaxTransferQuota() +
            ", minRetentionQuota=" + getMinRetentionQuota() +
            ", minTpTransferQuota=" + getMinTpTransferQuota() +
            ", maxTpTransferQuota=" + getMaxTpTransferQuota() +
            ", minTpRetentionQuota=" + getMinTpRetentionQuota() +
            ", lockCount=" + getLockCount() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", tenantId='" + getTenantId() + "'" +
            "}";
    }
}
