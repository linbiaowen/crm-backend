package com.hthk.crm.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.io.Serializable;
import java.util.Objects;

/**
 * A OfferCustomerClass.
 */
@Document(collection = "offer_customer_class")
public class OfferCustomerClass implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("customer_class")
    private String customerClass;

    @DBRef
    @Field("offer")
    @JsonIgnoreProperties("customerClasses")
    private Offer offer;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerClass() {
        return customerClass;
    }

    public OfferCustomerClass customerClass(String customerClass) {
        this.customerClass = customerClass;
        return this;
    }

    public void setCustomerClass(String customerClass) {
        this.customerClass = customerClass;
    }

    public Offer getOffer() {
        return offer;
    }

    public OfferCustomerClass offer(Offer offer) {
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
        if (!(o instanceof OfferCustomerClass)) {
            return false;
        }
        return id != null && id.equals(((OfferCustomerClass) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "OfferCustomerClass{" +
            "id=" + getId() +
            ", customerClass='" + getCustomerClass() + "'" +
            "}";
    }
}
