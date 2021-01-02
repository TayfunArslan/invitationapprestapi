package com.arslan.invitationapp.invitationapp.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "organization_coOwners")
@Getter
@Setter
public class OrganizationCoOwner extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Organization organization;

    @ManyToOne(optional = false)
    private User coOwner;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Role role;
}
