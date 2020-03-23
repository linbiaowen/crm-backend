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

import com.hthk.crm.domain.enumeration.SubscriptionStatus;

/**
 * A CustSubscription.
 */
@Document(collection = "cust_subscription")
public class CustSubscription implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("subscription_id")
    private String subscriptionId;

    @Field("activation_date")
    private Instant activationDate;

    @Field("subs_end_date")
    private Instant subsEndDate;

    @Field("subs_purchase_date")
    private Instant subsPurchaseDate;

    @Field("orig_service_start_date")
    private Instant origServiceStartDate;

    @NotNull
    @Field("status")
    private SubscriptionStatus status;

    @Field("primary_subs_ind")
    private Boolean primarySubsInd;

    @Field("subs_last_status_code")
    private String subsLastStatusCode;

    @Field("last_status_updated_date")
    private Instant lastStatusUpdatedDate;

    @Field("cust_acct_id")
    private String custAcctId;

    @Field("billing_acct_id")
    private String billingAcctId;

    @Field("bill_cycle_id")
    private Integer billCycleId;

    @Field("order_id")
    private String orderId;

    @Field("matrixx_object_id")
    private String matrixxObjectId;

    @Field("subscriber_name")
    private String subscriberName;

    @Field("subs_dept_name")
    private String subsDeptName;

    @Field("self_care_password")
    private String selfCarePassword;

    @Field("subs_category")
    private String subsCategory;

    @Field("temp_subs_detail_ids")
    private String tempSubsDetailIds;

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
    @Field("custDocuments")
    private Set<CustDocument> custDocuments = new HashSet<>();

    //@DBRef
    @Field("custCommOptouts")
    private Set<CustCommOptoutMaster> custCommOptouts = new HashSet<>();

    //@DBRef
    @Field("subscriptionDetails")
    private Set<SubscriptionDetails> subscriptionDetails = new HashSet<>();

    //@DBRef
    @Field("supremeMasters")
    private Set<SupremeMaster> supremeMasters = new HashSet<>();

    //@DBRef
    @Field("custContacts")
    private Set<CustContact> custContacts = new HashSet<>();

    //@DBRef
    @Field("custAddresses")
    private Set<CustAddress> custAddresses = new HashSet<>();

    @DBRef
    @Field("orderMasters")
    private Set<OrderMaster> orderMasters = new HashSet<>();

    @DBRef
    @Field("customer")
    @JsonIgnoreProperties("custSubscriptions")
    private Customer customer;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public CustSubscription subscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
        return this;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public Instant getActivationDate() {
        return activationDate;
    }

    public CustSubscription activationDate(Instant activationDate) {
        this.activationDate = activationDate;
        return this;
    }

    public void setActivationDate(Instant activationDate) {
        this.activationDate = activationDate;
    }

    public Instant getSubsEndDate() {
        return subsEndDate;
    }

    public CustSubscription subsEndDate(Instant subsEndDate) {
        this.subsEndDate = subsEndDate;
        return this;
    }

    public void setSubsEndDate(Instant subsEndDate) {
        this.subsEndDate = subsEndDate;
    }

    public Instant getSubsPurchaseDate() {
        return subsPurchaseDate;
    }

    public CustSubscription subsPurchaseDate(Instant subsPurchaseDate) {
        this.subsPurchaseDate = subsPurchaseDate;
        return this;
    }

    public void setSubsPurchaseDate(Instant subsPurchaseDate) {
        this.subsPurchaseDate = subsPurchaseDate;
    }

    public Instant getOrigServiceStartDate() {
        return origServiceStartDate;
    }

    public CustSubscription origServiceStartDate(Instant origServiceStartDate) {
        this.origServiceStartDate = origServiceStartDate;
        return this;
    }

    public void setOrigServiceStartDate(Instant origServiceStartDate) {
        this.origServiceStartDate = origServiceStartDate;
    }

    public SubscriptionStatus getStatus() {
        return status;
    }

    public CustSubscription status(SubscriptionStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(SubscriptionStatus status) {
        this.status = status;
    }

    public Boolean isPrimarySubsInd() {
        return primarySubsInd;
    }

    public CustSubscription primarySubsInd(Boolean primarySubsInd) {
        this.primarySubsInd = primarySubsInd;
        return this;
    }

    public void setPrimarySubsInd(Boolean primarySubsInd) {
        this.primarySubsInd = primarySubsInd;
    }

    public String getSubsLastStatusCode() {
        return subsLastStatusCode;
    }

    public CustSubscription subsLastStatusCode(String subsLastStatusCode) {
        this.subsLastStatusCode = subsLastStatusCode;
        return this;
    }

    public void setSubsLastStatusCode(String subsLastStatusCode) {
        this.subsLastStatusCode = subsLastStatusCode;
    }

    public Instant getLastStatusUpdatedDate() {
        return lastStatusUpdatedDate;
    }

    public CustSubscription lastStatusUpdatedDate(Instant lastStatusUpdatedDate) {
        this.lastStatusUpdatedDate = lastStatusUpdatedDate;
        return this;
    }

    public void setLastStatusUpdatedDate(Instant lastStatusUpdatedDate) {
        this.lastStatusUpdatedDate = lastStatusUpdatedDate;
    }

    public String getCustAcctId() {
        return custAcctId;
    }

    public CustSubscription custAcctId(String custAcctId) {
        this.custAcctId = custAcctId;
        return this;
    }

    public void setCustAcctId(String custAcctId) {
        this.custAcctId = custAcctId;
    }

    public String getBillingAcctId() {
        return billingAcctId;
    }

    public CustSubscription billingAcctId(String billingAcctId) {
        this.billingAcctId = billingAcctId;
        return this;
    }

    public void setBillingAcctId(String billingAcctId) {
        this.billingAcctId = billingAcctId;
    }

    public Integer getBillCycleId() {
        return billCycleId;
    }

    public CustSubscription billCycleId(Integer billCycleId) {
        this.billCycleId = billCycleId;
        return this;
    }

    public void setBillCycleId(Integer billCycleId) {
        this.billCycleId = billCycleId;
    }

    public String getOrderId() {
        return orderId;
    }

    public CustSubscription orderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getMatrixxObjectId() {
        return matrixxObjectId;
    }

    public CustSubscription matrixxObjectId(String matrixxObjectId) {
        this.matrixxObjectId = matrixxObjectId;
        return this;
    }

    public void setMatrixxObjectId(String matrixxObjectId) {
        this.matrixxObjectId = matrixxObjectId;
    }

    public String getSubscriberName() {
        return subscriberName;
    }

    public CustSubscription subscriberName(String subscriberName) {
        this.subscriberName = subscriberName;
        return this;
    }

    public void setSubscriberName(String subscriberName) {
        this.subscriberName = subscriberName;
    }

    public String getSubsDeptName() {
        return subsDeptName;
    }

    public CustSubscription subsDeptName(String subsDeptName) {
        this.subsDeptName = subsDeptName;
        return this;
    }

    public void setSubsDeptName(String subsDeptName) {
        this.subsDeptName = subsDeptName;
    }

    public String getSelfCarePassword() {
        return selfCarePassword;
    }

    public CustSubscription selfCarePassword(String selfCarePassword) {
        this.selfCarePassword = selfCarePassword;
        return this;
    }

    public void setSelfCarePassword(String selfCarePassword) {
        this.selfCarePassword = selfCarePassword;
    }

    public String getSubsCategory() {
        return subsCategory;
    }

    public CustSubscription subsCategory(String subsCategory) {
        this.subsCategory = subsCategory;
        return this;
    }

    public void setSubsCategory(String subsCategory) {
        this.subsCategory = subsCategory;
    }

    public String getTempSubsDetailIds() {
        return tempSubsDetailIds;
    }

    public CustSubscription tempSubsDetailIds(String tempSubsDetailIds) {
        this.tempSubsDetailIds = tempSubsDetailIds;
        return this;
    }

    public void setTempSubsDetailIds(String tempSubsDetailIds) {
        this.tempSubsDetailIds = tempSubsDetailIds;
    }

    public Integer getLockCount() {
        return lockCount;
    }

    public CustSubscription lockCount(Integer lockCount) {
        this.lockCount = lockCount;
        return this;
    }

    public void setLockCount(Integer lockCount) {
        this.lockCount = lockCount;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public CustSubscription createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public CustSubscription createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public CustSubscription lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public CustSubscription lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getTenantId() {
        return tenantId;
    }

    public CustSubscription tenantId(String tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public Set<CustDocument> getCustDocuments() {
        return custDocuments;
    }

    public CustSubscription custDocuments(Set<CustDocument> custDocuments) {
        this.custDocuments = custDocuments;
        return this;
    }

    public CustSubscription addCustDocuments(CustDocument custDocument) {
        this.custDocuments.add(custDocument);
        custDocument.setCustSubscription(this);
        return this;
    }

    public CustSubscription removeCustDocuments(CustDocument custDocument) {
        this.custDocuments.remove(custDocument);
        custDocument.setCustSubscription(null);
        return this;
    }

    public void setCustDocuments(Set<CustDocument> custDocuments) {
        this.custDocuments = custDocuments;
    }

    public Set<CustCommOptoutMaster> getCustCommOptouts() {
        return custCommOptouts;
    }

    public CustSubscription custCommOptouts(Set<CustCommOptoutMaster> custCommOptoutMasters) {
        this.custCommOptouts = custCommOptoutMasters;
        return this;
    }

    public CustSubscription addCustCommOptouts(CustCommOptoutMaster custCommOptoutMaster) {
        this.custCommOptouts.add(custCommOptoutMaster);
        custCommOptoutMaster.setCustSubscription(this);
        return this;
    }

    public CustSubscription removeCustCommOptouts(CustCommOptoutMaster custCommOptoutMaster) {
        this.custCommOptouts.remove(custCommOptoutMaster);
        custCommOptoutMaster.setCustSubscription(null);
        return this;
    }

    public void setCustCommOptouts(Set<CustCommOptoutMaster> custCommOptoutMasters) {
        this.custCommOptouts = custCommOptoutMasters;
    }

    public Set<SubscriptionDetails> getSubscriptionDetails() {
        return subscriptionDetails;
    }

    public CustSubscription subscriptionDetails(Set<SubscriptionDetails> subscriptionDetails) {
        this.subscriptionDetails = subscriptionDetails;
        return this;
    }

    public CustSubscription addSubscriptionDetails(SubscriptionDetails subscriptionDetails) {
        this.subscriptionDetails.add(subscriptionDetails);
        subscriptionDetails.setCustSubscription(this);
        return this;
    }

    public CustSubscription removeSubscriptionDetails(SubscriptionDetails subscriptionDetails) {
        this.subscriptionDetails.remove(subscriptionDetails);
        subscriptionDetails.setCustSubscription(null);
        return this;
    }

    public void setSubscriptionDetails(Set<SubscriptionDetails> subscriptionDetails) {
        this.subscriptionDetails = subscriptionDetails;
    }

    public Set<SupremeMaster> getSupremeMasters() {
        return supremeMasters;
    }

    public CustSubscription supremeMasters(Set<SupremeMaster> supremeMasters) {
        this.supremeMasters = supremeMasters;
        return this;
    }

    public CustSubscription addSupremeMasters(SupremeMaster supremeMaster) {
        this.supremeMasters.add(supremeMaster);
        supremeMaster.setCustSubscription(this);
        return this;
    }

    public CustSubscription removeSupremeMasters(SupremeMaster supremeMaster) {
        this.supremeMasters.remove(supremeMaster);
        supremeMaster.setCustSubscription(null);
        return this;
    }

    public void setSupremeMasters(Set<SupremeMaster> supremeMasters) {
        this.supremeMasters = supremeMasters;
    }

    public Set<CustContact> getCustContacts() {
        return custContacts;
    }

    public CustSubscription custContacts(Set<CustContact> custContacts) {
        this.custContacts = custContacts;
        return this;
    }

    public CustSubscription addCustContacts(CustContact custContact) {
        this.custContacts.add(custContact);
        custContact.setCustSubscription(this);
        return this;
    }

    public CustSubscription removeCustContacts(CustContact custContact) {
        this.custContacts.remove(custContact);
        custContact.setCustSubscription(null);
        return this;
    }

    public void setCustContacts(Set<CustContact> custContacts) {
        this.custContacts = custContacts;
    }

    public Set<CustAddress> getCustAddresses() {
        return custAddresses;
    }

    public CustSubscription custAddresses(Set<CustAddress> custAddresses) {
        this.custAddresses = custAddresses;
        return this;
    }

    public CustSubscription addCustAddresses(CustAddress custAddress) {
        this.custAddresses.add(custAddress);
        custAddress.setCustSubscription(this);
        return this;
    }

    public CustSubscription removeCustAddresses(CustAddress custAddress) {
        this.custAddresses.remove(custAddress);
        custAddress.setCustSubscription(null);
        return this;
    }

    public void setCustAddresses(Set<CustAddress> custAddresses) {
        this.custAddresses = custAddresses;
    }

    public Set<OrderMaster> getOrderMasters() {
        return orderMasters;
    }

    public CustSubscription orderMasters(Set<OrderMaster> orderMasters) {
        this.orderMasters = orderMasters;
        return this;
    }

    public CustSubscription addOrderMasters(OrderMaster orderMaster) {
        this.orderMasters.add(orderMaster);
        orderMaster.setCustSubscription(this);
        return this;
    }

    public CustSubscription removeOrderMasters(OrderMaster orderMaster) {
        this.orderMasters.remove(orderMaster);
        orderMaster.setCustSubscription(null);
        return this;
    }

    public void setOrderMasters(Set<OrderMaster> orderMasters) {
        this.orderMasters = orderMasters;
    }

    public Customer getCustomer() {
        return customer;
    }

    public CustSubscription customer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CustSubscription)) {
            return false;
        }
        return id != null && id.equals(((CustSubscription) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "CustSubscription{" +
            "id=" + getId() +
            ", subscriptionId='" + getSubscriptionId() + "'" +
            ", activationDate='" + getActivationDate() + "'" +
            ", subsEndDate='" + getSubsEndDate() + "'" +
            ", subsPurchaseDate='" + getSubsPurchaseDate() + "'" +
            ", origServiceStartDate='" + getOrigServiceStartDate() + "'" +
            ", status='" + getStatus() + "'" +
            ", primarySubsInd='" + isPrimarySubsInd() + "'" +
            ", subsLastStatusCode='" + getSubsLastStatusCode() + "'" +
            ", lastStatusUpdatedDate='" + getLastStatusUpdatedDate() + "'" +
            ", custAcctId='" + getCustAcctId() + "'" +
            ", billingAcctId='" + getBillingAcctId() + "'" +
            ", billCycleId=" + getBillCycleId() +
            ", orderId='" + getOrderId() + "'" +
            ", matrixxObjectId='" + getMatrixxObjectId() + "'" +
            ", subscriberName='" + getSubscriberName() + "'" +
            ", subsDeptName='" + getSubsDeptName() + "'" +
            ", selfCarePassword='" + getSelfCarePassword() + "'" +
            ", subsCategory='" + getSubsCategory() + "'" +
            ", tempSubsDetailIds='" + getTempSubsDetailIds() + "'" +
            ", lockCount=" + getLockCount() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", tenantId='" + getTenantId() + "'" +
            "}";
    }
}
