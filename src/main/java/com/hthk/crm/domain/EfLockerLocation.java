package com.hthk.crm.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A EfLockerLocation.
 */
@Document(collection = "ef_locker_location")
public class EfLockerLocation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("ef_locker_code")
    private String efLockerCode;

    @Field("region_eng")
    private String regionEng;

    @Field("area_eng")
    private String areaEng;

    @Field("full_address_eng")
    private String fullAddressEng;

    @Field("region_chi")
    private String regionChi;

    @Field("area_chi")
    private String areaChi;

    @Field("full_address_chi")
    private String fullAddressChi;

    @DBRef
    @Field("subsItemDelivery")
    @com.fasterxml.jackson.annotation.JsonBackReference
    private SubsItemDelivery subsItemDelivery;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEfLockerCode() {
        return efLockerCode;
    }

    public EfLockerLocation efLockerCode(String efLockerCode) {
        this.efLockerCode = efLockerCode;
        return this;
    }

    public void setEfLockerCode(String efLockerCode) {
        this.efLockerCode = efLockerCode;
    }

    public String getRegionEng() {
        return regionEng;
    }

    public EfLockerLocation regionEng(String regionEng) {
        this.regionEng = regionEng;
        return this;
    }

    public void setRegionEng(String regionEng) {
        this.regionEng = regionEng;
    }

    public String getAreaEng() {
        return areaEng;
    }

    public EfLockerLocation areaEng(String areaEng) {
        this.areaEng = areaEng;
        return this;
    }

    public void setAreaEng(String areaEng) {
        this.areaEng = areaEng;
    }

    public String getFullAddressEng() {
        return fullAddressEng;
    }

    public EfLockerLocation fullAddressEng(String fullAddressEng) {
        this.fullAddressEng = fullAddressEng;
        return this;
    }

    public void setFullAddressEng(String fullAddressEng) {
        this.fullAddressEng = fullAddressEng;
    }

    public String getRegionChi() {
        return regionChi;
    }

    public EfLockerLocation regionChi(String regionChi) {
        this.regionChi = regionChi;
        return this;
    }

    public void setRegionChi(String regionChi) {
        this.regionChi = regionChi;
    }

    public String getAreaChi() {
        return areaChi;
    }

    public EfLockerLocation areaChi(String areaChi) {
        this.areaChi = areaChi;
        return this;
    }

    public void setAreaChi(String areaChi) {
        this.areaChi = areaChi;
    }

    public String getFullAddressChi() {
        return fullAddressChi;
    }

    public EfLockerLocation fullAddressChi(String fullAddressChi) {
        this.fullAddressChi = fullAddressChi;
        return this;
    }

    public void setFullAddressChi(String fullAddressChi) {
        this.fullAddressChi = fullAddressChi;
    }

    public SubsItemDelivery getSubsItemDelivery() {
        return subsItemDelivery;
    }

    public EfLockerLocation subsItemDelivery(SubsItemDelivery subsItemDelivery) {
        this.subsItemDelivery = subsItemDelivery;
        return this;
    }

    public void setSubsItemDelivery(SubsItemDelivery subsItemDelivery) {
        this.subsItemDelivery = subsItemDelivery;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EfLockerLocation)) {
            return false;
        }
        return id != null && id.equals(((EfLockerLocation) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "EfLockerLocation{" +
            "id=" + getId() +
            ", efLockerCode='" + getEfLockerCode() + "'" +
            ", regionEng='" + getRegionEng() + "'" +
            ", areaEng='" + getAreaEng() + "'" +
            ", fullAddressEng='" + getFullAddressEng() + "'" +
            ", regionChi='" + getRegionChi() + "'" +
            ", areaChi='" + getAreaChi() + "'" +
            ", fullAddressChi='" + getFullAddressChi() + "'" +
            "}";
    }
}
