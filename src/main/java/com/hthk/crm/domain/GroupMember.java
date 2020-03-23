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

import com.hthk.crm.domain.enumeration.GroupRole;

/**
 * A GroupMember.
 */
@Document(collection = "group_member")
public class GroupMember implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("group_member_id")
    private Long groupMemberId;

    @NotNull
    @Field("group_id")
    private Long groupId;

    @NotNull
    @Field("msisdn")
    private String msisdn;

    @NotNull
    @Field("group_role")
    private GroupRole groupRole;

    @Field("end_reason_code")
    private String endReasonCode;

    @Field("remarks")
    private String remarks;

    @Field("start_date")
    private Instant startDate;

    @Field("end_date")
    private Instant endDate;

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
    @Field("subscriptionGroup")
    @JsonIgnoreProperties("groupMembers")
    private SubscriptionGroup subscriptionGroup;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getGroupMemberId() {
        return groupMemberId;
    }

    public GroupMember groupMemberId(Long groupMemberId) {
        this.groupMemberId = groupMemberId;
        return this;
    }

    public void setGroupMemberId(Long groupMemberId) {
        this.groupMemberId = groupMemberId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public GroupMember groupId(Long groupId) {
        this.groupId = groupId;
        return this;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public GroupMember msisdn(String msisdn) {
        this.msisdn = msisdn;
        return this;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public GroupRole getGroupRole() {
        return groupRole;
    }

    public GroupMember groupRole(GroupRole groupRole) {
        this.groupRole = groupRole;
        return this;
    }

    public void setGroupRole(GroupRole groupRole) {
        this.groupRole = groupRole;
    }

    public String getEndReasonCode() {
        return endReasonCode;
    }

    public GroupMember endReasonCode(String endReasonCode) {
        this.endReasonCode = endReasonCode;
        return this;
    }

    public void setEndReasonCode(String endReasonCode) {
        this.endReasonCode = endReasonCode;
    }

    public String getRemarks() {
        return remarks;
    }

    public GroupMember remarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public GroupMember startDate(Instant startDate) {
        this.startDate = startDate;
        return this;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public GroupMember endDate(Instant endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    public Integer getLockCount() {
        return lockCount;
    }

    public GroupMember lockCount(Integer lockCount) {
        this.lockCount = lockCount;
        return this;
    }

    public void setLockCount(Integer lockCount) {
        this.lockCount = lockCount;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public GroupMember createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public GroupMember createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public GroupMember lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public GroupMember lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getTenantId() {
        return tenantId;
    }

    public GroupMember tenantId(String tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public SubscriptionGroup getSubscriptionGroup() {
        return subscriptionGroup;
    }

    public GroupMember subscriptionGroup(SubscriptionGroup subscriptionGroup) {
        this.subscriptionGroup = subscriptionGroup;
        return this;
    }

    public void setSubscriptionGroup(SubscriptionGroup subscriptionGroup) {
        this.subscriptionGroup = subscriptionGroup;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GroupMember)) {
            return false;
        }
        return id != null && id.equals(((GroupMember) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "GroupMember{" +
            "id=" + getId() +
            ", groupMemberId=" + getGroupMemberId() +
            ", groupId=" + getGroupId() +
            ", msisdn='" + getMsisdn() + "'" +
            ", groupRole='" + getGroupRole() + "'" +
            ", endReasonCode='" + getEndReasonCode() + "'" +
            ", remarks='" + getRemarks() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", lockCount=" + getLockCount() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", tenantId='" + getTenantId() + "'" +
            "}";
    }
}
