package com.arslan.invitationapp.invitationapp.data.entity;

import javax.persistence.*;

@Entity
@Table(name = "organization_coOwners")
public class OrganizationCoOwner extends BaseEntity {
    @OneToOne
    private Organization organization;

    @OneToOne
    private User coOwner;

    @OneToOne
    private Role role;

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
