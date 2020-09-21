package com.arslan.invitationapp.invitationapp.data.entity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "OrganizationGuest")
public class OrganizationGuest extends BaseEntity {
    @OneToOne
    private Organization organization;

    @OneToOne
    private Guest guest;

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }
}
