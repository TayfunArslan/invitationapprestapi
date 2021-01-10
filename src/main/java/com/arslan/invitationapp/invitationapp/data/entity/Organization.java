package com.arslan.invitationapp.invitationapp.data.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Organizations")
@Getter
@Setter
public class Organization extends BaseEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "code", unique = true)
    private String code;

    @Column(name = "date")
    private LocalDateTime date;

    @ManyToOne(optional = false)
    private User createdUser;

    @OneToMany(mappedBy = "organization", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Where(clause = "is_deleted = false")
    private List<Guest> guests;

    @OneToMany(mappedBy = "organization", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Where(clause = "is_deleted = false")
    private List<OrganizationCoOwner> organizationCoOwners;
}
