package com.hthk.crm.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.io.Serializable;
import java.util.Objects;

/**
 * A OfferSalesChannel.
 */
@Document(collection = "offer_sales_channel")
public class OfferSalesChannel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("sales_channel")
    private String salesChannel;

    @DBRef
    @Field("offer")
    @JsonIgnoreProperties("salesChannels")
    private Offer offer;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSalesChannel() {
        return salesChannel;
    }

    public OfferSalesChannel salesChannel(String salesChannel) {
        this.salesChannel = salesChannel;
        return this;
    }

    public void setSalesChannel(String salesChannel) {
        this.salesChannel = salesChannel;
    }

    public Offer getOffer() {
        return offer;
    }

    public OfferSalesChannel offer(Offer offer) {
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
        if (!(o instanceof OfferSalesChannel)) {
            return false;
        }
        return id != null && id.equals(((OfferSalesChannel) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "OfferSalesChannel{" +
            "id=" + getId() +
            ", salesChannel='" + getSalesChannel() + "'" +
            "}";
    }
}
