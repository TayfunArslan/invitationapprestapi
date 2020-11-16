package com.arslan.invitationapp.invitationapp.viewmodel;

import com.arslan.invitationapp.invitationapp.data.entity.Organization;
import com.arslan.invitationapp.invitationapp.data.entity.Role;
import com.arslan.invitationapp.invitationapp.data.entity.User;

import javax.persistence.OneToOne;
import java.time.LocalDate;

public class OrganizationCoOwnerViewModel {
    private long id;
    private LocalDate createdDate;
    private boolean isActive;
    private boolean isDeleted;
    private int organizationId;
    private int coOwnerId;
    private int roleId;

    public OrganizationCoOwnerViewModel() {
    }

    public OrganizationCoOwnerViewModel(long id, LocalDate createdDate, boolean isActive, boolean isDeleted, int organizationId, int coOwnerId, int roleId) {
        this.id = id;
        this.createdDate = createdDate;
        this.isActive = isActive;
        this.isDeleted = isDeleted;
        this.organizationId = organizationId;
        this.coOwnerId = coOwnerId;
        this.roleId = roleId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    public int getCoOwnerId() {
        return coOwnerId;
    }

    public void setCoOwnerId(int coOwnerId) {
        this.coOwnerId = coOwnerId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
