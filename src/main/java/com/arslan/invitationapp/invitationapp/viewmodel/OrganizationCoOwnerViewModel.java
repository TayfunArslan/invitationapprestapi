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
    private Organization organization;
    private User coOwner;
    private Role role;

    public OrganizationCoOwnerViewModel() {}

    public OrganizationCoOwnerViewModel(long id, LocalDate createdDate, boolean isActive, boolean isDeleted, Organization organization, User coOwner, Role role) {
        this.id = id;
        this.createdDate = createdDate;
        this.isActive = isActive;
        this.isDeleted = isDeleted;
        this.organization = organization;
        this.coOwner = coOwner;
        this.role = role;
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

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public User getCoOwner() {
        return coOwner;
    }

    public void setCoOwner(User coOwner) {
        this.coOwner = coOwner;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
