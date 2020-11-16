package com.arslan.invitationapp.invitationapp.data.entity;

import javax.persistence.*;

@Entity
@Table(name = "organization_coOwners")
public class OrganizationCoOwner extends BaseEntity {
    @Column(name = "organization_id")
    private int organizationId;
    @Column(name = "co_owner_id")
    private int coOwnerId;
    @Column(name = "role_id")
    private int roleId;

    @OneToOne
    @Transient
    private Organization organization;

    @OneToOne
    @Transient
    private User coOwner;

    @OneToOne
    @Transient
    private Role role;

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
