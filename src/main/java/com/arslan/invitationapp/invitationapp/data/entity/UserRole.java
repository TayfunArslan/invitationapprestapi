package com.arslan.invitationapp.invitationapp.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "User_Roles")
@Getter
@Setter
public class UserRole extends BaseEntity{
    @ManyToOne(optional = false)
    private User user;

    @ManyToOne(optional = false)
    private Role role;

    @OneToOne
    private Organization organization;
}
