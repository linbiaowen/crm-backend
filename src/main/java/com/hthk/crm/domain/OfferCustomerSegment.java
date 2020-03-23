package com.hthk.crm.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.io.Serializable;
import java.util.Objects;

/**
 * A OfferCustomerSegment.
 */
@Document(collection = "offer_customer_segment")
public class OfferCustomerSegment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("customer_segment")
    private String customerSegment;

    @DBRef
    @Field("offer")
    @JsonIgnoreProperties("customerSegments")
    private Offer offer;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerSegment() {
        return customerSegment;
    }

    public OfferCustomerSegment customerSegment(String customerSegment) {
        this.customerSegment = customerSegment;
        return this;
    }

    public void setCustomerSegment(String customerSegment) {
        this.customerSegment = customerSegment;
    }

    public Offer getOffer() {
        return offer;
    }

    public OfferCustomerSegment offer(Offer offer) {
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
        if (!(o instanceof OfferCustomerSegment)) {
            return false;
        }
        return id != null && id.equals(((OfferCustomerSegment) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "OfferCustomerSegment{" +
            "id=" + getId() +
            ", customerSegment='" + getCustomerSegment() + "'" +
            "}";
    }
}
